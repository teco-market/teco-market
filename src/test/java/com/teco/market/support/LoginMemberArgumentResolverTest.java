package com.teco.market.support;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.springframework.web.context.request.RequestAttributes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

import com.teco.market.member.MemberFixture;
import com.teco.market.member.domain.Member;

@ExtendWith(MockitoExtension.class)
class LoginMemberArgumentResolverTest {

    private ServletWebRequest servletWebRequest;

    private LoginMemberArgumentResolver loginMemberArgumentResolver;

    private MethodParameter methodParameter;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        loginMemberArgumentResolver = new LoginMemberArgumentResolver();
        servletWebRequest = new ServletWebRequest(new MockHttpServletRequest());
        methodParameter = new MethodParameter(SupportUtil.TestController.class.getMethod("testUserMethod", Member.class),
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
        methodParameter = new MethodParameter(SupportUtil.TestController.class.getMethod("testAdminMethod", Member.class), 0);

        assertThat(loginMemberArgumentResolver.supportsParameter(methodParameter)).isFalse();
    }

    @DisplayName("로그인한 멤버의 정보를 가져온다.")
    @Test
    void resolveArgumentTest() {
        Member expected = MemberFixture.createGuestWithId(1L);
        servletWebRequest.setAttribute("member", expected, SCOPE_REQUEST);
        Member actual = loginMemberArgumentResolver.resolveArgument(methodParameter, null, servletWebRequest, null);

        assertThat(expected).isEqualToComparingFieldByField(actual);
    }

    @DisplayName("servletWebRequest attribute에 kakaoId 정보가 없을 때 AssertionError로 예외처리한다.")
    @Test
    void resolveArgumentTest3() {
        assertThatThrownBy(
            () -> loginMemberArgumentResolver.resolveArgument(methodParameter, null, servletWebRequest, null))
            .isInstanceOf(AssertionError.class)
            .hasMessageContaining("Cannot find member NativeWebRequest attribute!");
    }
}