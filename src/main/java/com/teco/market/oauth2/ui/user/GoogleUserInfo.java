package com.teco.market.oauth2.ui.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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

    public GoogleUserInfo() {

    }

    public String fullName() {
        return String.format("%s %s", familyName, givenName);
    }
}
