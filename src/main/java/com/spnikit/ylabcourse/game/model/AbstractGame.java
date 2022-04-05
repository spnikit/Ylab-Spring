package com.spnikit.ylabcourse.game.model;

import com.spnikit.ylabcourse.game.listeners.GameEnded;
import com.spnikit.ylabcourse.game.listeners.GameStarted;
import com.spnikit.ylabcourse.game.listeners.PlayerMoved;
import com.spnikit.ylabcourse.game.listeners.PlayerRegistered;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGame {
    protected Player player1;
    protected Player player2;
    protected Player winner;
    protected GameBoard board = new GameBoard();


    protected final List<PlayerMoved> playerMovedListeners = new ArrayList<>();
    protected final List<PlayerRegistered> playerRegisteredListeners = new ArrayList<>();
    protected final List<GameStarted> gameStartListeners = new ArrayList<>();
    protected final List<GameEnded> gameEndListeners = new ArrayList<>();


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






    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "AbstractGame{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", board=" + board +
                ", playerMovedListeners=" + playerMovedListeners +
                ", playerRegisteredListeners=" + playerRegisteredListeners +
                ", gameStartListeners=" + gameStartListeners +
                ", gameEndListeners=" + gameEndListeners +
                '}';
    }
}
