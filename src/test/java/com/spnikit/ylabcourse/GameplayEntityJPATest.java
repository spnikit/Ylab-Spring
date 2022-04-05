package com.spnikit.ylabcourse;


import com.spnikit.ylabcourse.db.entities.GameplayEntity;
import com.spnikit.ylabcourse.db.entities.StepEntity;
import com.spnikit.ylabcourse.db.repos.GameplayRepository;
import com.spnikit.ylabcourse.db.repos.StepRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class GameplayEntityJPATest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private GameplayRepository gameplayRepository;

    @Autowired
    private StepRepository stepRepository;


    @Test
    public void shouldStoreAGameplayEntity() {
        GameplayEntity entity = gameplayRepository.save(new GameplayEntity("Serge", "Pete", null));
        assertThat(entity).hasFieldOrPropertyWithValue("player1", "Serge");
        assertThat(entity).hasFieldOrPropertyWithValue("player2", "Pete");
        assertThat(entity).hasFieldOrPropertyWithValue("result", null);
    }

    @Test
    public void shouldStoreAStepEntity() {
        var gameplay = new GameplayEntity("Serge", "Pete", null);
        gameplayRepository.save(gameplay);

        StepEntity entity = stepRepository.save(new StepEntity(
                1,
                2,
                1,
                1,
                gameplay
        ));
        assertThat(entity).hasFieldOrPropertyWithValue("pointX", 1);
        assertThat(entity).hasFieldOrPropertyWithValue("pointY", 2);
        assertThat(entity).hasFieldOrPropertyWithValue("number", 1);
        assertThat(entity).hasFieldOrPropertyWithValue("playerId", 1);
    }

    @Test
    public void shouldFindAllGameplayEntities() {
        var gameplay1 = new GameplayEntity("Serge", "Pete", null);
        testEntityManager.persist(gameplay1);

        var gameplay2 = new GameplayEntity("Serge", "Pete", "Serge");
        testEntityManager.persist(gameplay2);

        var gameplay3 = new GameplayEntity("Serge", "Pete", "Pete");
        testEntityManager.persist(gameplay3);

        Iterable<GameplayEntity> all = gameplayRepository.findAll();

        assertThat(all).hasSize(3).contains(gameplay1, gameplay2, gameplay3);


    }

    @Test
    public void shouldDeleteAllGameplayEntities() {
        var gameplay1 = new GameplayEntity("Serge", "Pete", null);
        testEntityManager.persist(gameplay1);

        var gameplay2 = new GameplayEntity("Serge", "Pete", "Serge");
        testEntityManager.persist(gameplay2);

        var gameplay3 = new GameplayEntity("Serge", "Pete", "Pete");
        testEntityManager.persist(gameplay3);

        gameplayRepository.deleteAll();

        Assertions.assertThat(gameplayRepository.findAll()).isEmpty();
    }

    @Test
    public void shouldDeleteAllGameplayAndStepEntities() {
        var gameplay = new GameplayEntity("Serge", "Pete", null);

        var step = new StepEntity(
                1,
                2,
                1,
                1,
                gameplay
        );
        gameplay.setSteps(List.of(step));

        testEntityManager.persist(gameplay);

        gameplayRepository.deleteAll();

        Assertions.assertThat(gameplayRepository.findAll()).isEmpty();
        Assertions.assertThat(stepRepository.findAll()).isEmpty();


    }

}
