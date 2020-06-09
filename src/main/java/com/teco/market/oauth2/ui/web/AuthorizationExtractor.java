package com.teco.market.oauth2.ui.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.teco.market.exception.invalid.InvalidJwtTokenException;

@Component
public class AuthorizationExtractor {

    private static final String AUTHORIZATION = "Authorization";

    public String extract(HttpServletRequest request, String authType) {
        Enumeration<String> headers = request.getHeaders(AUTHORIZATION);
        if (headers.nextElement().equals(authType)) {
            throw new InvalidJwtTokenException();
        }
        return headers.nextElement();
    }
}
