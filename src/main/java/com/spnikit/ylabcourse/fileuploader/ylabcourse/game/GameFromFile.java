package com.spnikit.ylabcourse.fileuploader.ylabcourse.game;

import java.io.IOException;
import java.util.Objects;

public class GameFromFile extends AbstractGame{
    private final IOManager manager = new IOManager();

    private void finishGame() {
        try {
            manager.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play(Gameplay gameplay) {

        Objects.requireNonNull(gameplay, "Gameplay object can't be null");


        manager.printToConsole("Привет, сейчас начнется игра в крестики-нолики");

        player1 = gameplay.getPlayer1();
        manager.printToConsole("Имя первого игрока: " + player1.getName());

        sleep();

        player2 = gameplay.getPlayer2();
        manager.printToConsole("Имя второго игрока: " + player2.getName());

        sleep();

        replayOneRound(gameplay);


        finishGame();
        manager.printToConsole("Игра окончена!");

    }

    private void replayOneRound(Gameplay gameplay) {
        Objects.requireNonNull(gameplay, "Gameplay object can't be null");


        var player1 = gameplay.getPlayer1();
        var player2 = gameplay.getPlayer2();
        var steps = gameplay.getSteps();

        board = new GameBoard();
        board.printBoard();

        boolean turn = true;

        for (var step : steps) {

            Player playerToMove = turn ? player1 : player2;

            int x = step.getxCoord();
            int y = step.getyCoord();

            try {
                board.acceptMove(x, y, playerToMove.getToken());

            } catch (IllegalArgumentException e) {
                manager.printToConsole(e.getMessage());
            }

            board.printBoard();
            turn = !turn;

            sleep();
        }

        if (board.isWinner()) {
            Player winner = (turn) ? player2 : player1;
            manager.printToConsole("Победил : " + winner.getName());

        } else {
            manager.printToConsole("Ничья!");
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Problem with sleeping in thread");
            e.printStackTrace();
        }
    }
}
