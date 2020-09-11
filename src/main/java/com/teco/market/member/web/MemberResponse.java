package com.teco.market.member.web;

import com.querydsl.core.annotations.QueryProjection;
import com.teco.market.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponse {
    private Long id;
    private Long kakaoId;
    private String nickname;
    private String email;
    private String name;
    private String role;
    private String generation;

    @QueryProjection
    @Builder
    public MemberResponse(Long id, Long kakaoId, String nickname, String email, String name, String role, String generation) {
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.email = email;
        this.name = name;
        this.role = role;
        this.generation = generation;
    }

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
            .id(member.getId())
            .nickname(member.getNickname())
            .email(member.getEmail())
            .role(member.getRole().name())
            .generation(member.getGeneration().getAlias())
            .build();
    }
}
