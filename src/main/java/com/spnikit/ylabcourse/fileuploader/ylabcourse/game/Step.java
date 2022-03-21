package com.spnikit.ylabcourse.fileuploader.ylabcourse.game;

//public record Step(int number, int xCoord, int yCoord, String playerNumber) {
//}

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Step{

    private int number;
    private int xCoord;
    private int yCoord;
    private String playerNumber;

    public Step(){}

    public Step(int number, int xCoord, int yCoord, String playerNumber) {
        this.number = number;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.playerNumber = playerNumber;
    }

    @JsonGetter("_num")
    public int getNumber() {
        return number;
    }

    @JsonSetter("_num")
    public void setNumber(int number) {
        this.number = number;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    @JsonGetter("_playerId")
    public String getPlayerNumber() {
        return playerNumber;
    }

    @JsonSetter("_playerId")
    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }


    @Override
    public String toString() {
        return "Step{" +
                "number=" + number +
                ", xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                ", playerNumber='" + playerNumber + '\'' +
                '}';
    }
}
