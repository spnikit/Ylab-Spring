package com.spnikit.ylabcourse.game;

public interface PlayerMoved {

    void onPlayerMoved(int xCoord, int yCoord, Player player, int moveNumber);
}


interface PlayerRegistered{
    void onPlayerRegister(Player player);
}

interface GameStarted{
    void onGameStart();
}

interface GameEnded{
    void onGameEnd(Player gameResult);
}