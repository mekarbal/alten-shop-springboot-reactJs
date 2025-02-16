package com.shop.altenshop.DTOs;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class WishlistDTO {
    private List<ProductDTO> products;
}