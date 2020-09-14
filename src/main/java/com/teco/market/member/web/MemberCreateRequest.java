package com.teco.market.member.web;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.teco.market.member.domain.Member;
import com.teco.market.member.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCreateRequest {
    @NotNull
    private Long kakaoId;
    @NotBlank
    private String name;
    @NotBlank
    private String email;

    @Builder
    public MemberCreateRequest(Long kakaoId, String name, String email) {
        this.kakaoId = kakaoId;
        this.name = name;
        this.email = email;
    }

    public Member toMember() {
        return new Member(kakaoId, name, email, Role.GUEST);
    }
}
