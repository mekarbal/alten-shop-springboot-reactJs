package com.shop.altenshop.services.servicesImpl;

import com.shop.altenshop.DTOs.ProductDTO;
import com.shop.altenshop.DTOs.WishlistDTO;
import com.shop.altenshop.entities.Product;
import com.shop.altenshop.entities.User;
import com.shop.altenshop.entities.Wishlist;
import com.shop.altenshop.repositories.ProductRepository;
import com.shop.altenshop.repositories.UserRepository;
import com.shop.altenshop.repositories.WishlistRepository;
import com.shop.altenshop.services.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListSericeImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public String addProductToWishlist(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseGet(() -> {
                     Wishlist newWishList =Wishlist.builder()
                             .user(user)
                             .products(new ArrayList<>())
                             .build();
                     return wishlistRepository.save(newWishList);
                });

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!wishlist.getProducts().contains(product)) {
            wishlist.getProducts().add(product);
        }

         wishlistRepository.save(wishlist);
        return "Product added to wishlist";
    }

    @Override
    public void removeProductFromWishlist(Long userId, Long productId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        wishlist.getProducts().remove(product);
        wishlistRepository.save(wishlist);
    }

    @Override
    @Transactional(readOnly = true)
    public WishlistDTO getWishlistForUser(Long userId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user id: " + userId));

        List<ProductDTO> products = wishlist.getProducts().stream()
                .map(product -> ProductDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .build())
                .collect(Collectors.toList());

        return WishlistDTO.builder()
                .products(products)
                .build();
    }
}