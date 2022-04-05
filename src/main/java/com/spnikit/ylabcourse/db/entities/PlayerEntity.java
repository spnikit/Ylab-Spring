package com.spnikit.ylabcourse.db.entities;


import com.spnikit.ylabcourse.game.model.Token;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}
