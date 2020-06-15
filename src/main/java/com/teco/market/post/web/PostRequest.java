package com.teco.market.post.web;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class PostRequest {
    private String title;
    private Long category;
    private double price;
    private String content;

    public PostRequest() {
    }

    public PostRequest(String title, Long category, double price, String content) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.content = content;
    }
}
