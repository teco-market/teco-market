package com.teco.market.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {
    private final ErrorType errorType;

    public BusinessException() {
        this.errorType = ErrorType.BUSINESS;
    }
}
