package com.teco.market.exception;

import com.teco.market.exception.notfound.NotFoundException;

public class NotFoundCommentException extends NotFoundException {
    public NotFoundCommentException() {
        super(ErrorType.NOT_FOUND_COMMENT.getCode(), ErrorType.NOT_FOUND_COMMENT.getMessage());
    }
}
