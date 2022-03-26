package com.spnikit.ylabcourse.fileuploader.ylabcourse.response.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MoveResp {
    private String[] board;
    private int playerNextMoveId;
    private Integer winnerId;
    private boolean draw;

}
