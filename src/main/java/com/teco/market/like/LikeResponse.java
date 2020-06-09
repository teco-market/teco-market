package com.teco.market.like;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeResponse {
    private Long count;

    private LikeResponse(Long count) {
        this.count = count;
    }

    public static LikeResponse of(Long count) {
        return new LikeResponse(count);
    }
}
