package com.spnikit.ylabcourse.fileuploader.ylabcourse.controller;


import com.spnikit.ylabcourse.fileuploader.ylabcourse.request.model.Move;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.response.model.NewGameResp;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.service.PlayGameRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/gameplay")
@RestController
public class TicTacToeGameController {

    private final PlayGameRestService playGameRestService;

    @Autowired
    public TicTacToeGameController(PlayGameRestService playGameRestService) {
        this.playGameRestService = playGameRestService;
    }

    @PostMapping(value = "/move", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> processMove(@Valid @RequestBody Move move){

        var responseBody = playGameRestService.makeMove(move);

        return ResponseEntity.ok().body(responseBody);
    }

    @GetMapping(value = "/new")
    public ResponseEntity<NewGameResp> restartGame(){

        playGameRestService.setNewGame();

        var response = new NewGameResp("New Game created!");

        return ResponseEntity.ok().body(response);
    }
}
