package com.teco.market.infra.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.teco.market.infra.oauth2.dto.KakaoTokenResponse;
import com.teco.market.infra.oauth2.dto.KakaoUserResponse;
import reactor.core.publisher.Mono;

@Component
@Transactional
public class KakaoAPIService implements LoginAPIService<KakaoTokenResponse, KakaoUserResponse> {
    private static final String CLIENT_ID = "client_id";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String REDIRECT_PATH = "/api/login/token";
    private static final String OAUTH_TOKEN_PATH = "/oauth/token";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CODE = "code";
    private static final String LOGIN_CHECK_PATH = "/api/login/check";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String USER_INFO_PATH = "/v2/user/me";
    private static final String AUTHORIZATION = "Authorization";
    private static final String AUTHORIZATION_TYPE = "Bearer ";

    private final String authorizeUri;
    private final String apiUri;
    private final String serverUri;
    private final String clientIdValue;
    private final String clientSecretValue;
    private final String grantTypeValue;

    public KakaoAPIService(@Value("${secrets.kakao.authorizeUri}") final String authorizeUri,
        @Value("${secrets.kakao.apiUri}") final String apiUri,
        @Value("${server.uri}") final String serverUri,
        @Value("${secrets.kakao.clientId}") final String clientIdValue,
        @Value("${secrets.kakao.clientSecret}") final String clientSecretValue,
        @Value("${secrets.kakao.grantType}") final String grantTypeValue) {
        this.authorizeUri = authorizeUri;
        this.apiUri = apiUri;
        this.serverUri = serverUri;
        this.clientIdValue = clientIdValue;
        this.clientSecretValue = clientSecretValue;
        this.grantTypeValue = grantTypeValue;
    }

    @Override
    public String createTokenUrl(final JwtTokenResponse token) {
        return new DefaultUriBuilderFactory().builder()
            .path(serverUri + LOGIN_CHECK_PATH)
            .queryParam(ACCESS_TOKEN, token.getAccessToken())
            .build().toString();
    }

    @Override
    public Mono<KakaoTokenResponse> fetchOAuthToken(final String code) {
        final WebClient webClient = WebClient.builder()
            .baseUrl(authorizeUri)
            .build();
        return webClient.post()
            .uri(uriBuilder -> uriBuilder
                .path(OAUTH_TOKEN_PATH)
                .queryParam(GRANT_TYPE, grantTypeValue)
                .queryParam(CLIENT_ID, clientIdValue)
                .queryParam(CLIENT_SECRET, clientSecretValue)
                .queryParam(CODE, code)
                .queryParam(REDIRECT_URI, serverUri + REDIRECT_PATH)
                .build())
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(KakaoTokenResponse.class);
    }

    @Override
    public Mono<KakaoUserResponse> fetchUserInfo(final KakaoTokenResponse token) {
        final WebClient webClient = WebClient.builder()
            .baseUrl(apiUri)
            .build();
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path(USER_INFO_PATH)
                .build())
            .header(AUTHORIZATION, AUTHORIZATION_TYPE + token.getAccessToken())
            .retrieve()
            .bodyToMono(KakaoUserResponse.class);
    }
}
