package com.spnikit.ylabcourse.game;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Step{

    private int number;
    private int xCoord;
    private int yCoord;
    private int playerNumber;


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
    public int getPlayerNumber() {
        return playerNumber;
    }

    @JsonSetter("_playerId")
    public void setPlayerNumber(int playerNumber) {
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
