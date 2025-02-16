package com.shop.altenshop.repositories;

import com.shop.altenshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}