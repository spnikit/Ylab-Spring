package com.spnikit.ylabcourse.service;

import com.spnikit.ylabcourse.entities.GameplayEntity;
import com.spnikit.ylabcourse.entities.StepEntity;
import com.spnikit.ylabcourse.game.Gameplay;
import com.spnikit.ylabcourse.repos.GameplayRepository;
import com.spnikit.ylabcourse.repos.StepRepository;
import com.spnikit.ylabcourse.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DBStorageServiceImpl implements DBStorageService<Gameplay> {


    private final GameplayRepository gameplayRepository;
    private final StepRepository stepRepository;

    @Autowired
    public DBStorageServiceImpl(GameplayRepository gameplayRepository, StepRepository stepRepository) {
        this.gameplayRepository = gameplayRepository;
        this.stepRepository = stepRepository;
    }


    @Override
    @Transactional
    public void save(Gameplay gameplay) {
        Objects.requireNonNull(gameplay, "Gameplay object can't be null while saving in DB");

        var player1Name = gameplay.getPlayer1().getName();
        var player2Name = gameplay.getPlayer2().getName();
        var result = gameplay.getGameResult() != null ?
                gameplay.getGameResult().getName() : null;


        var gameplayEntityToSaveInDb = new GameplayEntity(player1Name, player2Name, result);

        this.gameplayRepository.save(gameplayEntityToSaveInDb);

        gameplay.getSteps().forEach(step -> {
            var stepEntityToSaveInDB = new StepEntity(
                    step.getxCoord(),
                    step.getyCoord(),
                    step.getNumber(),
                    step.getPlayerNumber(),
                    gameplayEntityToSaveInDb);

            this.stepRepository.save(stepEntityToSaveInDB);
        });
    }

    @Override
    public List<Gameplay> getGameplays() {

        var gameplayEntities = this.gameplayRepository.findAll();

        var gameplays = StreamSupport
                .stream(gameplayEntities.spliterator(), false)
                .map(Utils::convertDBEntityToGameplay)
                .collect(Collectors.toList());

        return gameplays;
    }

    @Override
    public Gameplay getLastGameplay() {
        var gameplayEntity = this.gameplayRepository.findTopByOrderByIdDesc();

        return Utils.convertDBEntityToGameplay(
                Objects.requireNonNull(gameplayEntity));
    }
}
