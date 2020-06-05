package com.teco.market.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ErrorResponse {
    private final String code;
    private final String message;

    public static ErrorResponse from(ErrorType type) {
        return new ErrorResponse(type.getCode(), type.getMessage());
    }
}
