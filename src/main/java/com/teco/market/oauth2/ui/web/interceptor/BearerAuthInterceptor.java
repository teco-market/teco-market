package com.teco.market.oauth2.ui.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.teco.market.oauth2.ui.util.JwtTokenConverter;
import com.teco.market.oauth2.ui.web.AuthorizationExtractor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class BearerAuthInterceptor implements HandlerInterceptor {
    public static final String AUTH_TYPE = "Bearer";

    private final JwtTokenConverter jwtTokenConverter;
    private final AuthorizationExtractor authorizationExtractor;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = authorizationExtractor.extract(request, AUTH_TYPE);
        jwtTokenConverter.validateToken(token);
        String id = jwtTokenConverter.getSubject(token);
        request.setAttribute("id", Long.parseLong(id));
        return true;
    }
}
