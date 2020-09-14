package com.teco.market.infra.oauth2;

import static com.teco.market.member.MemberFixture.*;

import com.teco.market.infra.oauth2.web.KakaoTokenResponse;
import com.teco.market.infra.oauth2.web.KakaoUserResponse;

public class LoginFixture {
    public static final String CODE = "code";
    public static final String CODE_VALUE = "CODE";
    public static final String URL = "http:localhost:8080";
    public static final String GUEST_TOKEN = "SAMPLE_ACCESS_GUEST_TOKEN";
    public static final String USER_TOKEN = "SAMPLE_ACCESS_USER_TOKEN";
    public static final String ADMIN_TOKEN = "SAMPLE_ACCESS_ADMIN_TOKEN";
    public static final String LOGIN_SUCCESS = "true";
    public static final String LOGIN_FAIL = "false";
    public static final String SERVER_URI = "http://localhost:8080";
    public static final String CLIENT_ID_VALUE = "1231234";
    public static final String CLIENT_SECRET_VALUE = "SECRET";
    public static final String GRANT_TYPE_VALUE = "code";
    public static final String OAUTH_TOKEN_PATH = "/oauth/token";
    public static final String LOGIN_CHECK_PATH = "/api/login/check";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String SUCCESS = "success";
    public static final String IS_CREATED = "is_created";
    public static final String USER_INFO_PATH = "/v2/user/me";
    public static final String TOKEN_TYPE = "Bearer ";
    public static final String SCOPE = "scope ";
    public static final String NICKNAME = "nickname";
    public static final String BIRTHDAY = "0429";
    public static final boolean EXIST = true;
    public static final boolean NOT_EXIST = false;
    public static final int EXPIRE = 1;


    public static KakaoTokenResponse createMockKakaoTokenResponse() {
        return KakaoTokenResponse.builder()
            .accessToken(USER_TOKEN)
            .refreshToken(USER_TOKEN)
            .refreshTokenExpiresIn(EXPIRE)
            .expiresIn(EXPIRE)
            .tokenType(TOKEN_TYPE)
            .scope(SCOPE)
            .build();
    }

    public static KakaoUserResponse createMockKakaoUserResponse() {
        return KakaoUserResponse.builder()
            .id(KAKAO_ID)
            .nickname(NICKNAME)
            .profileImage(URL)
            .thumbnailImage(URL)
            .hasEmail(EXIST)
            .emailValid(NOT_EXIST)
            .emailVerified(NOT_EXIST)
            .email(EMAIL)
            .emailNeedsAgreement(EXIST)
            .hasBirthday(EXIST)
            .birthdayNeedsAgreement(EXIST)
            .birthday(BIRTHDAY)
            .hasGender(EXIST)
            .genderNeedsAgreement(EXIST)
            .build();
    }

    public static String getUserTokenHeader() {
        return TOKEN_TYPE + USER_TOKEN;
    }

    public static String getGuestTokenHeader() {
        return TOKEN_TYPE + GUEST_TOKEN;
    }

    public static String getAdminTokenHeader() {
        return TOKEN_TYPE + ADMIN_TOKEN;
    }
}
