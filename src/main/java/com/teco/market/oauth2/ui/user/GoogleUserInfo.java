package com.teco.market.oauth2.ui.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoogleUserInfo {
    private String sub;
    private String name;
    @JsonProperty("given_name")
    private String givenName;
    @JsonProperty("family_name")
    private String familyName;
    private String picture;
    private String email;
    @JsonProperty("email_verified")
    private boolean emailVerified;
    private String locale;

    public GoogleUserInfo(String sub, String name, String givenName, String familyName,
        String picture, String email, boolean emailVerified, String locale) {
        this.sub = sub;
        this.name = name;
        this.givenName = givenName;
        this.familyName = familyName;
        this.picture = picture;
        this.email = email;
        this.emailVerified = emailVerified;
        this.locale = locale;
    }
}
