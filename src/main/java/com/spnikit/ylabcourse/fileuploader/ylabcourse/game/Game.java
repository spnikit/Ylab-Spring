package com.spnikit.ylabcourse.fileuploader.ylabcourse.game;

import com.spnikit.ylabcourse.fileuploader.ylabcourse.request.model.Move;
import com.spnikit.ylabcourse.fileuploader.ylabcourse.shared.Utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class Game {
    private Player player1;
    private Player player2;
    private GameBoard board = new GameBoard();
    private Token currentPlayerToMove = Token.X;
    private final IOManager manager = new IOManager();
    private final List<PlayerMoved> playerMovedListeners = new ArrayList<>();
    private final List<PlayerRegistered> playerRegisteredListeners = new ArrayList<>();
    private final List<GameStarted> gameStartListeners = new ArrayList<>();
    private final List<GameEnded> gameEndListeners = new ArrayList<>();


    public void addPlayerMovedListener(PlayerMoved listener) {
        playerMovedListeners.add(listener);
    }

    public void addPlayerRegisteredListener(PlayerRegistered listener) {
        playerRegisteredListeners.add(listener);
    }

    public void addGameStartListener(GameStarted listener) {
        gameStartListeners.add(listener);
    }

    public void addGameEndListener(GameEnded listener) {
        gameEndListeners.add(listener);
    }

    public void notifyPlayerMovedListeners(int x, int y, Player p, int moveNumber) {
        playerMovedListeners.forEach(listener -> listener.onPlayerMoved(x, y, p, moveNumber));
    }
    public void notifyPlayerRegisteredListeners(Player p) {
        playerRegisteredListeners.forEach(listener -> listener.onPlayerRegister(p));
    }
    public void notifyGameStartListeners() {
        gameStartListeners.forEach(GameStarted::onGameStart);
    }
    public void notifyGameEndListeners(Player p) {
        gameEndListeners.forEach(listener -> listener.onGameEnd(p));
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


        finishGame();
        manager.printToConsole("Игра окончена!");
    }

    private void finishGame() {
        try {
            manager.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void replay(Gameplay gameplay) {

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

    public void playWithRestService(Move move){
        var coordinates = Utils.convertNumpadValuesToXYCoordinates(move.getCellNumber());
        var pointX = coordinates[0];
        var pointY = coordinates[1];
        var token = move.getPlayerId() == 1 ? Token.X : Token.O;


        if (board.isWinner()) {
            throw new IllegalArgumentException("Game is over! The winner is playerId " +
                    (currentPlayerToMove == Token.X ? 1 : 2));
        }

        if (board.isDraw()) {
            throw new IllegalArgumentException("Game is over! The result is draw");
        }

        if (currentPlayerToMove != token) {
            throw new IllegalArgumentException("Now another player's turn");
        }

        board.acceptMove(pointX, pointY, token);

        currentPlayerToMove = move.getPlayerId() == 1 ? Token.O : Token.X;

        board.printBoard();

        var winner = board.isWinner() ?
                move.getPlayerId() : 0;
    }

    public GameBoard getBoard(){
        return board;
    }

}
