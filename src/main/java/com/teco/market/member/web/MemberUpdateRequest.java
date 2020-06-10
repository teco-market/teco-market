package com.teco.market.member.web;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequest {
    @NotNull
    private String nickname;
    @NotNull
    private Long generationId;

    public MemberUpdateRequest(String nickname, Long generationId) {
        this.nickname = nickname;
        this.generationId = generationId;
    }
}
