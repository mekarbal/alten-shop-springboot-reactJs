package com.shop.altenshop.services;

import com.shop.altenshop.DTOs.ProductPatchRequest;
import com.shop.altenshop.DTOs.ProductRequest;
import com.shop.altenshop.DTOs.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getAllProducts();
    void deleteProduct(Long id);
    ProductResponse patchProduct(Long id, ProductPatchRequest patchRequest);}