package com.shop.altenshop.services;


import com.shop.altenshop.DTOs.CartDetailsDTO;
import com.shop.altenshop.entities.Cart;

public interface CartService {


    String addProductToCart(Long userId, Long productId, int quantity);
    void removeProductFromCart(Long userId, Long productId);
    CartDetailsDTO getCartDetails(Long userId);

}
