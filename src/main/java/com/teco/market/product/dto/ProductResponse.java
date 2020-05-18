package com.teco.market.product.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.teco.market.product.domain.Image;
import com.teco.market.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductResponse {
    private Long id;
    private String title;
    private List<Image> images;
    private String content;
    private String category;
    private Long price;

    public static ProductResponse of(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getTitle(),
            product.getImages(),
            product.getContent(),
            product.getCategory().name(),
            product.getPrice()
        );
    }

    public static List<ProductResponse> listOf(List<Product> products) {
        return products.stream()
            .map(ProductResponse::of)
            .collect(Collectors.toList());
    }
}
