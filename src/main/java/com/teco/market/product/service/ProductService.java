package com.teco.market.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teco.market.product.domain.Product;
import com.teco.market.product.dto.ProductRequest;
import com.teco.market.product.dto.ProductResponse;
import com.teco.market.product.exception.NotFoundProductException;
import com.teco.market.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse save(ProductRequest productRequest) {
        Product product = productRepository.save(productRequest.toProduct());
        return ProductResponse.of(product);
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new NotFoundProductException());
        return ProductResponse.of(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        List<Product> products = productRepository.findAll();
        return ProductResponse.listOf(products);
    }

    public ProductResponse update(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundProductException());
        product.update(productRequest.toProduct());
        return ProductResponse.of(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
