package com.teco.market.exception.notfound;

import com.teco.market.exception.ErrorType;
import lombok.Getter;

@Getter
public class NotFoundMemberException extends NotFoundException {
    public NotFoundMemberException() {
        super(ErrorType.NOT_FOUND_MEMBER.getCode(), ErrorType.NOT_FOUND_MEMBER.getMessage());
    }
}
