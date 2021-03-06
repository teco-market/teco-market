package com.teco.market.post.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.querydsl.core.annotations.QueryProjection;
import com.teco.market.comment.Comment;
import com.teco.market.comment.web.CommentResponse;
import com.teco.market.image.Photo;
import com.teco.market.member.web.MemberResponse;
import com.teco.market.post.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostDetailResponse {
    private Long id;
    private String title;
    private MemberResponse memberResponse;
    private List<String> images;
    private String category;
    private String content;
    private List<CommentResponse> commentResponses;
    private BigDecimal price;
    private Long likeCount;

    public PostDetailResponse() {
    }

    @QueryProjection
    @Builder
    public PostDetailResponse(Long id, String title, MemberResponse memberResponse, String category,
        List<String> photos,
        String content, List<CommentResponse> commentResponses, BigDecimal price, Long likeCount) {
        this.id = id;
        this.title = title;
        this.memberResponse = memberResponse;
        this.category = category;
        this.images = photos;
        this.content = content;
        this.commentResponses = commentResponses;
        this.price = price;
        this.likeCount = likeCount;
    }

    public static PostDetailResponse of(Post post, Long likeCount, List<Comment> comments) {
        List<String> photos = post.getPhotos().stream()
            .map(Photo::getUrl).collect(Collectors.toList());
        List<CommentResponse> commentResponses = comments.stream()
            .map(comment -> new CommentResponse(comment.getMember().getName(), comment.getContent()))
            .collect(Collectors.toList());

        return PostDetailResponse.builder()
            .id(post.getId())
            .title(post.getTitle())
            .memberResponse(MemberResponse.of(post.getMember()))
            .category(post.getCategory().getName())
            .photos(photos)
            .content(post.getContent())
            .commentResponses(commentResponses)
            .price(post.getPrice())
            .likeCount(likeCount)
            .build();
    }
}
