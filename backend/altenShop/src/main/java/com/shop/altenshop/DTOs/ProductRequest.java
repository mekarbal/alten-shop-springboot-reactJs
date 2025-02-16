package com.shop.altenshop.DTOs;

import com.shop.altenshop.enums.InventoryStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequest {
    @NotEmpty(message = "Code cannot be empty")
    private String code;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    private String description;
    private String image;
    private String category;
    @NotEmpty(message = "price cannot be empty")
    @NotNull(message = "price cannt be null")
    @Positive(message = "price must be grather than 0")
    private double price;

    private int quantity;
    private String internalReference;
    private Long shellId;
    private InventoryStatus inventoryStatus;
    private double rating;
}
