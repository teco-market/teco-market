package com.teco.market.infra.oauth2.service;

import static com.teco.market.infra.oauth2.LoginFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teco.market.infra.oauth2.JwtTokenProvider;
import com.teco.market.infra.oauth2.LoginFixture;
import com.teco.market.infra.oauth2.web.JwtTokenResponse;
import com.teco.market.infra.oauth2.web.KakaoTokenResponse;
import com.teco.market.infra.oauth2.web.KakaoUserResponse;
import com.teco.market.member.MemberFixture;
import com.teco.market.member.MemberService;
import com.teco.market.member.web.MemberCreateRequest;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    private LoginService loginService;

    @Mock
    private LoginAPIService<KakaoTokenResponse, KakaoUserResponse> kakaoAPIService;
    @Mock
    private MemberService memberService;
    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() {
        loginService = new LoginService(kakaoAPIService, memberService, jwtTokenProvider);
    }

    @DisplayName("존재하는 회원의 경우 토큰을 반환한다.")
    @Test
    void createJwtTokenUrl() {
        when(kakaoAPIService.createTokenUrl(any(JwtTokenResponse.class))).thenReturn(URL);
        when(kakaoAPIService.fetchOAuthToken(CODE_VALUE)).thenReturn(Mono.just(createMockKakaoTokenResponse()));
        when(kakaoAPIService.fetchUserInfo(any(KakaoTokenResponse.class))).thenReturn(
            Mono.just(LoginFixture.createMockKakaoUserResponse()));
        when(memberService.existsByKakaoId(anyLong())).thenReturn(EXIST);
        when(memberService.findByKakaoId(anyLong())).thenReturn(MemberFixture.createWithId(1L));
        when(jwtTokenProvider.createToken(anyString())).thenReturn(TOKEN);

        assertThat(loginService.createJwtTokenUrl(CODE_VALUE)).isEqualTo(URL);
    }

    @DisplayName("존재하지 않는 회원의 경우 회원을 생성하여 토큰을 반환한다.")
    @Test
    void createJwtTokenUrlTest2() {
        when(kakaoAPIService.createTokenUrl(any(JwtTokenResponse.class))).thenReturn(URL);
        when(kakaoAPIService.fetchOAuthToken(CODE_VALUE)).thenReturn(Mono.just(createMockKakaoTokenResponse()));
        when(kakaoAPIService.fetchUserInfo(any(KakaoTokenResponse.class))).thenReturn(
            Mono.just(createMockKakaoUserResponse()));
        when(memberService.existsByKakaoId(anyLong())).thenReturn(NOT_EXIST);
        when(memberService.createMember(any(MemberCreateRequest.class))).thenReturn(MemberFixture.createResponse());
        when(jwtTokenProvider.createToken(anyString())).thenReturn(TOKEN);

        assertThat(loginService.createJwtTokenUrl(CODE_VALUE)).isEqualTo(URL);
    }
}