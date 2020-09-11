package com.teco.market.infra.oauth2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoTokenResponse {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private int expiresIn;
    private int refreshTokenExpiresIn;
    private String scope;
}
