package com.shop.altenshop.DTOs;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
}
