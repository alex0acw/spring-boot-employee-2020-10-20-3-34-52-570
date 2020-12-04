package com.thoughtworks.springbootemployee.controller;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Resources not found")  // 409
    @ExceptionHandler(NoSuchElementException.class)
    public void notFound() {
    }

    @ResponseStatus(
            value = HttpStatus.UNPROCESSABLE_ENTITY,
            reason = "Invalid input format")  // 409
    @ExceptionHandler(ConversionFailedException.class)
    public void unprocessed() {
    }
}
