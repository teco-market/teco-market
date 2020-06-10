package com.teco.market.comment.web;

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
