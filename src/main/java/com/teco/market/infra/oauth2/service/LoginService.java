package com.teco.market.infra.oauth2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teco.market.infra.oauth2.JwtTokenProvider;
import com.teco.market.infra.oauth2.web.JwtTokenResponse;
import com.teco.market.infra.oauth2.web.KakaoTokenResponse;
import com.teco.market.infra.oauth2.web.KakaoUserResponse;
import com.teco.market.member.Member;
import com.teco.market.member.MemberService;
import com.teco.market.member.web.MemberCreateRequest;
import com.teco.market.member.web.MemberResponse;

@Service
@Transactional
public class LoginService {
    private final LoginAPIService<KakaoTokenResponse, KakaoUserResponse> kakaoAPIService;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginService(
        LoginAPIService<KakaoTokenResponse, KakaoUserResponse> kakaoAPIService,
        MemberService memberService,
        JwtTokenProvider jwtTokenProvider) {
        this.kakaoAPIService = kakaoAPIService;
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String createJwtTokenUrl(String code) {
        return kakaoAPIService.createTokenUrl(createJwtToken(code));
    }

    private JwtTokenResponse createJwtToken(String code) {
        KakaoTokenResponse kakaoTokenResponse = kakaoAPIService.fetchOAuthToken(code).block();
        KakaoUserResponse kakaoUserResponse = kakaoAPIService.fetchUserInfo(kakaoTokenResponse).block();
        if (memberService.existsByKakaoId(kakaoUserResponse.getId())) {
            Member member = memberService.findByKakaoId(kakaoUserResponse.getId());
            return JwtTokenResponse.of(jwtTokenProvider.createToken(member.getKakaoId().toString()));
        }

        MemberCreateRequest memberCreateRequest = MemberCreateRequest.builder()
            .kakaoId(kakaoUserResponse.getId())
            .email(kakaoUserResponse.getEmail())
            .name(kakaoUserResponse.getNickname())
            .build();
        MemberResponse memberResponse = memberService.createMember(memberCreateRequest);

        return JwtTokenResponse.of(jwtTokenProvider.createToken(memberResponse.getKakaoId().toString()));
    }
}
