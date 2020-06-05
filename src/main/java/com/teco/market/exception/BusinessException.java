package com.teco.market.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private final String code;
    private final String message;

    public BusinessException() {
        this.code = ErrorType.BUSINESS.getCode();
        this.message = ErrorType.BUSINESS.getMessage();
    }
}
