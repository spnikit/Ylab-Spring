package com.spnikit.ylabcourse.db.entities;


import com.spnikit.ylabcourse.game.model.Player;
import com.spnikit.ylabcourse.game.model.Token;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "gameplays")
public class GameplayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player1_id", referencedColumnName = "id")
    private PlayerEntity player1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player2_id", referencedColumnName = "id")
    private PlayerEntity player2;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id", referencedColumnName = "id")
    private PlayerEntity result;

    @OneToMany(mappedBy = "gameplay", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StepEntity> steps;


    protected GameplayEntity() {
    }

    public GameplayEntity(String player1,
                          String player2,
                          String result) {
        this.player1 = new PlayerEntity(player1, Token.X);
        this.player2 = new PlayerEntity(player2, Token.O);

        var token = result != null ?
                result.equalsIgnoreCase(player1) ? Token.X : Token.O
                : null;
        this.result = new PlayerEntity(result, token);
    }


    public Long getId() {
        return id;
    }

    public GameplayEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public PlayerEntity getPlayer1() {
        return player1;
    }

    public GameplayEntity setPlayer1(PlayerEntity player1) {
        this.player1 = player1;
        return this;
    }

    public PlayerEntity getPlayer2() {
        return player2;
    }

    public GameplayEntity setPlayer2(PlayerEntity player2) {
        this.player2 = player2;
        return this;
    }

    public PlayerEntity getResult() {
        return result;
    }

    public GameplayEntity setResult(PlayerEntity result) {
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
