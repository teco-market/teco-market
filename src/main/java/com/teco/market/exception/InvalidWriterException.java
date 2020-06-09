package com.teco.market.exception;

import com.teco.market.exception.invalid.InvalidException;

public class InvalidWriterException extends InvalidException {
    public InvalidWriterException() {
        super(ErrorType.INVALID_WRITER.getCode(), ErrorType.INVALID_WRITER.getMessage());
    }
}
