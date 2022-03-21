package com.spnikit.ylabcourse.fileuploader.ylabcourse.exeption;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleFileEmptyException(IllegalArgumentException exception){
        log.error("Exception when uploading file " + exception.getMessage());

        var errorMessage = new ErrorMessage(Objects.requireNonNull(exception.getLocalizedMessage()),
                LocalDateTime.now().withNano(0));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
