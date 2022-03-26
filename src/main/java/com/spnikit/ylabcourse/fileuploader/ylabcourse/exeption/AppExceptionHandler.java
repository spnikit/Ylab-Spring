package com.spnikit.ylabcourse.fileuploader.ylabcourse.exeption;


import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAnyException(Exception exception) {
        log.error("Exception when uploading file " + exception.getMessage());

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

        var errorMessage = ex.getFieldError().getDefaultMessage();

        if (errorMessage == null) {
            errorMessage = ex.toString();
        }

        return ResponseEntity.badRequest().body(errorMessage);
    }
}
