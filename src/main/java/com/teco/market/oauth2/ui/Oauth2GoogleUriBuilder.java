package com.teco.market.oauth2.ui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Component
public class Oauth2GoogleUriBuilder {
    private final String AUTHORIZE_URI = "https://accounts.google.com/o/oauth2/auth";
    private static final String CLIENT_ID = "client_id";
    private static final String ACCESS_TYPE = "access_type";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String RESPONSE_TYPE = "response_type";
    private static final String SCOPE = "scope";

    private final String clientId;
    private final String clientSecret;
    private final String accessType;
    private final String redirectUri;
    private final String responseType;
    private final String scope;

    public Oauth2GoogleUriBuilder(@Value("${google.clientId}") String clientId,
        @Value("${google.clientSecret") String clientSecret,
        @Value("${google.accessType}") String accessType,
        @Value("${google.redirectUri}") String redirectUri,
        @Value("${google.responseType}") String responseType,
        @Value("${google.scope}") String scope) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessType = accessType;
        this.redirectUri = redirectUri;
        this.responseType = responseType;
        this.scope = scope;
    }

    public String build() {
        return new DefaultUriBuilderFactory(AUTHORIZE_URI).builder()
            .queryParam(CLIENT_ID, clientId)
            .queryParam(ACCESS_TYPE, accessType)
            .queryParam(REDIRECT_URI, redirectUri)
            .queryParam(RESPONSE_TYPE, responseType)
            .queryParam(SCOPE, scope)
            .build().toString();
    }
}
