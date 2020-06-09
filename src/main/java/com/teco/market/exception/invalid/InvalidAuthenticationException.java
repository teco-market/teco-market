package com.teco.market.exception.invalid;

import com.teco.market.exception.ErrorType;

public class InvalidAuthenticationException extends InvalidException {
    public InvalidAuthenticationException() {
        super(ErrorType.VALID_TOKEN_BUT_NOT_FOUND_MEMBER.getCode(),
            ErrorType.VALID_TOKEN_BUT_NOT_FOUND_MEMBER.getMessage());
    }
}
