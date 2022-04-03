package com.spnikit.ylabcourse;


import com.spnikit.ylabcourse.entities.GameplayEntity;
import com.spnikit.ylabcourse.entities.StepEntity;
import com.spnikit.ylabcourse.repos.GameplayRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameplayEntityTest {

    @Autowired
    private GameplayRepository gameplayRepository;

    @Test
    @Transactional
    public void getGameplayById() {
        var gameplay = gameplayRepository.findById(1l);

        var steps = gameplay.stream()
                .map(GameplayEntity::getSteps)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        assertAll(() -> {

            assertInstanceOf(GameplayEntity.class, gameplay.get());
            assertFalse(steps.isEmpty());
            assertInstanceOf(StepEntity.class, steps.get(0));

        });

    }

}
