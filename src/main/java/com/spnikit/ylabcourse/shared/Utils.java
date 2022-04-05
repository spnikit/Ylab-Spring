package com.spnikit.ylabcourse.shared;

import com.spnikit.ylabcourse.db.entities.GameplayEntity;
import com.spnikit.ylabcourse.db.entities.StepEntity;
import com.spnikit.ylabcourse.game.model.Gameplay;
import com.spnikit.ylabcourse.game.model.Player;
import com.spnikit.ylabcourse.game.model.PlayerNumber;
import com.spnikit.ylabcourse.game.model.Step;

import java.util.Objects;
import java.util.stream.Collectors;

public class Utils {

    public static int[] convertNumpadValuesToXYCoordinates(int numpadVal) {

        switch (numpadVal) {
            case 1 -> {
                return new int[]{0, 0};
            }
            case 2 -> {
                return new int[]{1, 0};
            }
            case 3 -> {
                return new int[]{2, 0};
            }
            case 4 -> {
                return new int[]{0, 1};
            }
            case 5 -> {
                return new int[]{1, 1};
            }
            case 6 -> {
                return new int[]{2, 1};
            }
            case 7 -> {
                return new int[]{0, 2};
            }
            case 8 -> {
                return new int[]{1, 2};
            }
            case 9 -> {
                return new int[]{2, 2};
            }
            default -> throw new IllegalArgumentException("value should be from 1 to 9");
        }
    }

    public static Gameplay convertDBEntityToGameplay(GameplayEntity entity) {
        Objects.requireNonNull(entity, "GameplayEntity can't be null while converting to Gameplay");

        var player1 = new Player(entity.getPlayer1().getName(), PlayerNumber.ONE);
        var player2 = new Player(entity.getPlayer2().getName(), PlayerNumber.TWO);
        Player gameResult = null;
        var result = entity.getResult() != null ?
                entity.getResult().getName():
                null;

        if (result != null) {
            if (result.equalsIgnoreCase(player1.getName())) {
                gameResult = player1;
            } else if (result.equalsIgnoreCase((player2.getName()))) {
                gameResult = player2;
            }
        }

        var steps = entity.getSteps().stream().map(Utils::convertDBEntityToStep)
                .collect(Collectors.toList());


        return new Gameplay(
                player1,
                player2,
                gameResult,
                steps
        );
    }


    public static Step convertDBEntityToStep(StepEntity entity) {

        Objects.requireNonNull(entity, "StepEntity can't be null while converting to Step");


        return new Step(
                entity.getNumber(),
                entity.getPointX(),
                entity.getPointY(),
                entity.getPlayerId()
        );
    }
}
