package com.teco.market.product.dto;

import org.springframework.web.multipart.MultipartFile;

import com.teco.market.product.domain.Category;
import com.teco.market.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductRequest {
    private String title;
    private MultipartFile images;
    private String content;
    private String category;
    private Long price;

    public Product toProduct() {
        return Product.builder()
            .title(title)
            .content(content)
            .category(Category.of(category))
            .price(price)
            .build();
    }
}
