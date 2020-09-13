package com.teco.market.infra.oauth2.web;

import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserResponse {
    private long id;
    private String nickname;
    @Nullable
    private String profileImage;
    @Nullable
    private String thumbnailImage;
    private boolean hasEmail;
    private boolean emailValid;
    private boolean emailVerified;
    private String email;
    private boolean emailNeedsAgreement;
    private boolean hasBirthday;
    private boolean birthdayNeedsAgreement;
    private String birthday;
    private boolean hasGender;
    private boolean genderNeedsAgreement;

    @Builder
    public KakaoUserResponse(
        String nickname, String profileImage, String thumbnailImage,
        long id, boolean hasEmail, boolean emailValid, boolean emailVerified,
        boolean emailNeedsAgreement, boolean hasBirthday, boolean birthdayNeedsAgreement,
        boolean hasGender, boolean genderNeedsAgreement, String birthday, String email) {
        this.id = id;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.thumbnailImage = thumbnailImage;
        this.hasEmail = hasEmail;
        this.emailValid = emailValid;
        this.emailVerified = emailVerified;
        this.email = email;
        this.emailNeedsAgreement = emailNeedsAgreement;
        this.hasBirthday = hasBirthday;
        this.birthdayNeedsAgreement = birthdayNeedsAgreement;
        this.birthday = birthday;
        this.hasGender = hasGender;
        this.genderNeedsAgreement = genderNeedsAgreement;
    }
}

