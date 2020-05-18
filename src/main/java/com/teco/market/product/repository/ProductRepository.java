package com.teco.market.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teco.market.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
