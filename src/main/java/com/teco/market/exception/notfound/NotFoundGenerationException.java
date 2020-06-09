package com.teco.market.exception.notfound;

import com.teco.market.exception.ErrorType;

public class NotFoundGenerationException extends NotFoundException {
    public NotFoundGenerationException() {
        super(ErrorType.NOT_FOUND_GENERATION);
    }
}
