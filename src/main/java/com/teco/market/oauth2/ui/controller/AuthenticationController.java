package com.teco.market.oauth2.ui.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.teco.market.oauth2.ui.AuthInfoRequest;
import com.teco.market.oauth2.ui.Oauth2GoogleClient;
import com.teco.market.oauth2.ui.dto.GoogleTokenRequest;
import com.teco.market.oauth2.ui.dto.GoogleTokenResponse;
import com.teco.market.oauth2.ui.user.GoogleUserInfo;
import com.teco.market.oauth2.ui.util.JwtTokenConverter;

@RestController
public class AuthenticationController {

    private final Oauth2GoogleClient oauth2GoogleClient;
    private final WebClient webClient;

    public AuthenticationController(Oauth2GoogleClient oauth2GoogleClient,
        WebClient.Builder webClientBuilder) {
        this.oauth2GoogleClient = oauth2GoogleClient;
        this.webClient = webClientBuilder.build();
    }

    @PostMapping("/auth")
    public ResponseEntity<Void> auth(@RequestBody AuthInfoRequest authInfoRequest) {
        String code = authInfoRequest.getCode();
        GoogleTokenRequest googleTokenRequest = oauth2GoogleClient.requestToken(code);
        GoogleTokenResponse response = WebClient.create("https://accounts.google.com")
            .post()
            .uri(uriBuilder ->
                uriBuilder.path("/o/oauth2/token")
                    .queryParam("code", code)
                    .queryParam("client_id", googleTokenRequest.getClientId())
                    .queryParam("client_secret", googleTokenRequest.getClientSecret())
                    .queryParam("redirect_uri", googleTokenRequest.getRedirectUri())
                    .queryParam("grant_type", googleTokenRequest.getGrantType())
                    .build())
            .retrieve()
            .bodyToMono(GoogleTokenResponse.class).block();
        GoogleUserInfo userInfo = WebClient.create("https://www.googleapis.com")
            .get()
            .uri(uriBuilder ->
                uriBuilder.path("/oauth2/v3/userinfo").build()
            ).header("Authorization", "Bearer " + response.getAccessToken())
            .retrieve()
            .bodyToMono(GoogleUserInfo.class).block();
        String token = JwtTokenConverter.create(userInfo);
        return ResponseEntity.ok().header("token", token).build();
    }
}
