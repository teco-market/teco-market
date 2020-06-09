package com.teco.market.exception.invalid;

import com.teco.market.exception.BusinessException;
import com.teco.market.exception.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidException extends BusinessException {
    private final ErrorType errorType;

    public InvalidException() {
        this(ErrorType.INVALID);
    }
}
