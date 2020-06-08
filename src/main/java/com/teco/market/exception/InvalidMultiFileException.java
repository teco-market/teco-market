package com.teco.market.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class InvalidMultiFileException extends InvalidException {
    public InvalidMultiFileException() {
        super(ErrorType.INVALID_IMAGE.getCode(), ErrorType.INVALID_IMAGE.getMessage());
    }
}
