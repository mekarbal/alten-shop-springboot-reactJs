package com.shop.altenshop.mappers;

import com.shop.altenshop.DTOs.ProductRequest;
import com.shop.altenshop.DTOs.ProductResponse;
import com.shop.altenshop.entities.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toEntity(ProductRequest productRequest);

    ProductResponse toResponse(Product product);

}
