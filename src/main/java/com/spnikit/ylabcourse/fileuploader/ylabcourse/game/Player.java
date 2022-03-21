package com.spnikit.ylabcourse.fileuploader.ylabcourse.game;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Objects;

class Player {

    private int id;
    private String name;
    private int numberOfMoves = 0;
    private Token token;

    public Player() {
    }

    public Player(String name, PlayerNumber playerNumber) {
        this.name = name;
        this.token = playerNumber == PlayerNumber.ONE ? Token.X : Token.O;
        this.id = this.token == Token.X ? 1 : 2;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public void makeMove() {
        this.numberOfMoves += 1;
    }

    @JsonIgnore
    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    @JsonGetter("_id")
    public int getId() {
        return id;
    }

    @JsonGetter("_symbol")
    public Token getToken() {
        return token;
    }

    @JsonGetter("_name")
    public String getName() {
        return name;
    }

    @JsonSetter("_id")
    public void setId(int id) {
        this.id = id;
    }
    @JsonSetter("_name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonSetter("_symbol")
    public void setToken(Token token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", token=" + token +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name) && token == player.token;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, token);
    }
}
