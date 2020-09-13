package com.teco.market.member;

import com.teco.market.member.web.MemberResponse;

public class MemberFixture {
    public static final Long ID = 1L;
    public final static Long KAKAO_ID = 1038582L;
    public final static String EMAIL = "TEST@GMAIL.COM";
    public final static String NAME = "TEST_NAME";
    public final static String NICKNAME = "KYLE";
    public static final Role ROLE = Role.USER;

    public static Member createWithId(Long id) {
        return Member.builder()
            .id(id)
            .kakaoId(KAKAO_ID)
            .email(EMAIL)
            .name(NAME)
            .role(Role.GUEST)
            .build();
    }

    public static MemberResponse createResponse() {
        return MemberResponse.builder()
            .id(ID)
            .email(EMAIL)
            .kakaoId(KAKAO_ID)
            .name(NAME)
            .nickname(NICKNAME)
            .role(ROLE.name())
            .build();
    }
}
