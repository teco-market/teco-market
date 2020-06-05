package com.teco.market.oauth2.ui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.teco.market.oauth2.ui.dto.GoogleTokenRequest;

@Component
public class Oauth2GoogleClient {
    private static final String TOKEN_REQUEST_URI = "https://accounts.google.com/o/oauth2/token";
    private final String clientId;
    private final String clientSecret;

    public Oauth2GoogleClient(@Value("${google.clientId}") String clientId,
        @Value("${google.clientSecret}") String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public GoogleTokenRequest requestToken(String code) {
        return new GoogleTokenRequest(code, clientId, clientSecret,
            "http://localhost:3000/oauth2/google",
            "authorization_code");
    }
}
