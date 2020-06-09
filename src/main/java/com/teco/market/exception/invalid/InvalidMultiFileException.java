package com.teco.market.exception.invalid;

import com.teco.market.exception.ErrorType;
import lombok.Getter;

@Getter
public class InvalidMultiFileException extends InvalidException {
    public InvalidMultiFileException() {
        super(ErrorType.INVALID_IMAGE.getCode(), ErrorType.INVALID_IMAGE.getMessage());
    }
}
