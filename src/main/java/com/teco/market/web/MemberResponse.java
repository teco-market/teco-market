package com.teco.market.web;

import com.teco.market.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponse {
    private String nickname;
    private String email;
    private String name;
    private String role;
    private String generation;

    @Builder
    private MemberResponse(String nickname, String email, String name, String role, String generation) {
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.role = role;
        this.generation = generation;
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
            .nickname(member.getNickname())
            .email(member.getEmail())
            .role(member.getRole().name())
            .generation(member.getGeneration().getAlias())
            .build();
    }
}
