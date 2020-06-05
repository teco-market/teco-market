package com.teco.market.web;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequest {
    private String nickname;
    private Long generationId;

    public MemberUpdateRequest(String nickname, Long generationId) {
        this.nickname = nickname;
        this.generationId = generationId;
    }
}
