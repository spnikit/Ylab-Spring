package com.spnikit.ylabcourse.controller;


import com.spnikit.ylabcourse.game.Gameplay;
import com.spnikit.ylabcourse.game.Player;
import com.spnikit.ylabcourse.request.model.Move;
import com.spnikit.ylabcourse.request.model.PlayerRequest;
import com.spnikit.ylabcourse.response.model.MoveResp;
import com.spnikit.ylabcourse.response.model.NewGameResp;
import com.spnikit.ylabcourse.service.DBStorageService;
import com.spnikit.ylabcourse.service.PlayGameRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RequestMapping("/gameplay")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TicTacToeGameController {

    private final PlayGameRestService playGameRestService;
    private final DBStorageService<Gameplay> dbStorageService;

    @Autowired
    public TicTacToeGameController(PlayGameRestService playGameRestService,
                                   DBStorageService<Gameplay> dbStorageService) {
        this.playGameRestService = playGameRestService;
        this.dbStorageService = dbStorageService;
    }


    @GetMapping(value = "/new")
    public ResponseEntity<NewGameResp> restartGame() {

        playGameRestService.setNewGame();

        var response = new NewGameResp("New Game created!");

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/result")
    public List<Gameplay> getGameplay() {
        return this.dbStorageService.getGameplays();
    }

    @PostMapping(value = "/move", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MoveResp> processMove(@Valid @RequestBody Move move) {

        var responseBody = playGameRestService.makeMove(move);

        // if game is over save gameplay in DB
        if (responseBody.isDraw() || responseBody.getWinnerId() != null) {
            var gameplay = this.playGameRestService.getGameplay();
            this.dbStorageService.save(Objects.requireNonNull(gameplay));
        }

        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping("/register/player")
    public ResponseEntity<Player> registerPlayer(@Valid @RequestBody PlayerRequest player) {
        var playerRegistered = this.playGameRestService.registerPlayer(player);
        return ResponseEntity.ok(playerRegistered);
    }

}
