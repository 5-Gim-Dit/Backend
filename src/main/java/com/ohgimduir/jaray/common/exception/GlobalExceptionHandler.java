package com.ohgimduir.jaray.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    protected ErrorResponse handleCustomException(CustomException e) {
        log.error("CustomException message : " + e.getMessage());

        return ErrorResponse.of(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    protected ErrorResponse handleNullPointerException(NullPointerException e) {
        log.error("NullPointerException message : {}", e.getMessage());

        return ErrorResponse.of(500, "Internal Server exception occurred");
    }

    @ExceptionHandler(value = Exception.class)
    protected ErrorResponse handleException(Exception e) {
        log.error("Exception class : {}", e.getClass().getSimpleName());
        log.error("Exception message : {}", e.getMessage());

        return ErrorResponse.of(500, "Uncaught exception occurred");
    }

}