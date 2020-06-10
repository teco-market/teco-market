package com.teco.market.common.exception.notfound;

import com.teco.market.common.exception.ErrorType;

public class NotFoundCategoryException extends NotFoundException {
    public NotFoundCategoryException() {
        super(ErrorType.NOT_FOUND_CATEGORY);
    }
}
