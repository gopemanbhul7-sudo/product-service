package com.ecommerce.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCategoryId(Long categoryId);
}
