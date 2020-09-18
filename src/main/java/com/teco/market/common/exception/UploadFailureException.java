package com.teco.market.common.exception;

public class UploadFailureException extends BusinessException {
    public UploadFailureException() {
        super(ErrorType.INVALID_FILE);
    }
}
