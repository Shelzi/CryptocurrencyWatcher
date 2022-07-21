package com.shelzi.cryptocurrencywatcher.handler;

import com.shelzi.cryptocurrencywatcher.exception.ServiceException;
import com.shelzi.cryptocurrencywatcher.response.ExceptionResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<ExceptionResponse> handleRuntimeExceptions(ServiceException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getDetail() + StringUtils.SPACE + Thread.getAllStackTraces());
        return new ResponseEntity<>(exceptionResponse, status);
    }
}