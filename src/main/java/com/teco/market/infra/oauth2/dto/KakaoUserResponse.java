package com.teco.market.infra.oauth2.dto;

import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KakaoUserResponse {
    private final long id;
    private final String nickname;

    @Nullable
    private final String profileImage;

    @Nullable
    private final String thumbnailImage;

    private final boolean hasEmail;
    private final boolean emailValid;
    private final boolean emailVerified;
    private final String email;
    private final boolean emailNeedsAgreement;
    private final boolean hasBirthday;
    private final boolean birthdayNeedsAgreement;
    private final String birthday;
    private final boolean hasGender;
    private final boolean genderNeedsAgreement;
}

