package com.spnikit.ylabcourse.exсeption;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String message;
    private LocalDateTime localDate;
}
