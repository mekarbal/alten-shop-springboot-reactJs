package com.shop.altenshop.DTOs;

import com.shop.altenshop.enums.InventoryStatus;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class ProductPatchRequest {

    @Size(max = 50, message = "Code must be less than 50 characters")
    private String code;

    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @Size(max = 200, message = "Image URL must be less than 200 characters")
    private String image;

    @Size(max = 50, message = "Category must be less than 50 characters")
    private String category;

    @PositiveOrZero(message = "Price must be a positive number or zero")
    private Double price;

    @PositiveOrZero(message = "Quantity must be a positive number or zero")
    private Integer quantity;

    @Size(max = 50, message = "Internal reference must be less than 50 characters")
    private String internalReference;

    @Positive(message = "Shell ID must be a positive number")
    private Long shellId;

    private InventoryStatus inventoryStatus;

    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
    private Double rating;
}