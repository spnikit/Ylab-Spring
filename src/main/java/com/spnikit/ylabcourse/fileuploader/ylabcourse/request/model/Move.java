package com.spnikit.ylabcourse.fileuploader.ylabcourse.request.model;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Move {
    @NotNull
    @Min(value = 1, message = "cell number must be from 1 to 9")
    @Max(value = 9, message = "cell number must be from 1 to 9")
    private int cellNumber;

    @NotNull
    @Min(value = 1, message = "player Id must be either 1 or 2")
    @Max(value = 2, message = "player Id must be either 1 or 2")
    private int playerId;

}
