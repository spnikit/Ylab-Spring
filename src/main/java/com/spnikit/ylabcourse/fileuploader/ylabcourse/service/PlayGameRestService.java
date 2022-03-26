package com.spnikit.ylabcourse.fileuploader.ylabcourse.service;

import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.GameBoard;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.Token;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.request.model.Move;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.response.model.MoveResp;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.shared.Utils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PlayGameRestService {
    private final GameBoard gameBoard = new GameBoard();
    private Token currentPlayerToMove = Token.X;

    public MoveResp makeMove(Move move) {

        Objects.requireNonNull(move, "move object can't be null");

        var coordinates = Utils.convertNumpadValuesToXYCoordinates(move.getCellNumber());
        var pointX = coordinates[0];
        var pointY = coordinates[1];
        var token = move.getPlayerId() == 1 ? Token.X : Token.O;

        if (gameBoard.isWinner()) {
            throw new IllegalArgumentException("Game is over! The winner is playerId " + (token == Token.X ? 2 : 1));
        }

        if (gameBoard.isDraw()) {
            throw new IllegalArgumentException("Game is over! The result is draw");
        }

        if (currentPlayerToMove != token) {
            throw new IllegalArgumentException("Now another player's turn");
        }


        gameBoard.acceptMove(pointX, pointY, token);

        currentPlayerToMove = move.getPlayerId() == 1 ? Token.O : Token.X;

        gameBoard.printBoard();

        var winner = gameBoard.isWinner() ?
                move.getPlayerId() : 0;


        return MoveResp.builder()
                .board(gameBoard.get1DBoard())
                .draw(gameBoard.isDraw())
                .playerNextMoveId(move.getPlayerId() == 1 ? 2 : 1)
                .winnerId(gameBoard.isWinner() ? move.getPlayerId() : null)
                .build();

    }


}
