package com.spnikit.ylabcourse.fileuploader.ylabcourse.request.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class PlayerRequest {
    @NotBlank(message = "Player's name can't be blank")
    @NotNull(message = "Player's name can't be null")
    private String name;
    @NotNull(message="Player must pick his symbol to play the game, X or O")
    @NotBlank(message="Player must pick his symbol to play the game, X or O")
    @Pattern(regexp = "[XxOo]", message = "Symbols can be X or O")
    private String symbol;
}
