package com.spnikit.ylabcourse.entities;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "gameplays")
public class GameplayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String player1;
    private String player2;
    private String result;

    @OneToMany(mappedBy = "gameplay")
    private List<StepEntity> steps;



    protected GameplayEntity() {
    }

    public GameplayEntity(String player1,
                          String player2,
                          String result) {
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
    }



    public Long getId() {
        return id;
    }

    public GameplayEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPlayer1() {
        return player1;
    }

    public GameplayEntity setPlayer1(String player1) {
        this.player1 = player1;
        return this;
    }

    public String getPlayer2() {
        return player2;
    }

    public GameplayEntity setPlayer2(String player2) {
        this.player2 = player2;
        return this;
    }

    public String getResult() {
        return result;
    }

    public GameplayEntity setResult(String result) {
        this.result = result;
        return this;
    }

    public List<StepEntity> getSteps() {
        return steps;
    }

    public GameplayEntity setSteps(List<StepEntity> steps) {
        this.steps = steps;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameplayEntity that = (GameplayEntity) o;
        return id.equals(that.id) && Objects.equals(player1, that.player1) && Objects.equals(player2, that.player2) && Objects.equals(result, that.result) && steps.equals(that.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, player1, player2, result, steps);
    }

    @Override
    public String toString() {
        return "GameplayEntity{" +
                "id=" + id +
                ", player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", result='" + result + '\'' +
                ", steps=" + steps +
                '}';
    }
}
