package com.teco.market.exception.notfound;

import com.teco.market.exception.BusinessException;
import com.teco.market.exception.ErrorType;
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
