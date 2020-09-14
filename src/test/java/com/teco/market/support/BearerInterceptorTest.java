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

import com.teco.market.infra.oauth2.JwtTokenProvider;
import com.teco.market.member.domain.Member;

@ExtendWith(MockitoExtension.class)
class BearerInterceptorTest {
    @Mock
    private AuthorizationExtractor extractor;

    @Mock
    private JwtTokenProvider provider;

    private HandlerMethod handlerMethod;
    private MockHttpServletRequest servletRequest;
    private MockHttpServletResponse servletResponse;
    private BearerInterceptor interceptor;

    @BeforeEach
    void setUp() {
        servletRequest = new MockHttpServletRequest();
        servletResponse = new MockHttpServletResponse();
        interceptor = new BearerInterceptor(extractor, provider);
    }

    @DisplayName("정상적으로 동작하는 Intercept")
    @Test
    void preHandle() throws NoSuchMethodException {
        given(extractor.extract(any(HttpServletRequest.class))).willReturn(TOKEN_TYPE + MOCK_TOKEN);
        given(provider.getSubject(TOKEN_TYPE + MOCK_TOKEN)).willReturn(KAKAO_ID);

        Method method = TestController.class.getMethod("testMethod", Member.class);
        handlerMethod = new HandlerMethod(new TestController(), method);

        assertThat(interceptor.preHandle(servletRequest, servletResponse, handlerMethod)).isTrue();
    }

    @DisplayName("핸들러 메소드가 아닌 경우 로직을 수행하지 않고 true를 반환한다.")
    @Test
    void notHandlerPreHandle() throws NoSuchMethodException {
        Method method = TestController.class.getMethod("notHandlerMethod", Member.class);
        handlerMethod = new HandlerMethod(new TestController(), method);

        assertThat(interceptor.preHandle(servletRequest, servletResponse, handlerMethod)).isTrue();
    }

    @DisplayName("해당 인터셉터를 타지 않는 예외적인 경우 로직을 수행하지 않고 true를 반환한다.")
    @Test
    void excludePattern() throws NoSuchMethodException {
        Method method = TestController.class.getMethod("excludePattern");
        handlerMethod = new HandlerMethod(new TestController(), method);
        servletRequest.setRequestURI("/api/members");
        servletRequest.setMethod("POST");

        assertThat(interceptor.preHandle(servletRequest, servletResponse, handlerMethod)).isTrue();
    }
}