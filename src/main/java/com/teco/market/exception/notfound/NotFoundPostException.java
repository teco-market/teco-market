package com.teco.market.exception.notfound;

import com.teco.market.exception.ErrorType;

public class NotFoundPostException extends NotFoundException {
    public NotFoundPostException() {
        super(ErrorType.NOT_FOUND_POST.getCode(), ErrorType.NOT_FOUND_POST.getMessage());
    }
}
