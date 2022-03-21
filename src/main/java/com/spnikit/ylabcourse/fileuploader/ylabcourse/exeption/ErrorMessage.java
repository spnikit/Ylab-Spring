package com.spnikit.ylabcourse.fileuploader.ylabcourse.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String message;
    private LocalDateTime localDate;
}
