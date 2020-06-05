package com.teco.market.exception;

import lombok.Getter;

@Getter
public class InvalidJwtTokenException extends InvalidException {
    public InvalidJwtTokenException() {
        super(ErrorType.INVALID_TOKEN.getCode(), ErrorType.INVALID_TOKEN.getMessage());
    }
}
