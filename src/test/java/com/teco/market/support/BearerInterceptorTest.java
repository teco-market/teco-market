package com.teco.market.support;

import static com.teco.market.support.SupportUtil.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;

import com.teco.market.common.exception.AuthorizationException;
import com.teco.market.infra.oauth2.JwtTokenProvider;
import com.teco.market.member.MemberFixture;
import com.teco.market.member.application.MemberService;
import com.teco.market.member.domain.Member;

@ExtendWith(MockitoExtension.class)
class BearerInterceptorTest {
    @Mock
    private AuthenticationExtractor extractor;

    @Mock
    private JwtTokenProvider provider;

    @Mock
    private MemberService memberService;
    private MockHttpServletRequest servletRequest;
    private MockHttpServletResponse servletResponse;
    private BearerInterceptor interceptor;

    @BeforeEach
    void setUp() {
        servletRequest = new MockHttpServletRequest();
        servletResponse = new MockHttpServletResponse();
        interceptor = new BearerInterceptor(extractor, provider, memberService);
    }

    @DisplayName("NONE일 경우 로그인 없이 접근이 가능합니다.")
    @Test
    void preHandleNone() throws NoSuchMethodException {
        HandlerMethod handlerMethod = getAuthHandlerMethod("/api/none", "testNoneMethod");

        assertThat(interceptor.preHandle(servletRequest, servletResponse, handlerMethod)).isTrue();
    }

    @DisplayName("GUEST의 경우, GUEST에게 허용된 API만 접근이 가능합니다.")
    @Test
    void preHandleGuest() throws NoSuchMethodException {
        given(extractor.extract(any(HttpServletRequest.class))).willReturn(TOKEN_TYPE + MOCK_TOKEN);
        given(provider.getSubject(TOKEN_TYPE + MOCK_TOKEN)).willReturn(KAKAO_ID);
        given(memberService.findByKakaoId(anyLong())).willReturn(MemberFixture.createGuestWithId(1L));

        HandlerMethod noneHandler = getAuthHandlerMethod("/api/none", "testNoneMethod");
        assertThat(interceptor.preHandle(servletRequest, servletResponse, noneHandler)).isTrue();

        HandlerMethod guestHandler = getAuthHandlerMethod("/api/guest", "testGuestMethod");
        assertThat(interceptor.preHandle(servletRequest, servletResponse, guestHandler)).isTrue();
    }

    @DisplayName("USER의 경우, GUEST 및 USER에게 허용된 API만 접근이 가능합니다.")
    @Test
    void preHandleUser() throws NoSuchMethodException {
        given(extractor.extract(any(HttpServletRequest.class))).willReturn(TOKEN_TYPE + MOCK_TOKEN);
        given(provider.getSubject(TOKEN_TYPE + MOCK_TOKEN)).willReturn(KAKAO_ID);
        given(memberService.findByKakaoId(anyLong())).willReturn(MemberFixture.createUserWithId(1L));

        HandlerMethod noneHandler = getAuthHandlerMethod("/api/none", "testNoneMethod");
        assertThat(interceptor.preHandle(servletRequest, servletResponse, noneHandler)).isTrue();

        HandlerMethod guestHandler = getAuthHandlerMethod("/api/guest", "testGuestMethod");
        assertThat(interceptor.preHandle(servletRequest, servletResponse, guestHandler)).isTrue();

        HandlerMethod userHandler = getAuthHandlerMethod("/api/user", "testUserMethod");
        assertThat(interceptor.preHandle(servletRequest, servletResponse, userHandler)).isTrue();
    }

    @DisplayName("ADMIN의 경우, 모든 API에 접근이 가능합니다.")
    @Test
    void preHandleAdmin() throws NoSuchMethodException {
        given(extractor.extract(any(HttpServletRequest.class))).willReturn(TOKEN_TYPE + MOCK_TOKEN);
        given(provider.getSubject(TOKEN_TYPE + MOCK_TOKEN)).willReturn(KAKAO_ID);
        given(memberService.findByKakaoId(anyLong())).willReturn(MemberFixture.createAdminWithId(1L));

        HandlerMethod noneHandler = getAuthHandlerMethod("/api/none", "testNoneMethod");
        assertThat(interceptor.preHandle(servletRequest, servletResponse, noneHandler)).isTrue();

        HandlerMethod guestHandler = getAuthHandlerMethod("/api/guest", "testGuestMethod");
        assertThat(interceptor.preHandle(servletRequest, servletResponse, guestHandler)).isTrue();

        HandlerMethod userHandler = getAuthHandlerMethod("/api/user", "testUserMethod");
        assertThat(interceptor.preHandle(servletRequest, servletResponse, userHandler)).isTrue();

        HandlerMethod adminHandler = getAuthHandlerMethod("/api/admin", "testUserMethod");
        assertThat(interceptor.preHandle(servletRequest, servletResponse, adminHandler)).isTrue();
    }

    @DisplayName("권한이 없는 접근은 예외를 반환한다. GUEST가 USER API 접근")
    @Test
    void preHandleWithNoPermission() throws NoSuchMethodException {
        given(extractor.extract(any(HttpServletRequest.class))).willReturn(TOKEN_TYPE + MOCK_TOKEN);
        given(provider.getSubject(TOKEN_TYPE + MOCK_TOKEN)).willReturn(KAKAO_ID);
        given(memberService.findByKakaoId(anyLong())).willReturn(MemberFixture.createGuestWithId(1L));

        HandlerMethod userHandler = getAuthHandlerMethod("/api/user", "testUserMethod");
        assertThatThrownBy(() -> interceptor.preHandle(servletRequest, servletResponse, userHandler))
            .isInstanceOf(AuthorizationException.class);
    }

    @DisplayName("핸들러 메소드가 아닌 경우 로직을 수행하지 않고 true를 반환한다.")
    @Test
    void notHandlerPreHandle() throws NoSuchMethodException {
        Method method = TestController.class.getMethod("notHandlerMethod", Member.class);

        assertThat(interceptor.preHandle(servletRequest, servletResponse, method)).isTrue();
    }

    private HandlerMethod getAuthHandlerMethod(String s, String testNoneMethod) throws NoSuchMethodException {
        servletRequest.setServletPath(s);
        Method method = TestController.class.getMethod(testNoneMethod, Member.class);
        return new HandlerMethod(new TestController(), method);
    }
}