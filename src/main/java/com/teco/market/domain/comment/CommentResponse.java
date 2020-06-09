package com.teco.market.domain.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentResponse {
    private String writer;
    private String content;

    @Builder
    public CommentResponse(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }
}
