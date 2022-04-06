package com.spnikit.ylabcourse.db.entities;


import com.spnikit.ylabcourse.game.model.Token;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "players")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Token token;


    public PlayerEntity(){}

    public PlayerEntity(String name, Token token){
        this.name = name;
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerEntity that = (PlayerEntity) o;
        return Objects.equals(name, that.name) && token == that.token;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, token);
    }
}
