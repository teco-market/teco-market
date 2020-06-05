package com.teco.market.exception;

public class NotFoundGenerationException extends NotFoundException {
    public NotFoundGenerationException() {
        super(ErrorType.NOT_FOUND_GENERATION.getCode(), ErrorType.NOT_FOUND_GENERATION.getMessage());
    }
}
