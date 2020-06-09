package com.teco.market.post.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class PostRequest {
    private String title;
    private List<MultipartFile> multipartFiles;
    private Long category;
    private double price;
    private String content;

    public PostRequest() {
    }

    public PostRequest(String title, List<MultipartFile> multipartFiles, Long category, double price,
        String content) {
        this.title = title;
        this.multipartFiles = multipartFiles;
        this.category = category;
        this.price = price;
        this.content = content;
    }
}
