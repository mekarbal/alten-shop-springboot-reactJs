package com.shop.altenshop.services.servicesImpl;


import com.shop.altenshop.DTOs.CartDetailsDTO;
import com.shop.altenshop.DTOs.CartItemDTO;
import com.shop.altenshop.DTOs.ProductDTO;
import com.shop.altenshop.entities.Cart;
import com.shop.altenshop.entities.CartItem;
import com.shop.altenshop.entities.Product;
import com.shop.altenshop.entities.User;
import com.shop.altenshop.repositories.CartRepository;
import com.shop.altenshop.repositories.ProductRepository;
import com.shop.altenshop.repositories.UserRepository;
import com.shop.altenshop.services.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CartSericeImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public String addProductToCart(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .user(user)
                            .items(new ArrayList<>())
                            .build();
                    return cartRepository.save(newCart);
                });

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .build();
            cart.getItems().add(newItem);
        }

        cartRepository.save(cart);
        return "Product added to cart successfully";
    }

    @Override
    @Transactional
    public void removeProductFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user id: " + userId));

        Optional<CartItem> optionalCartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        optionalCartItem.ifPresentOrElse(item -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
            } else {
                cart.getItems().remove(item);
            }
            cartRepository.save(cart);
        }, () -> {
            throw new RuntimeException("Product not found in cart for product id: " + productId);
        });
    }

    @Override
    public CartDetailsDTO getCartDetails(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user id: " + userId));

        List<CartItemDTO> cartItemDTOs = cart.getItems().stream()
                .map(item -> {
                    ProductDTO productDTO = ProductDTO.builder()
                            .id(item.getProduct().getId())
                            .name(item.getProduct().getName())
                            .description(item.getProduct().getDescription())
                            .price(item.getProduct().getPrice())
                            .build();

                    return CartItemDTO.builder()
                            .product(productDTO)
                            .quantity(item.getQuantity())
                            .totalPrice(item.getQuantity() * item.getProduct().getPrice())
                            .build();
                })
                .collect(Collectors.toList());

        Double total = cartItemDTOs.stream()
                .mapToDouble(CartItemDTO::getTotalPrice)
                .sum();

        return CartDetailsDTO.builder()
                .cartItems(cartItemDTOs)
                .productsNumber(cart.getItems().size())
                .total(total)
                .build();
    }
}