package com.shop.altenshop.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class CartDetailsDTO {
    private List<CartItemDTO> cartItems;
    private Integer productsNumber;
    private Double total;
}