package com.shop.altenshop.DTOs;

import com.shop.altenshop.enums.InventoryStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponse {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private String internalReference;
    private Long shellId;
    private InventoryStatus inventoryStatus;
    private double rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
