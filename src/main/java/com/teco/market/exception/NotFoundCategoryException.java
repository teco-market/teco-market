package com.teco.market.exception;

public class NotFoundCategoryException extends NotFoundException {
    public NotFoundCategoryException() {
        super(ErrorType.NOT_FOUND_CATEGORY.getCode(), ErrorType.NOT_FOUND_CATEGORY.getMessage());
    }
}
