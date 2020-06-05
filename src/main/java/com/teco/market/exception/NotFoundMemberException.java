package com.teco.market.exception;

import lombok.Getter;

@Getter
public class NotFoundMemberException extends NotFoundException {
    public NotFoundMemberException() {
        super(ErrorType.NOT_FOUND_MEMBER.getCode(), ErrorType.NOT_FOUND_MEMBER.getMessage());
    }
}
