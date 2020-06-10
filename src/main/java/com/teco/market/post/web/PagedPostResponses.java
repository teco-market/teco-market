package com.teco.market.post.web;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PagedPostResponses implements Serializable {
    private Page<PostResponse> posts;

    public PagedPostResponses(Page<PostResponse> posts) {
        this.posts = posts;
    }

    public static PagedPostResponses of(Page<PostResponse> posts) {
        return new PagedPostResponses(posts);
    }
}
