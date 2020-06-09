package com.teco.market.oauth2.web;

import java.util.Enumeration;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.teco.market.exception.invalid.InvalidJwtTokenException;

@Component
public class AuthorizationExtractor {

    private static final String AUTHORIZATION = "Authorization";

    public String extract(HttpServletRequest request, String authType) {
        Enumeration<String> headers = request.getHeaders(AUTHORIZATION);
        if (!Objects.equals(headers.nextElement(), authType)) {
            throw new InvalidJwtTokenException();
        }
        return headers.nextElement();
    }
}
