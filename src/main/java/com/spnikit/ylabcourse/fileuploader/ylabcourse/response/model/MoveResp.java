package com.spnikit.ylabcourse.fileuploader.ylabcourse.response.model;


import lombok.Data;

@Data
public class MoveResp {
    private String[] board;
    private int playerNextMoveId;
    private Integer winnerId;
    private boolean draw;

}
