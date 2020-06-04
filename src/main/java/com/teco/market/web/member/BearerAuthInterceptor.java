package com.teco.market.web.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.teco.market.oauth2.ui.util.JwtTokenConverter;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BearerAuthInterceptor implements HandlerInterceptor {
    private final AuthorizationExtractor extractor;
    private final JwtTokenConverter converter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
        Exception {
        String credential = extractor.extract(request, "Bearer");
        if (!converter.validToken(credential)) {
            throw new InvalidAuthenticationException();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception ex) throws Exception {

    }
}
