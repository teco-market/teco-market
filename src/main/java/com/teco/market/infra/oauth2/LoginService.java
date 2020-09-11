package com.teco.market.infra.oauth2;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teco.market.infra.oauth2.dto.KakaoTokenResponse;
import com.teco.market.infra.oauth2.dto.KakaoUserResponse;
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

    public String createJwtTokenUrl(final String code) {
        return kakaoAPIService.createTokenUrl(createJwtToken(code));
    }

    private JwtTokenResponse createJwtToken(final String code) {
        KakaoTokenResponse kakaoTokenResponse = kakaoAPIService.fetchOAuthToken(code).block();
        KakaoUserResponse kakaoUserResponse = kakaoAPIService.fetchUserInfo(kakaoTokenResponse).block();
        if (memberService.existsByKakaoId(kakaoUserResponse.getId())) {
            MemberResponse memberResponse = memberService.findByKakaoId(kakaoUserResponse.getId());
            return JwtTokenResponse.of(jwtTokenProvider.createToken(memberResponse.getKakaoId().toString()));
        }

        final MemberCreateRequest memberCreateRequest = MemberCreateRequest.builder()
            .kakaoId(kakaoUserResponse.getId())
            .email(kakaoUserResponse.getEmail())
            .name(kakaoUserResponse.getNickname())
            .build();
        MemberResponse memberResponse = memberService.createMember(memberCreateRequest);

        return JwtTokenResponse.of(jwtTokenProvider.createToken(memberResponse.getKakaoId().toString()));
    }
}
