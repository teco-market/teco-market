package com.teco.market.like;

import java.math.BigDecimal;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyLikeResponse {
    private Long postId;
    private String postTitle;
    private BigDecimal postPrice;
    private Long myLikeCount;

    @QueryProjection
    @Builder
    public MyLikeResponse(Long postId, String postTitle, BigDecimal postPrice, Long myLikeCount) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postPrice = postPrice;
        this.myLikeCount = myLikeCount;
    }
}
