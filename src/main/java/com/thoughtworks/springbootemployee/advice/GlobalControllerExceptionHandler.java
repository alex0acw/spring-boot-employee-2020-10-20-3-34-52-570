package com.thoughtworks.springbootemployee.advice;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
//    @ResponseStatus(
//            value = HttpStatus.NOT_FOUND,
//            reason = "Resources not found")  // 409
//    @ExceptionHandler(NoSuchElementException.class)
//    public void notFound() {
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleBadRequest(IllegalArgumentException exception) {
        return new ErrorResponse(exception.getMessage(),HttpStatus.BAD_REQUEST.name());
    }
}
