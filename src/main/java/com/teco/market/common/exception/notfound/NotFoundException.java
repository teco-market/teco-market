package com.teco.market.common.exception.notfound;

import com.teco.market.common.exception.BusinessException;
import com.teco.market.common.exception.ErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NotFoundException extends BusinessException {
    private final ErrorType errorType;

    public NotFoundException() {
        this(ErrorType.NOT_FOUND);
    }
}
