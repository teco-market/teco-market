package com.teco.market.oauth2.ui.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.teco.market.exception.BusinessException;
import com.teco.market.exception.ErrorResponse;
import com.teco.market.exception.ErrorType;
import com.teco.market.exception.InvalidAuthenticationException;
import com.teco.market.exception.InvalidException;
import com.teco.market.exception.InvalidJwtTokenException;
import com.teco.market.exception.NotFoundException;
import com.teco.market.exception.NotFoundMemberException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessException(BusinessException e) {
        logger.error("%s 예외가 발생하였습니다. %s", e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.from(ErrorType.BUSINESS));
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<ErrorResponse> invalidException(InvalidException e) {
        logger.error("%s 예외가 발생하였습니다. %s", e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.from(ErrorType.INVALID));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException e) {
        logger.error("%s 예외가 발생하였습니다. %s", e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.from(ErrorType.NOT_FOUND));
    }

    @ExceptionHandler(InvalidAuthenticationException.class)
    public ResponseEntity<ErrorResponse> invalidAuthenticationException(InvalidAuthenticationException e) {
        logger.error("%s 예외가 발생하였습니다. %s", e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse.from(ErrorType.VALID_TOKEN_BUT_NOT_FOUND_MEMBER));
    }

    @ExceptionHandler(InvalidJwtTokenException.class)
    public ResponseEntity<ErrorResponse> invalidJwtTokenException(InvalidJwtTokenException e) {
        logger.error("%s 예외가 발생하였습니다. %s", e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse.from(ErrorType.INVALID_TOKEN));
    }

    @ExceptionHandler(NotFoundMemberException.class)
    public ResponseEntity<ErrorResponse> notFoundMemberException(NotFoundMemberException e) {
        logger.error("%s 예외가 발생하였습니다. %s", e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.from(ErrorType.NOT_FOUND_MEMBER));
    }

}
