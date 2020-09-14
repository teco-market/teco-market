package com.teco.market.member.acceptance;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.teco.market.AcceptanceTest;
import com.teco.market.generation.GenerationFixture;
import com.teco.market.infra.oauth2.web.JwtTokenResponse;
import com.teco.market.member.MemberFixture;
import com.teco.market.member.web.MemberRequiredUpdateRequest;
import com.teco.market.member.web.MemberResponse;

public class MemberAcceptanceTest extends AcceptanceTest {

    public static final String API_MEMBERS = "/api/members";

    /**
     * 회원을 관리한다.
     *
     * Given Generation(기수) 존재한다.
     * When 회원 가입을 요청한다.
     * Then 회원이 생성 된다.
     *
     * When 가입한 회원이 로그인을 요청한다.
     * Then 로그인 되었다.
     *
     * When 회원의 필수정보를 수정한다.
     * Then 필수 정보가 수정되었다.
     *
     * When 회원의 정보를 수정한다.
     * Then 회원의 정보가 수정되었다.
     *
     * When 회원이 탈퇴를 요청한다.
     * Then 회원이 탈퇴 되었다.
     */
    @Test
    void manageMember() {
        Long generationId = createGeneration(GenerationFixture.createRequestWithId());
        JwtTokenResponse token = loginMember(MemberFixture.createMemberRequest());
        updateRequiredInfo(token, generationId);

    }

    private void updateRequiredInfo(JwtTokenResponse token, Long generationId) {
        MemberRequiredUpdateRequest expected = MemberFixture.createRequiredInfoRequest(generationId);
        doPatch(API_MEMBERS, createTokenHeader(token), expected);
        MemberResponse response = doGet(API_MEMBERS, createTokenHeader(token), MemberResponse.class);

        assertAll(
            () -> assertThat(response.getNickname()).isEqualTo(expected.getNickname()),
            () -> assertThat(response.getGenerationId()).isEqualTo(expected.getGenerationId())
        );
    }
}
