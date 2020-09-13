package com.teco.market.support;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.web.context.request.RequestAttributes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

import com.teco.market.common.exception.notfound.NotFoundMemberException;
import com.teco.market.member.Member;
import com.teco.market.member.MemberFixture;
import com.teco.market.member.MemberService;

@ExtendWith(MockitoExtension.class)
class LoginMemberArgumentResolverTest {

    @Mock
    private MemberService memberService;

    private ServletWebRequest servletWebRequest;

    private LoginMemberArgumentResolver loginMemberArgumentResolver;

    private MethodParameter methodParameter;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        loginMemberArgumentResolver = new LoginMemberArgumentResolver(memberService);
        servletWebRequest = new ServletWebRequest(new MockHttpServletRequest());
        methodParameter = new MethodParameter(SupportUtil.TestController.class.getMethod("testMethod", Member.class),
            0);
    }

    @DisplayName("Parameter에 LoginMember annotation이 들어있는지 확인한다.")
    @Test
    void supportsParameterTest() {
        assertThat(loginMemberArgumentResolver.supportsParameter(methodParameter)).isTrue();
    }

    @DisplayName("Parameter에 LoginMember annotation이 없는 경우 false를 반환한다.")
    @Test
    void notSupportsParameterTest() throws NoSuchMethodException {
        methodParameter = new MethodParameter(SupportUtil.TestController.class.getMethod("notHandlerMethod", Member.class), 0);

        assertThat(loginMemberArgumentResolver.supportsParameter(methodParameter)).isFalse();
    }

    @DisplayName("로그인한 멤버의 정보를 가져온다.")
    @Test
    void resolveArgumentTest() {
        Member expected = MemberFixture.createWithId(1L);
        given(memberService.findByKakaoId(any(Long.class))).willReturn(expected);

        servletWebRequest.setAttribute("kakaoId", "1", SCOPE_REQUEST);
        Member actual = loginMemberArgumentResolver.resolveArgument(methodParameter, null, servletWebRequest, null);

        assertThat(actual.getId()).isEqualToComparingFieldByField(expected.getId());
    }

    @DisplayName("해당 kakaoId와 매칭되는 회원정보가 존재하지 않을 경우 MemberNotFoundException으로 예외처리한다.")
    @Test
    void resolveArgumentTest2() {
        long kakaoId = 1L;
        servletWebRequest.setAttribute("kakaoId", String.valueOf(kakaoId), SCOPE_REQUEST);
        given(memberService.findByKakaoId(kakaoId)).willThrow(new NotFoundMemberException());

        assertThatThrownBy(
            () -> loginMemberArgumentResolver.resolveArgument(methodParameter, null, servletWebRequest, null))
            .isInstanceOf(NotFoundMemberException.class);
    }

    @DisplayName("servletWebRequest attribute에 kakaoId 정보가 없을 때 AssertionError로 예외처리한다.")
    @Test
    void resolveArgumentTest3() {
        assertThatThrownBy(
            () -> loginMemberArgumentResolver.resolveArgument(methodParameter, null, servletWebRequest, null))
            .isInstanceOf(AssertionError.class)
            .hasMessageContaining("Cannot find KakaoId NativeWebRequest attribute!");
    }

    @DisplayName("parseLong(kakaoId)에서 NumberFormatException이 발생할 경우 MemberNotFound로 예외처리한다.")
    @Test
    void resolveArgumentTest4() {
        servletWebRequest.setAttribute("kakaoId", "notLongId", SCOPE_REQUEST);
        assertThatThrownBy(
            () -> loginMemberArgumentResolver.resolveArgument(methodParameter, null, servletWebRequest, null))
            .isInstanceOf(NotFoundMemberException.class);
    }
}