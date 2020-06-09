package com.teco.market.oauth2.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.teco.market.oauth2.util.JwtTokenProvider;
import com.teco.market.oauth2.web.AuthorizationExtractor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class BearerAuthInterceptor implements HandlerInterceptor {
    public static final String AUTH_TYPE = "Bearer";

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthorizationExtractor authorizationExtractor;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = authorizationExtractor.extract(request, AUTH_TYPE);
        jwtTokenProvider.validateToken(token);
        DecodedJWT decoded = jwtTokenProvider.decode(token);
        String id = decoded.getSubject();
        String role = decoded.getClaim("role").asString();
        request.setAttribute("id", Long.parseLong(id));
        request.setAttribute("role", String.valueOf(role));
        return true;
    }
}