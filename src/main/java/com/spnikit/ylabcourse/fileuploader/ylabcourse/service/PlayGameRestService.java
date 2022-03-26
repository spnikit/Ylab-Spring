package com.spnikit.ylabcourse.fileuploader.ylabcourse.service;

import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.GameBoard;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.Token;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.request.model.Move;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.response.model.MoveResp;
import org.springframework.stereotype.Service;

@Service
public class PlayGameRestService {
    private final GameBoard gameBoard = new GameBoard();
    private Token currentPlayerToMove = Token.X;

    public MoveResp makeMove(Move move) {

        var pointX = move.getPointX();
        var pointY = move.getPointY();
        var token = move.getPlayerId() == 1 ? Token.X : Token.O;

        if (gameBoard.isWinner()) {
            throw new IllegalArgumentException("Game is over! The winner is playerId " + (token == Token.X ? 2 : 1));
        }

        if (gameBoard.isDraw()) {
            throw new IllegalArgumentException("Game is over! The resul is draw");
        }

        if (currentPlayerToMove != token) {
            throw new IllegalArgumentException("Now another player's turn");
        }


        gameBoard.acceptMove(pointX, pointY, token);

        currentPlayerToMove = move.getPlayerId() == 1 ? Token.O : Token.X;

        gameBoard.printBoard();

        var winner = gameBoard.isWinner() ?
                move.getPlayerId() : 0;


        var moveResponse = new MoveResp();

        moveResponse.setBoard(gameBoard.get1DBoard());
        moveResponse.setDraw(gameBoard.isDraw());
        moveResponse.setPlayerNextMoveId(move.getPlayerId() == 1 ? 2 : 1);
        moveResponse.setWinnerId(gameBoard.isWinner() ? move.getPlayerId() : 0);

        return moveResponse;

    }


}
