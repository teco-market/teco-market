package com.teco.market.exception.invalid;

import com.teco.market.exception.BusinessException;
import com.teco.market.exception.ErrorType;
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
