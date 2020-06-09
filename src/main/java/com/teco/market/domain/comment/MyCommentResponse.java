package com.teco.market.domain.comment;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyCommentResponse {
    private String postTitle;
    private String commentContent;

    @QueryProjection
    public MyCommentResponse(String postTitle, String commentContent) {
        this.postTitle = postTitle;
        this.commentContent = commentContent;
    }
}
