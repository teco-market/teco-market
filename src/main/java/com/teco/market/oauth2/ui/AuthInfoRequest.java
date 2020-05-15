package com.teco.market.oauth2.ui;

import lombok.Getter;

@Getter
public class AuthInfoRequest {
    private String type;
    private String code;

    public AuthInfoRequest() {

    }
}
