package com.shop.altenshop.services.servicesImpl;

import com.shop.altenshop.DTOs.ProductPatchRequest;
import com.shop.altenshop.DTOs.ProductRequest;
import com.shop.altenshop.DTOs.ProductResponse;
import com.shop.altenshop.entities.Product;
import com.shop.altenshop.exceptions.ResourceNotFoundException;
import com.shop.altenshop.mappers.ProductMapper;
import com.shop.altenshop.repositories.ProductRepository;
import com.shop.altenshop.services.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        System.out.println("Product " + id );
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    @Override
    @Transactional
    public ProductResponse patchProduct(Long id, ProductPatchRequest patchRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
       applyPatch(product, patchRequest);

        Product updatedProduct = productRepository.save(product);
        return productMapper.toResponse(updatedProduct);
    }


    private void applyPatch(Product product, ProductPatchRequest patchRequest){
        if (patchRequest.getCode() != null) {
            product.setCode(patchRequest.getCode());
        }
        if (patchRequest.getName() != null) {
            product.setName(patchRequest.getName());
        }
        if (patchRequest.getDescription() != null) {
            product.setDescription(patchRequest.getDescription());
        }
        if (patchRequest.getImage() != null) {
            product.setImage(patchRequest.getImage());
        }
        if (patchRequest.getCategory() != null) {
            product.setCategory(patchRequest.getCategory());
        }
        if (patchRequest.getPrice() != null) {
            product.setPrice(patchRequest.getPrice());
        }
        if (patchRequest.getQuantity() != null) {
            product.setQuantity(patchRequest.getQuantity());
        }
        if (patchRequest.getInternalReference() != null) {
            product.setInternalReference(patchRequest.getInternalReference());
        }
        if (patchRequest.getShellId() != null) {
            product.setShellId(patchRequest.getShellId());
        }
        if (patchRequest.getInventoryStatus() != null) {
            product.setInventoryStatus(patchRequest.getInventoryStatus());
        }
        if (patchRequest.getRating() != null) {
            product.setRating(patchRequest.getRating());
        }

    }
}