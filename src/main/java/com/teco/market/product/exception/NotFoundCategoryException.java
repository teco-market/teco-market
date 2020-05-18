package com.teco.market.product.exception;

public class NotFoundCategoryException extends RuntimeException{
    public NotFoundCategoryException(String message) {
        super(message);
    }
}
