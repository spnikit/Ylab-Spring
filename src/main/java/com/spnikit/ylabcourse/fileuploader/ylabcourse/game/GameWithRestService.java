package com.spnikit.ylabcourse.fileuploader.ylabcourse.game;

import com.spnikit.ylabcourse.fileuploader.ylabcourse.request.model.Move;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.shared.Utils;


public class GameWithRestService extends AbstractGame {

    private Token currentPlayerToMove = Token.X;
    private int moveNumber;

    public void play(Move move) {
        var coordinates = Utils.convertNumpadValuesToXYCoordinates(move.getCellNumber());
        var pointX = coordinates[0];
        var pointY = coordinates[1];
        var token = move.getPlayerId() == 1 ? Token.X : Token.O;


        if (board.isWinner()) {
            throw new IllegalArgumentException("Game is over! The winner is playerId " +
                    (currentPlayerToMove == Token.X ? 1 : 2));
        } else if (board.isDraw()) {
            throw new IllegalArgumentException("Game is over! The result is draw");
        } else if (currentPlayerToMove != token) {
            throw new IllegalArgumentException("Now another player's turn");
        }

        board.acceptMove(pointX, pointY, token);
        notifyPlayerMovedListeners(pointX, pointY, null, moveNumber);
        moveNumber++;

        currentPlayerToMove = move.getPlayerId() == 1 ? Token.O : Token.X;

        board.printBoard();

    }

    public GameBoard getBoard() {
        return board;
    }

}
