package com.shop.altenshop.DTOs;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItemDTO {
    private ProductDTO product;
    private Integer quantity;
    private Double totalPrice;
}
