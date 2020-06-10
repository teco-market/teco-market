package com.teco.market.common.exception.notfound;

import com.teco.market.common.exception.ErrorType;

public class NotFoundGenerationException extends NotFoundException {
    public NotFoundGenerationException() {
        super(ErrorType.NOT_FOUND_GENERATION);
    }
}
