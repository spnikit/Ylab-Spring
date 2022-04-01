package com.spnikit.ylabcourse.entities;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "steps")
public class StepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int pointX;
    private int pointY;
    private int number;
    private int playerId;

    @ManyToOne
    @JoinColumn(name = "gameplay_id", nullable = false)
    private GameplayEntity gameplay;


    protected StepEntity() {
    }

    public StepEntity(int pointX,
                      int pointY,
                      int number,
                      int playerId,
                      GameplayEntity gameplay) {
        this.pointX = pointX;
        this.pointY = pointY;
        this.number = number;
        this.playerId = playerId;
        this.gameplay = gameplay;
    }


    public Long getId() {
        return id;
    }

    public StepEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public int getPointX() {
        return pointX;
    }

    public StepEntity setPointX(int pointX) {
        this.pointX = pointX;
        return this;
    }

    public int getPointY() {
        return pointY;
    }

    public StepEntity setPointY(int pointY) {
        this.pointY = pointY;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public StepEntity setNumber(int number) {
        this.number = number;
        return this;
    }

    public int getPlayerId() {
        return playerId;
    }

    public StepEntity setPlayerId(int playerId) {
        this.playerId = playerId;
        return this;
    }

    public GameplayEntity getGameplay() {
        return gameplay;
    }

    public StepEntity setGameplay(GameplayEntity gameplay) {
        this.gameplay = gameplay;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StepEntity that = (StepEntity) o;
        return pointX == that.pointX && pointY == that.pointY && number == that.number && playerId == that.playerId && id.equals(that.id) && gameplay.equals(that.gameplay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pointX, pointY, number, playerId, gameplay);
    }

    @Override
    public String toString() {
        return "StepEntity{" +
                "id=" + id +
                ", pointX=" + pointX +
                ", pointY=" + pointY +
                ", number=" + number +
                ", playerId=" + playerId +
                ", gameplay=" + gameplay +
                '}';
    }
}
