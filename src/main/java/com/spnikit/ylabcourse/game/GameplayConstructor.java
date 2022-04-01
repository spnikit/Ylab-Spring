package com.spnikit.ylabcourse.game;

import java.util.Optional;

public class GameplayConstructor implements PlayerMoved, PlayerRegistered, GameStarted, GameEnded {
    private final Gameplay gameplay = new Gameplay();

    public GameplayConstructor(AbstractGame game) {
        game.addGameEndListener(this);
        game.addGameStartListener(this);
        game.addPlayerRegisteredListener(this);
        game.addPlayerMovedListener(this);
    }


    public Optional<Gameplay> getGameplay() {

        if(gameplay.getSteps().size() <= 0){
            return Optional.empty();
        }

        return Optional.of(this.gameplay);
    }

    @Override
    public void onPlayerMoved(int xCoord, int yCoord, Player player, int moveNumber) {
        var playerNumber = player.getToken() == Token.X ? 1 : 2;
        gameplay.addStep(new Step(moveNumber, xCoord, yCoord, playerNumber));
    }

    @Override
    public void onPlayerRegister(Player player) {
        gameplay.setPlayer(player);
    }

    @Override
    public void onGameStart() {

    }

    @Override
    public void onGameEnd(Player gameResult) {
        gameplay.setGameResult(gameResult);
    }
}
