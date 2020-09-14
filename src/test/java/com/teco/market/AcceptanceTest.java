package com.teco.market;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.teco.market.generation.GenerationCreateRequest;
import com.teco.market.infra.oauth2.JwtTokenProvider;
import com.teco.market.infra.oauth2.web.JwtTokenResponse;
import com.teco.market.member.MemberFixture;
import com.teco.market.member.web.MemberCreateRequest;
import com.teco.market.member.web.MemberResponse;
import io.restassured.http.Header;

public class AcceptanceTest extends BaseAcceptanceTestUtil {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    protected JwtTokenResponse loginMember(MemberCreateRequest request) {
        createMember(request);

        String token = jwtTokenProvider.createToken(String.valueOf(request.getKakaoId()));
        return JwtTokenResponse.of(token);
    }

    protected JwtTokenResponse loginAdmin(MemberCreateRequest request) {
        createMember(request);

        String token = jwtTokenProvider.createToken(String.valueOf(request.getKakaoId()));
        return JwtTokenResponse.of(token);
    }

    protected List<JwtTokenResponse> loginMembers(List<MemberCreateRequest> requests) {
        requests.forEach(this::createMember);

        return requests.stream()
            .map(request -> JwtTokenResponse.of(jwtTokenProvider.createToken(String.valueOf(request.getKakaoId()))))
            .collect(Collectors.toList());
    }

    protected Long createMember(MemberCreateRequest memberRequest) {
        return doPost("/api/members", memberRequest, null);
    }

    protected Long createAdmin() {
        return doPost("/api/members", MemberFixture.createAdmin(), null);
    }

    protected Long createGeneration(GenerationCreateRequest request) {
        createAdmin();

        return doPost("/api/generations", request, createTokenHeader(MemberFixture.KAKAO_ID));
    }

    protected MemberResponse findMember(Long kakaoId) {
        return doGet("/api/members", createTokenHeader(kakaoId), MemberResponse.class);
    }

    protected MemberResponse findMember(JwtTokenResponse tokenResponse) {
        return doGet("/api/members", createTokenHeader(tokenResponse), MemberResponse.class);
    }

    protected Header createTokenHeader(Long kakaoId) {
        return new Header("Authorization", "Bearer " + jwtTokenProvider.createToken(String.valueOf(kakaoId)));
    }

    protected Header createTokenHeader(JwtTokenResponse tokenResponse) {
        return new Header("Authorization", "Bearer " + tokenResponse.getAccessToken());
    }
}
