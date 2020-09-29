package com.teco.market.member.web;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.teco.market.member.MemberFixture;
import com.teco.market.member.application.MemberService;
import com.teco.market.support.BaseControllerTestUtil;
import com.teco.market.support.BearerInterceptor;
import com.teco.market.support.LoginMemberArgumentResolver;

@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest extends BaseControllerTestUtil {
    public static final String BASE_PATH = "/api/members";
    public static final String BASE_PATH_WITH_ID = "/api/members/1";

    @MockBean BearerInterceptor bearerInterceptor;
    @MockBean LoginMemberArgumentResolver argumentResolver;
    @MockBean MemberService memberService;

    @DisplayName("회원을 정상적으로 생성한다.")
    @Test
    void create() throws Exception {
        when(bearerInterceptor.preHandle(any(), any(), any())).thenReturn(true);
        when(memberService.createMember(any())).thenReturn(MemberFixture.createResponse());

        doPost(BASE_PATH, MemberFixture.createMemberRequest());
    }

    @DisplayName("회원을 정상적으로 조회한다.")
    @Test
    void get() throws Exception {
        MemberResponse expected = MemberFixture.createResponse();
        when(bearerInterceptor.preHandle(any(), any(), any())).thenReturn(true);
        when(memberService.retrieveById(anyLong())).thenReturn(expected);

        doGet(BASE_PATH_WITH_ID, expected);
    }

    @DisplayName("회원을 정상적으로 수정한다.")
    @Test
    void put() throws Exception {
        given(bearerInterceptor.preHandle(any(), any(), any())).willReturn(true);
        given(argumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(MemberFixture.createGuestWithId(1L));
        when(memberService.findByKakaoId(anyLong())).thenReturn(MemberFixture.createGuestWithId(1L));

        doPut(BASE_PATH, MemberFixture.createUpdateRequest());
    }

    @DisplayName("회원의 필수정보를 수정한다.")
    @Test
    void updateRequiredInfo() throws Exception {
        given(bearerInterceptor.preHandle(any(), any(), any())).willReturn(true);
        given(argumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(MemberFixture.createGuestWithId(1L));
        when(memberService.findByKakaoId(anyLong())).thenReturn(MemberFixture.createGuestWithId(1L));

        doPatch(BASE_PATH, MemberFixture.createRequiredInfoRequest());
    }

    @DisplayName("회원을 정상적으로 삭제한다.")
    @Test
    void delete() throws Exception {
        given(bearerInterceptor.preHandle(any(), any(), any())).willReturn(true);
        given(argumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(MemberFixture.createGuestWithId(1L));
        when(memberService.findByKakaoId(anyLong())).thenReturn(MemberFixture.createGuestWithId(1L));

        doDelete(BASE_PATH);
    }
}