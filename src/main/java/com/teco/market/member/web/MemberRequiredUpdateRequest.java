package com.teco.market.member.web;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequiredUpdateRequest {
    @NotNull
    private String nickname;
    @NotNull
    private Long generationId;

    public MemberRequiredUpdateRequest(String nickname, Long generationId) {
        this.nickname = nickname;
        this.generationId = generationId;
    }
}
