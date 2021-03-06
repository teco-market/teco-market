package com.teco.market.infra.oauth2.service;

import static com.teco.market.infra.oauth2.LoginFixture.*;
import static com.teco.market.support.SupportUtil.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teco.market.infra.oauth2.JwtTokenProvider;
import com.teco.market.infra.oauth2.web.JwtTokenResponse;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class KakaoAPIServiceTest {
    private KakaoAPIService kakaoAPIService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    private ObjectMapper objectMapper;
    private MockWebServer mockWebServer;
    private String mockServerUrl;
    private Dispatcher dispatcher;
    private MockResponse tokenResponse;
    private MockResponse userResponse;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        mockWebServer = new MockWebServer();
        mockServerUrl = mockWebServer.url("/").toString();
        kakaoAPIService = new KakaoAPIService(mockServerUrl, mockServerUrl, SERVER_URI, CLIENT_ID_VALUE,
            CLIENT_SECRET_VALUE, GRANT_TYPE_VALUE);

        tokenResponse = new MockResponse()
            .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .setResponseCode(HttpStatus.OK.value())
            .setBody(objectMapper.writeValueAsString(createMockKakaoTokenResponse()));

        userResponse = new MockResponse()
            .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .setResponseCode(HttpStatus.OK.value())
            .setBody(objectMapper.writeValueAsString(createMockKakaoUserResponse()));

        dispatcher = new Dispatcher() {
            @NotNull
            @Override
            public MockResponse dispatch(RecordedRequest request) {
                if (request.getPath().contains(OAUTH_TOKEN_PATH)) {
                    return tokenResponse;
                }
                if (request.getPath().contains(USER_INFO_PATH)) {
                    return userResponse;
                }
                return new MockResponse().setResponseCode(404);
            }
        };
        mockWebServer.setDispatcher(dispatcher);
    }

    @AfterEach
    void shutdown() throws IOException {
        mockWebServer.shutdown();
    }

    @DisplayName("Redirect 될 토큰 페이지 Url을 리턴한다.")
    @Test
    void createTokenUrlTest() {
        when(jwtTokenProvider.createToken(KAKAO_ID)).thenReturn(ACCESS_TOKEN);

        String url = new DefaultUriBuilderFactory().builder()
            .path(SERVER_URI + LOGIN_CHECK_PATH)
            .queryParam(ACCESS_TOKEN, ACCESS_TOKEN)
            .build().toString();

        assertThat(kakaoAPIService.createTokenUrl(
            JwtTokenResponse.of(jwtTokenProvider.createToken(KAKAO_ID))))
            .isEqualTo(url);
    }

    @DisplayName("카카오 서버에 요청을 해 Mono 토큰 정보를 받아온다.")
    @Test
    void fetchOAuthTokenTest() {
        StepVerifier.create(kakaoAPIService.fetchOAuthToken(CODE_VALUE))
            .consumeNextWith(body -> assertThat(body).isEqualToComparingFieldByField(createMockKakaoTokenResponse()))
            .verifyComplete();
    }

    @DisplayName("카카오 서버에 요청을 해 Mono UserInfo를 받아온다.")
    @Test
    void fetchOAuthUserInfoTest() {
        StepVerifier.create(kakaoAPIService.fetchUserInfo(createMockKakaoTokenResponse()))
            .consumeNextWith(body -> assertThat(body).isEqualToComparingFieldByField(createMockKakaoUserResponse()))
            .verifyComplete();
    }
}