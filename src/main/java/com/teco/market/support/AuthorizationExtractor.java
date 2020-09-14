package com.teco.market.support;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class AuthorizationExtractor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer";
    private static final int TOKEN_TYPE_INDEX = 0;
    private static final int TOKEN_INDEX = 1;
    private static final int VALID_AUTHORIZATION_LENGTH = 2;

    public String extract(final HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if (Objects.isNull(authorization)) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        String[] splitToken = authorization.split(" ");

        if (splitToken.length != VALID_AUTHORIZATION_LENGTH || !splitToken[TOKEN_TYPE_INDEX].equals(TOKEN_TYPE)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
        return splitToken[TOKEN_INDEX];
    }
}
