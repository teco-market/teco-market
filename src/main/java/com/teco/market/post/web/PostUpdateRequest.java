package com.teco.market.post.web;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostUpdateRequest {
    private String title;
    private BigDecimal price;
    private String content;

    public PostUpdateRequest() {
    }
}
