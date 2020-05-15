package com.teco.market.oauth2.ui;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.teco.market.oauth2.ui.dto.GoogleTokenRequest;

@Component
public class Oauth2GoogleClient {
    private static final String TOKEN_REQUEST_URI = "https://accounts.google.com/o/oauth2/token";
    private static final String USER_INFO_API_URI = "https://www.googleapis.com/oauth2/v4/userinfo";
    private final String clientId;
    private final String clientSecret;

    public Oauth2GoogleClient(@Value("${google.clientId}") String clientId,
        @Value("${google.clientSecret}") String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public GoogleTokenRequest requestToken(String code) {
        return new GoogleTokenRequest(code, clientId, clientSecret,
            "http://localhost:8080/oauth2/google",
            "authorization_code");
    }

    public String getUserInfo(String accessToken) {
        return "";
    }

    public URI getTokenRequestUri() {
        return URI.create(TOKEN_REQUEST_URI);
    }
}
