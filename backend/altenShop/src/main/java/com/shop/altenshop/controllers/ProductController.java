package com.shop.altenshop.controllers;


import com.shop.altenshop.DTOs.ProductPatchRequest;
import com.shop.altenshop.DTOs.ProductRequest;
import com.shop.altenshop.DTOs.ProductResponse;
import com.shop.altenshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/new-product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/product/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @PatchMapping("/update/{id}")
    public ProductResponse patchProduct(@PathVariable Long id, @RequestBody ProductPatchRequest productRequest) {
        return productService.patchProduct(id, productRequest);
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


}
