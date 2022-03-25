package com.spnikit.ylabcourse.fileuploader.ylabcourse.service;

import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.GameBoard;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.game.Token;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.request.model.Move;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.response.model.MoveResp;
import org.springframework.stereotype.Service;

@Service
public class PlayGameRestService {
    private final GameBoard gameBoard = new GameBoard();


    public MoveResp makeMove(Move move) {

        var xCoord = move.getPointX();
        var yCoord = move.getPointY();

        var token = move.getPlayerId() == 1 ? Token.X : Token.O;

        gameBoard.acceptMove(xCoord, yCoord, token);

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
