package com.teco.market.member;

import com.teco.market.member.domain.Member;
import com.teco.market.member.domain.Role;
import com.teco.market.member.web.MemberCreateRequest;
import com.teco.market.member.web.MemberRequiredUpdateRequest;
import com.teco.market.member.web.MemberResponse;
import com.teco.market.member.web.MemberUpdateRequest;

public class MemberFixture {
    public static final Long GENERATION_ID = 1L;
    public static final Long ID = 1L;
    public final static Long KAKAO_ID = 1038582L;
    public final static Long ADMIN_KAKAO_ID = 3498509801L;
    public final static String EMAIL = "TEST@GMAIL.COM";
    public final static String EMAIL2 = "UPDATED@GMAIL.COM";
    public final static String NAME = "TEST_NAME";
    public final static String NAME2 = "TEST_NAME-UPDATED";
    public final static String NICKNAME = "KYLE";
    public final static String NICKNAME2 = "KYLE-UPDATED";
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

    public static MemberCreateRequest createMemberRequest() {
        return MemberCreateRequest.builder()
            .name(NAME)
            .kakaoId(KAKAO_ID)
            .email(EMAIL)
            .build();
    }

    public static MemberCreateRequest createAdmin() {
        return MemberCreateRequest.builder()
            .name(NAME)
            .kakaoId(ADMIN_KAKAO_ID)
            .email(EMAIL)
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

    public static MemberUpdateRequest createUpdateRequest() {
        return MemberUpdateRequest.builder()
            .name(NAME2)
            .email(EMAIL2)
            .nickname(NICKNAME2)
            .build();
    }

    public static MemberRequiredUpdateRequest createRequiredInfoRequest() {
        return new MemberRequiredUpdateRequest(NICKNAME2, GENERATION_ID);
    }

    public static MemberRequiredUpdateRequest createRequiredInfoRequest(Long generationId) {
        return new MemberRequiredUpdateRequest(NICKNAME2, generationId);
    }
}
