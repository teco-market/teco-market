package com.teco.market.oauth2.ui.util;

import static com.teco.market.util.DateUtil.*;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.teco.market.domain.member.Role;
import com.teco.market.exception.InvalidJwtTokenException;

@Component
public class JwtTokenConverter {
    private static final String SECRET = "L2sAYGCBLB7YIDoLqzRd4NRZE7TWcbKKttYkHgcMZUWyUwwXf49mrzk6Wpl8XLnwt2uMpP5ukzoSWcoDJlPPMJbMzXo_ZkoevlDVcCv2M8LY9iG1xOUSXrQjkHtJXPhGqyxxZbSM9MO5p6L5-7fNUyDzsP-hRQHAmgS8Sfnq6us";
    public static final String SUB = "sub";
    public static final String PICTURE = "picture";
    public static final String TYPE = "type";
    public static final String GOOGLE = "google";

    public static String create(Long id, Role role) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
            .withSubject(String.valueOf(id))
            .withClaim("role", role.name())
            .withIssuer("localhost")
            .withIssuedAt(convertDateFrom(LocalDateTime.now()))
            .withExpiresAt(convertDateFrom(LocalDateTime.now().plusDays(7)))
            .sign(algorithm);

    }

    public void validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new InvalidJwtTokenException();
        }
    }

    public DecodedJWT decode(String token) {
        return JWT.decode(token);
    }
}
