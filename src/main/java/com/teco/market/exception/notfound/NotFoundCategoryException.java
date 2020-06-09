package com.teco.market.exception.notfound;

import com.teco.market.exception.ErrorType;

public class NotFoundCategoryException extends NotFoundException {
    public NotFoundCategoryException() {
        super(ErrorType.NOT_FOUND_CATEGORY);
    }
}
