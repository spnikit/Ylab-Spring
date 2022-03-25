package com.spnikit.ylabcourse.fileuploader.ylabcourse.request.model;



/*
* "xCoord" : 0,
          "yCoord" : 0,
          "_num" : 1,
          "_playerId" : "1"
*
* */

import lombok.Data;
import lombok.Setter;

@Data
public class Move {
    private int pointX;
    private int pointY;
    private int moveNumber;
    private int playerId;

}
