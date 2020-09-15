package com.teco.market.common.exception;

public class AuthorizationException extends BusinessException {
    public AuthorizationException() {
        super(ErrorType.UNAUTHORIZED_MEMBER);
    }
}
