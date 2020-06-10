package com.teco.market.common.exception.invalid;

import com.teco.market.common.exception.ErrorType;

public class InvalidWriterException extends InvalidException {
    public InvalidWriterException() {
        super(ErrorType.INVALID_WRITER);
    }
}
