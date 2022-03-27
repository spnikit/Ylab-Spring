package com.spnikit.ylabcourse.fileuploader.ylabcourse.game;

import com.spnikit.ylabcourse.fileuploader.ylabcourse.request.model.Move;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.shared.Utils;


public class GameWithRestApi extends AbstractGame {


    private int moveNumber = 1;
    private Player currentPlayerToMove;

    public void play(Move move) {
        if(currentPlayerToMove == null){
            currentPlayerToMove = getPlayer1();
        }

        var coordinates = Utils.convertNumpadValuesToXYCoordinates(move.getCellNumber());
        var pointX = coordinates[0];
        var pointY = coordinates[1];
        var token = move.getPlayerId() == 1 ? Token.X : Token.O;


        if (board.isWinner()) {
            throw new IllegalArgumentException("Game is over! The winner is playerId " +
                    this.getWinner().getId());
        } else if (board.isDraw()) {
            throw new IllegalArgumentException("Game is over! The result is draw");
        } else if (currentPlayerToMove.getId() != move.getPlayerId()) {
            throw new IllegalArgumentException("Now another player's turn");
        }

        board.acceptMove(pointX, pointY, token);
        notifyPlayerMovedListeners(pointX, pointY, currentPlayerToMove, moveNumber);

        if (board.isWinner()) {
            this.setWinner(currentPlayerToMove);
            notifyGameEndListeners(currentPlayerToMove);
        }
        moveNumber++;
        currentPlayerToMove = move.getPlayerId() == 1 ? getPlayer2(): getPlayer1();
        board.printBoard();

    }

    public GameBoard getBoard() {
        return board;
    }

    public Player setPlayer(String name, PlayerNumber pn) {
        if (pn == PlayerNumber.ONE && player1 == null) {
            player1 = new Player(name, pn);

            notifyPlayerRegisteredListeners(player1);

            return getPlayer1();
        } else if (pn == PlayerNumber.TWO && player2 == null) {
            player2 = new Player(name, pn);

            notifyPlayerRegisteredListeners(player2);

            return getPlayer2();
        } else {
            throw new IllegalArgumentException("Player " + pn + " already defined!");
        }
    }
}
