package com.teco.market.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.teco.market.common.exception.BusinessException;
import com.teco.market.common.exception.ErrorResponse;
import com.teco.market.common.exception.invalid.InvalidException;
import com.teco.market.common.exception.notfound.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> businessException(BusinessException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.from(e.getErrorType()));
    }

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<ErrorResponse> invalidException(InvalidException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse.from(e.getErrorType()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.from(e.getErrorType()));
    }
}
