package com.teco.market.common.exception.notfound;

import com.teco.market.common.exception.ErrorType;

public class NotFoundCommentException extends NotFoundException {
    public NotFoundCommentException() {
        super(ErrorType.NOT_FOUND_COMMENT);
    }
}
