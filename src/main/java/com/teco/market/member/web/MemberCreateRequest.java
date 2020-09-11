package com.teco.market.member.web;

import com.teco.market.member.Member;
import com.teco.market.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCreateRequest {
    private Long kakaoId;
    private String name;
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
