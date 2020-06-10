package com.teco.market.common.exception.invalid;

import com.teco.market.common.exception.ErrorType;

public class InvalidAuthenticationException extends InvalidException {
    public InvalidAuthenticationException() {
        super(ErrorType.VALID_TOKEN_BUT_NOT_FOUND_MEMBER);
    }
}
