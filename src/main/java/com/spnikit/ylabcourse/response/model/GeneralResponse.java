package com.spnikit.ylabcourse.response.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GeneralResponse {
    private LocalDateTime date;
    private String message;

    public GeneralResponse(String message){
        date = LocalDateTime.now().withNano(0);
        this.message = message;
    }
}
