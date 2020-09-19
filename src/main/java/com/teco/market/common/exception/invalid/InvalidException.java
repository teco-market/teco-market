package com.teco.market.common.exception.invalid;

import com.teco.market.common.exception.BusinessException;
import com.teco.market.common.exception.ErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InvalidException extends BusinessException {
    private final ErrorType errorType;

    public InvalidException() {
        this(ErrorType.INVALID);
    }
}
