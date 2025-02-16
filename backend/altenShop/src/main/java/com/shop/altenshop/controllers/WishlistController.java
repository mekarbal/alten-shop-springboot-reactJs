package com.shop.altenshop.controllers;

import com.shop.altenshop.DTOs.WishlistDTO;
import com.shop.altenshop.entities.Wishlist;
import com.shop.altenshop.services.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/add/{productId}")
    public String addProductToWishlist(@PathVariable Long userId, @PathVariable Long productId) {
       return wishlistService.addProductToWishlist(userId, productId);
    }

    @DeleteMapping("/remove/{productId}")
    public void removeProductFromWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        wishlistService.removeProductFromWishlist(userId, productId);
    }

    @GetMapping("/details")
    public ResponseEntity<WishlistDTO> getWishlist(@PathVariable Long userId) {
        WishlistDTO wishlistDTO = wishlistService.getWishlistForUser(userId);
        return ResponseEntity.ok(wishlistDTO);
    }
}
