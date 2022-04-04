package com.spnikit.ylabcourse.exсeption;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAnyException(Exception exception) {

        var errorMessageDescription = exception.getLocalizedMessage();

        if (errorMessageDescription == null) {
            errorMessageDescription = exception.toString();
        }

        var errorMessage = new ErrorMessage(errorMessageDescription,
                LocalDateTime.now().withNano(0));

        return ResponseEntity.badRequest().body(errorMessage);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var errorMessageDescription = ex.getFieldError().getDefaultMessage();

        if (errorMessageDescription == null) {
            errorMessageDescription = ex.toString();
        }

        var errorMessage = new ErrorMessage(errorMessageDescription,
                LocalDateTime.now().withNano(0));

        return ResponseEntity.badRequest().body(errorMessage);
    }
}
