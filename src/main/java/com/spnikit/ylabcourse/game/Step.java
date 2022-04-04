package com.spnikit.ylabcourse.game;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return number == step.number && xCoord == step.xCoord && yCoord == step.yCoord && playerNumber == step.playerNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, xCoord, yCoord, playerNumber);
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
