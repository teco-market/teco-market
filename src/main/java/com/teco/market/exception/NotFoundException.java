package com.teco.market.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotFoundException extends BusinessException {
    private final String code;
    private final String message;

    public NotFoundException() {
        this(ErrorType.NOT_FOUND.getCode(), ErrorType.NOT_FOUND.getMessage());
    }
}
