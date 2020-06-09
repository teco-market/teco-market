package com.teco.market.oauth2.service;

import org.springframework.stereotype.Service;

import com.teco.market.member.Member;
import com.teco.market.member.MemberRepository;
import com.teco.market.member.Role;
import com.teco.market.oauth2.AuthInfoRequest;
import com.teco.market.oauth2.repository.GoogleAuthRepository;
import com.teco.market.oauth2.user.GoogleUserInfo;
import com.teco.market.oauth2.user.PlatformType;
import com.teco.market.oauth2.util.JwtTokenProvider;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final GoogleAuthRepository googleAuthRepository;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String authGoogle(AuthInfoRequest authInfoRequest) {
        GoogleUserInfo googleUserInfo = googleAuthRepository.auth(authInfoRequest);
        Member member = memberRepository.findByPlatform(googleUserInfo.getSub(), PlatformType.GOOGLE)
            .orElseGet(() -> memberRepository.save(new Member(googleUserInfo.getSub(),
                    PlatformType.GOOGLE, googleUserInfo.fullName(),
                    googleUserInfo.getEmail(), Role.GUEST)));

        return jwtTokenProvider.create(member.getId(), member.getRole());
    }
}
