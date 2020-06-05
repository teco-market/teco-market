package com.teco.market.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidException extends BusinessException {
    private final String code;
    private final String message;

    public InvalidException() {
        this(ErrorType.INVALID.getCode(), ErrorType.INVALID.getMessage());
    }
}
