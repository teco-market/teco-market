package com.teco.market.oauth2.ui.web;

import java.util.Enumeration;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationExtractor {

    private static final String AUTHORIZATION = "Authorization";

    public String extract(HttpServletRequest request, String authType) {
        Enumeration<String> headers = request.getHeaders(AUTHORIZATION);
        return Optional.of(headers.nextElement())
            .filter(value -> value.toLowerCase().startsWith(authType.toLowerCase()))
            .map(value -> value.substring(authType.length()).trim())
            .map(value -> {
                int commaIndex = value.indexOf(',');
                return commaIndex <= 0 ? value : value.substring(0, commaIndex);
            }).orElse(Strings.EMPTY);
    }
}
