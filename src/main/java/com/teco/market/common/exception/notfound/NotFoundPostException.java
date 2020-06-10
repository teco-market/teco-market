package com.teco.market.common.exception.notfound;

import com.teco.market.common.exception.ErrorType;

public class NotFoundPostException extends NotFoundException {
    public NotFoundPostException() {
        super(ErrorType.NOT_FOUND_POST);
    }
}
