package com.teco.market.web.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.teco.market.domain.image.Photo;
import com.teco.market.domain.post.Post;
import com.teco.market.web.MemberResponse;
import lombok.Builder;

public class PostDetailResponse {
    private String title;
    private MemberResponse memberResponse;
    private List<String> images;
    private String category;
    private String content;
    private BigDecimal price;
    private Long likeCount;

    public PostDetailResponse() {
    }

    @Builder
    private PostDetailResponse(String title, MemberResponse memberResponse, String category, List<String> photos,
        String content, BigDecimal price, Long likeCount) {
        this.title = title;
        this.memberResponse = memberResponse;
        this.category = category;
        this.images = photos;
        this.content = content;
        this.price = price;
        this.likeCount = likeCount;
    }

    public static PostDetailResponse of(Post post, Long likeCount) {
        return PostDetailResponse.builder()
            .title(post.getTitle())
            .memberResponse(MemberResponse.of(post.getMember()))
            .category(post.getCategory().getName())
            .photos(post.getPhotos().stream().map(Photo::getUrl).collect(Collectors.toList()))
            .content(post.getContent())
            .price(post.getPrice())
            .likeCount(likeCount)
            .build();
    }
}
