package com.teco.market.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.teco.market.infra.oauth2.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BearerInterceptor implements HandlerInterceptor {
    private final AuthorizationExtractor extractor;
    private final JwtTokenProvider provider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (isExcludePatterns(request)) {
            return true;
        }
        String bearer = extractor.extract(request);
        String kakaoId = provider.getSubject(bearer);
        request.setAttribute("kakaoId", kakaoId);

        return true;
    }

    private boolean isExcludePatterns(final HttpServletRequest request) {
        return HttpMethod.POST.matches(request.getMethod()) && request.getServletPath().equals("/api/members");
    }
}
