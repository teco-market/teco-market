package com.teco.market.oauth2.repository;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.teco.market.oauth2.dto.GoogleTokenRequest;
import com.teco.market.oauth2.dto.GoogleTokenResponse;
import com.teco.market.oauth2.AuthInfoRequest;
import com.teco.market.oauth2.Oauth2GoogleClient;
import com.teco.market.oauth2.user.GoogleUserInfo;

@Service
public class GoogleAuthRepository {
    private final Oauth2GoogleClient oauth2GoogleClient;

    public GoogleAuthRepository(Oauth2GoogleClient oauth2GoogleClient) {
        this.oauth2GoogleClient = oauth2GoogleClient;
    }

    public GoogleUserInfo auth(AuthInfoRequest authInfoRequest) {
        GoogleTokenResponse response = token(authInfoRequest);
        return WebClient.create("https://www.googleapis.com")
            .get()
            .uri(uriBuilder ->
                uriBuilder.path("/oauth2/v3/userinfo").build()
            ).header("Authorization", "Bearer " + response.getAccessToken())
            .retrieve()
            .bodyToMono(GoogleUserInfo.class).block();
    }

    private GoogleTokenResponse token(AuthInfoRequest authInfoRequest) {
        String code = authInfoRequest.getCode();
        GoogleTokenRequest googleTokenRequest = oauth2GoogleClient.requestToken(code);
        return WebClient.create("https://accounts.google.com")
            .post()
            .uri(uriBuilder -> uriBuilder.path("/o/oauth2/token")
                .queryParams(googleTokenRequest.toMap())
                .build())
            .retrieve()
            .bodyToMono(GoogleTokenResponse.class).block();
    }
}
