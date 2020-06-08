package com.teco.market.oauth2.ui.service;

import org.springframework.stereotype.Service;

import com.teco.market.domain.member.Member;
import com.teco.market.domain.member.MemberRepository;
import com.teco.market.domain.member.Role;
import com.teco.market.oauth2.ui.AuthInfoRequest;
import com.teco.market.oauth2.ui.repository.GoogleAuthRepository;
import com.teco.market.oauth2.ui.user.GoogleUserInfo;
import com.teco.market.oauth2.ui.user.PlatformType;
import com.teco.market.oauth2.ui.util.JwtTokenProvider;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final GoogleAuthRepository googleAuthRepository;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String authGoogle(AuthInfoRequest authInfoRequest) {
        GoogleUserInfo googleUserInfo = googleAuthRepository.auth(authInfoRequest);
        Member member = memberRepository.findByPlatformIdAndPlatformType(googleUserInfo.getSub(), PlatformType.GOOGLE)
            .orElseGet(() -> memberRepository.save(new Member(googleUserInfo.getSub(),
                    PlatformType.GOOGLE, googleUserInfo.fullName(),
                    googleUserInfo.getEmail(), Role.GUEST)));

        return jwtTokenProvider.create(member.getId(), member.getRole());
    }
}
