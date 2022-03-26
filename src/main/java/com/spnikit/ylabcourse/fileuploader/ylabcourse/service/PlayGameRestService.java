package com.spnikit.ylabcourse.fileuploader.ylabcourse.service;

import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.Game;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.request.model.Move;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.response.model.MoveResp;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PlayGameRestService {

    private Game game = new Game();

    public MoveResp makeMove(Move move) {

        Objects.requireNonNull(move, "move object can't be null");

        game.playWithRestService(move);

        var gameBoard = game.getBoard();

        return MoveResp.builder()
                .board(gameBoard.get1DBoard())
                .draw(gameBoard.isDraw())
                .playerNextMoveId(move.getPlayerId() == 1 ? 2 : 1)
                .winnerId(gameBoard.isWinner() ? move.getPlayerId() : null)
                .build();

    }

    public void setNewGame(){
        game = new Game();
    }

}
