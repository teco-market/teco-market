package com.teco.market.oauth2.ui.util;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.teco.market.oauth2.ui.user.GoogleUserInfo;

@Component
public class JwtTokenConverter {
    private static final String SECRET = "L2sAYGCBLB7YIDoLqzRd4NRZE7TWcbKKttYkHgcMZUWyUwwXf49mrzk6Wpl8XLnwt2uMpP5ukzoSWcoDJlPPMJbMzXo_ZkoevlDVcCv2M8LY9iG1xOUSXrQjkHtJXPhGqyxxZbSM9MO5p6L5-7fNUyDzsP-hRQHAmgS8Sfnq6us";
    public static final String SUB = "sub";
    public static final String PICTURE = "picture";
    public static final String TYPE = "type";
    public static final String GOOGLE = "google";

    public static String create(GoogleUserInfo userInfo) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
            .withClaim(TYPE, GOOGLE)
            .withClaim(SUB, userInfo.getSub())
            .withClaim(PICTURE, userInfo.getPicture())
            .sign(algorithm);
    }
}
