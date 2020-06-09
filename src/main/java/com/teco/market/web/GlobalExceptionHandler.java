package com.teco.market.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.teco.market.exception.BusinessException;
import com.teco.market.exception.ErrorResponse;
import com.teco.market.exception.ErrorType;
import com.teco.market.exception.invalid.InvalidAuthenticationException;
import com.teco.market.exception.invalid.InvalidException;
import com.teco.market.exception.invalid.InvalidJwtTokenException;
import com.teco.market.exception.notfound.NotFoundCommentException;
import com.teco.market.exception.notfound.NotFoundException;
import com.teco.market.exception.notfound.NotFoundMemberException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessException(BusinessException e) {
        logger.error("%s 예외가 발생하였습니다. %s", e.getErrorType().getCode(), e.getErrorType().getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.from(e.getErrorType()));
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<ErrorResponse> invalidException(InvalidException e) {
        logger.error("%s 예외가 발생하였습니다. %s", e.getErrorType().getCode(), e.getErrorType().getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse.from(e.getErrorType()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException e) {
        logger.error("%s 예외가 발생하였습니다. %s", e.getErrorType().getCode(), e.getErrorType().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.from(e.getErrorType()));
    }
}
