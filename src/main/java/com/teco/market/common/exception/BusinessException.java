package com.teco.market.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private final ErrorType errorType;

    public BusinessException() {
        this.errorType = ErrorType.BUSINESS;
    }
}
