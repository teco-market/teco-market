package com.teco.market.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {
    private String content;

    public CommentRequest(String content) {
        this.content = content;
    }
}
