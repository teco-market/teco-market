package com.teco.market.post.web;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponses {
    private List<PostResponse> responses;

    public PostResponses(List<PostResponse> responses) {
        this.responses = responses;
    }

    public static PostResponses of(List<PostResponse> responses) {
        return new PostResponses(responses);
    }
}
