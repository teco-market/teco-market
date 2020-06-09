package com.teco.market.exception.invalid;

import com.teco.market.exception.ErrorType;
import lombok.Getter;

@Getter
public class InvalidJwtTokenException extends InvalidException {
    public InvalidJwtTokenException() {
        super(ErrorType.INVALID_TOKEN);
    }
}
