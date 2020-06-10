package com.teco.market.common.exception.notfound;

import com.teco.market.common.exception.ErrorType;
import lombok.Getter;

@Getter
public class NotFoundMemberException extends NotFoundException {
    public NotFoundMemberException() {
        super(ErrorType.NOT_FOUND_MEMBER);
    }
}
