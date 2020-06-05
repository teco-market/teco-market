package com.teco.market.oauth2.ui;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthInfoRequest {
    private String type;
    private String code;
}
