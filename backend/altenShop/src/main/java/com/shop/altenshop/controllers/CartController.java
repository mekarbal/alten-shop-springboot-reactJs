package com.shop.altenshop.controllers;

import com.shop.altenshop.DTOs.AddToCartRequest;
import com.shop.altenshop.DTOs.CartDetailsDTO;
import com.shop.altenshop.entities.Cart;
import com.shop.altenshop.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/cart")
@RequiredArgsConstructor
public class CartController {

//    TODO: I used userId instead of creating middleware to extract the ID from the token due to time pressure

    private final CartService cartService;

    @PostMapping("/add/{productId}")
    public String addProductToCart(@PathVariable Long userId, @PathVariable Long productId,@RequestBody AddToCartRequest quantity) {
      return  cartService.addProductToCart(userId, productId, quantity.getQuantity());
    }

    @DeleteMapping("/remove/{productId}")
    public void removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        cartService.removeProductFromCart(userId, productId);
    }

    @GetMapping("/details")
    public ResponseEntity<CartDetailsDTO> getCartDetails(@PathVariable Long userId) {
        CartDetailsDTO cartDetails = cartService.getCartDetails(userId);
        return ResponseEntity.ok(cartDetails);
    }
}
