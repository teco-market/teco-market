package com.teco.market.web.dto;

import java.math.BigDecimal;

import com.querydsl.core.annotations.QueryProjection;
import com.teco.market.domain.member.Member;
import com.teco.market.domain.post.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponse {
    private String thumbnail;
    private String title;
    private String name;
    private String nickname;
    private String category;
    private BigDecimal price;

    @QueryProjection
    @Builder
    public PostResponse(String thumbnail, String title, String name, String nickname, String category,
        BigDecimal price) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.name = name;
        this.nickname = nickname;
        this.category = category;
        this.price = price;
    }
}
