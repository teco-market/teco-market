package com.teco.market.post.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.querydsl.core.annotations.QueryProjection;
import com.teco.market.category.Category;
import com.teco.market.image.Photo;
import com.teco.market.member.domain.Member;
import com.teco.market.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String thumbnail;
    private String title;
    private String name;
    private String nickname;
    private String category;
    private BigDecimal price;
    private List<String> photos;

    @QueryProjection
    @Builder
    public PostResponse(Post post, Member member, Category category) {
        this.id = post.getId();
        this.thumbnail = post.getThumbnail();
        this.title = post.getTitle();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.category = category.getName();
        this.price = post.getPrice();
        this.photos = post.getPhotos().stream().map(Photo::getUrl).collect(Collectors.toList());
    }
}
