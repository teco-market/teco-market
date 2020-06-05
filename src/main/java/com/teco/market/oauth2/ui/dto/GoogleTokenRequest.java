package com.teco.market.oauth2.ui.dto;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GoogleTokenRequest {
    private final String code;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String grantType;

    public MultiValueMap<String, String> toMap() {
        LinkedMultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("code", code);
        request.add("client_id", clientId);
        request.add("client_secret", clientSecret);
        request.add("redirect_uri", redirectUri);
        request.add("grant_type", grantType);
        return request;
    }
}
