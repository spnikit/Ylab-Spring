package com.spnikit.ylabcourse.fileuploader.ylabcourse.response.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewGameResp {
    private LocalDateTime date;
    private String message;

    public NewGameResp(String message){
        date = LocalDateTime.now().withNano(0);
        this.message = message;
    }
}
