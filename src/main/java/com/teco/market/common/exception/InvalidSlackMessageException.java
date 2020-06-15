package com.teco.market.common.exception;

import com.teco.market.common.exception.invalid.InvalidException;

public class InvalidSlackMessageException extends InvalidException {
    public InvalidSlackMessageException() {
        super(ErrorType.INVALID_SLACK);
    }
}
