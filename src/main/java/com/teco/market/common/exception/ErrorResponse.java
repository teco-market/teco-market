package com.teco.market.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ErrorResponse {
    private final String code;
    private final String message;

    public static ErrorResponse from(ErrorType type) {
        return new ErrorResponse(type.getCode(), type.getMessage());
    }
}
