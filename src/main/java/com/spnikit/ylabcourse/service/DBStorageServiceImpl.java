package com.spnikit.ylabcourse.service;

import com.spnikit.ylabcourse.db.entities.GameplayEntity;
import com.spnikit.ylabcourse.db.entities.StepEntity;
import com.spnikit.ylabcourse.game.model.Gameplay;
import com.spnikit.ylabcourse.db.repos.GameplayRepository;
import com.spnikit.ylabcourse.db.repos.StepRepository;
import com.spnikit.ylabcourse.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    public List<Gameplay> getAll() {

        var gameplayEntities = this.gameplayRepository.findAll();

        var gameplays = StreamSupport
                .stream(gameplayEntities.spliterator(), false)
                .map(Utils::convertDBEntityToGameplay)
                .collect(Collectors.toList());

        return gameplays;
    }

    @Override
    public Optional<Gameplay> getLast() {
        var gameplayEntity = this.gameplayRepository.findTopByOrderByIdDesc();

        if(gameplayEntity.isEmpty()){
            return Optional.empty();
        }

        var gameplay = Utils.convertDBEntityToGameplay(gameplayEntity.get());

        return Optional.of(gameplay);
    }

    @Override
    public void deleteAll() {
        this.gameplayRepository.deleteAll();
    }
}
