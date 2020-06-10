package com.teco.market.common.exception.invalid;

import com.teco.market.common.exception.ErrorType;
import lombok.Getter;

@Getter
public class InvalidMultiFileException extends InvalidException {
    public InvalidMultiFileException() {
        super(ErrorType.INVALID_IMAGE);
    }
}
