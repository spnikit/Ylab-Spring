package com.spnikit.ylabcourse.request.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class PlayerRequest {
    @NotBlank(message = "Player's name can't be blank")
    @NotNull(message = "Player's name can't be null")
    private String name;

    @NotNull(message="Player must pick his symbol to play the game, X or O")
    @NotBlank(message="Player must pick his symbol to play the game, X or O")
    @Pattern(regexp = "[XxOo]", message = "Symbols can be X or O")
    private String symbol;
}

//todo: test manually win 1st player, win 2nd player, draw with gameplay upload after each game and resume via new game
//todo: write unit tests for player registration