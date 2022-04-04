package com.spnikit.ylabcourse;

import com.spnikit.ylabcourse.entities.GameplayEntity;
import com.spnikit.ylabcourse.entities.StepEntity;
import com.spnikit.ylabcourse.game.Gameplay;
import com.spnikit.ylabcourse.game.Player;
import com.spnikit.ylabcourse.game.PlayerNumber;
import com.spnikit.ylabcourse.game.Step;
import com.spnikit.ylabcourse.shared.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UtilsTest {

    @Test
    public void convertFromNumpadToXYCoord(){
        var result = Utils.convertNumpadValuesToXYCoordinates(1);

        Assertions.assertArrayEquals(new int[]{0, 0}, result);

        Assertions.assertThrows(IllegalArgumentException.class, () ->Utils.convertNumpadValuesToXYCoordinates(-1));
    }

    @Test
    public void shouldConvertFromDBEntityToGameplay(){
        var entityToConvert = new GameplayEntity("Sasha", "Serezha", "Serezha");
        var entitySteps = List.of(
                new StepEntity(0, 0, 1,1, entityToConvert),
                new StepEntity(1, 1, 2,2, entityToConvert),
                new StepEntity(2, 2, 3,1, entityToConvert),
                new StepEntity(3, 3, 4,2, entityToConvert)
        );
        entityToConvert.setSteps(entitySteps);
        var steps = List.of(
                new Step(0, 0, 1,1),
                new Step(1, 1, 2,2),
                new Step(2, 2, 3,1),
                new Step(3, 3, 4,2)
        );
        var gameplayToExpect = new Gameplay(
                new Player("Sasha", PlayerNumber.ONE),
                new Player("Serezha", PlayerNumber.TWO),
                new Player("Serezha", PlayerNumber.TWO),
                steps
        );

        Assertions.assertEquals(gameplayToExpect, Utils.convertDBEntityToGameplay(entityToConvert));
    }

    @Test
    public void shouldConvertDBEntityToStep(){
        var gameplayEntityToConvert = new GameplayEntity("Sasha", "Serezha", "Serezha");

        var stepEntityToConvert = new StepEntity(0, 0, 1,1, gameplayEntityToConvert);

        var stepToExpect = new Step(1, 0, 0,1);


        Assertions.assertEquals(stepToExpect, Utils.convertDBEntityToStep(stepEntityToConvert));

    }
}
