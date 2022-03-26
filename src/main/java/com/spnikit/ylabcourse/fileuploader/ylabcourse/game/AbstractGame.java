package com.spnikit.ylabcourse.fileuploader.ylabcourse.game;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGame {
    protected Player player1;
    protected Player player2;
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
}
