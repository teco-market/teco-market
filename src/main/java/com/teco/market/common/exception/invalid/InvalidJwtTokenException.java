package com.teco.market.common.exception.invalid;

import com.teco.market.common.exception.ErrorType;
import lombok.Getter;

@Getter
public class InvalidJwtTokenException extends InvalidException {
    public InvalidJwtTokenException() {
        super(ErrorType.INVALID_TOKEN);
    }
}
