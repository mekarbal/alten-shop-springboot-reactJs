package com.shop.altenshop.services;

import com.shop.altenshop.DTOs.WishlistDTO;
import com.shop.altenshop.entities.Wishlist;

public interface WishlistService {
     String addProductToWishlist(Long userId, Long productId);
     void removeProductFromWishlist(Long userId, Long productId);
     WishlistDTO getWishlistForUser(Long userId);

}
