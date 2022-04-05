package com.spnikit.ylabcourse.game;

import com.spnikit.ylabcourse.game.io.IOGameManager;
import com.spnikit.ylabcourse.game.io.IOManager;
import com.spnikit.ylabcourse.game.listeners.GameStarted;
import com.spnikit.ylabcourse.game.model.AbstractGame;
import com.spnikit.ylabcourse.game.model.GameBoard;
import com.spnikit.ylabcourse.game.model.Player;
import com.spnikit.ylabcourse.game.model.PlayerNumber;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

public class GameWithConsole extends AbstractGame {

    public IOGameManager manager;

    public GameWithConsole(){
        this.manager = new IOManager();
    }

    public void setManager(IOGameManager manager) {
        this.manager = manager;
    }

    private Player getPlayer(PlayerNumber pn) {
        Objects.requireNonNull(pn, "PlayerNumber object can't be null");


        Optional<String> playerName;
        do {
            manager.printToConsole("Введите имя игрока " + pn + " :");
            playerName = manager.readStringFromConsole();

        } while (playerName.isEmpty());

        String name = playerName.get();
        return new Player(name, pn);
    }

    private int getCoordinate(Player playerToMove, String axis) {
        Objects.requireNonNull(playerToMove, "Player object can't be null");
        Objects.requireNonNull(axis, "axis object can't be null");


        OptionalInt coordinate;
        do {
            manager.printToConsole(playerToMove.getName() + ", введите координату " + axis);

            coordinate = manager.readIntFromConsole();

        } while (coordinate.isEmpty());

        return coordinate.getAsInt();
    }

    private void playOneRound() {

        board = new GameBoard();
        board.printBoard();

        boolean turn = true;

        int numberOfMoves = 1;
        do {
            Player playerToMove = turn ? player1 : player2;

            int x = getCoordinate(playerToMove, "X");
            int y = getCoordinate(playerToMove, "Y");

            try {
                board.acceptMove(x, y, playerToMove.getToken());
                playerToMove.makeMove();

                for (var listener : playerMovedListeners) {
                    listener.onPlayerMoved(x, y, playerToMove, numberOfMoves);
                }

            } catch (IllegalArgumentException e) {
                manager.printToConsole(e.getMessage());
                continue;
            }

            board.printBoard();
            turn = !turn;

            if (board.isWinner()) break;
            numberOfMoves++;

        } while (!board.isDraw());

        if (board.isWinner()) {
            Player winner = (turn) ? player2 : player1;
            manager.printToConsole("Победил : " + winner.getName());
            manager.writeMatchResults(LocalDate.now() + " - Победил " + winner.getName() + " за "
                    + winner.getNumberOfMoves() + " ходов!");


            gameEndListeners.forEach(listener -> listener.onGameEnd(winner));


        } else {
            manager.printToConsole("Ничья!");
            manager.writeMatchResults(LocalDate.now() + " Ничья ");

            gameEndListeners.forEach(listener -> listener.onGameEnd(null));

        }

    }

    public void play() {

        do {
            manager.printToConsole("Привет, сейчас начнется игра в крестики-нолики");
            gameStartListeners.forEach(GameStarted::onGameStart);


            player1 = getPlayer(PlayerNumber.ONE);

            playerRegisteredListeners.forEach(listener -> listener.onPlayerRegister(player1));

            player2 = getPlayer(PlayerNumber.TWO);

            playerRegisteredListeners.forEach(listener -> listener.onPlayerRegister(player2));


            playOneRound();

        } while (doPlayAgain());


        manager.finish();
        manager.printToConsole("Игра окончена!");
    }

    private boolean doPlayAgain() {

        Optional<String> yesOrNo;
        do {
            manager.printToConsole("Повторим? Да / Нет:");
            yesOrNo = manager.readStringFromConsole();

        } while (yesOrNo.isEmpty());

        String response = yesOrNo.get();

        return response.equalsIgnoreCase("да");
    }

}
