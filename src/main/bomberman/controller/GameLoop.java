package controller;

import constants.Constant;
import entities.Entity;
import entities.player.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import levels.Map;

public class GameLoop {

    public static void start(GraphicsContext graphicsContext) {

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                graphicsContext.clearRect(0, 0
                        , Map.mapWidth * Constant.SCALED_SIZE
                        , Map.mapHeight * Constant.SCALED_SIZE);
                updateGame();
                renderGame(graphicsContext);
            }
        };
        timer.start();
    }

    private static void updateGame() {

        Map.life.setText("Life: " + Player.getPlayer().getLifeCount());
        Map.bombs.setText("Bomb: " + Player.getPlayer().getRemainBombs());
        Map.score.setText("Score: " + Map.gameScore);
        Map.powerup.setText("M: " + Player.getPlayer().getImmortalTime());
        Map.enemies.setText("Left: " + Map.getEnemyLayer().size());

        for (int i = 0; i < Map.getMidLayer().size(); i++) {
            Map.getMidLayer().get(i).update();
        }
        for (int i = 0; i < Map.getTopLayer().size(); i++) {
            Map.getTopLayer().get(i).update();
        }
        for (int i = 0; i < Map.getEnemyLayer().size(); i++) {
            Map.getEnemyLayer().get(i).update();
        }

        Player.getPlayer().update();
        Map.setCameraView();
        Map.removeEntity();
    }

    private static void renderGame(GraphicsContext graphicsContext) {

        for (Entity entity : Map.getBoardLayer()) {
            entity.render(graphicsContext);
        }

        for (Entity entity : Map.getMidLayer()) {
            entity.render(graphicsContext);
        }
        for (Entity entity : Map.getTopLayer()) {
            entity.render(graphicsContext);
        }
        for (Entity entity : Map.getEnemyLayer()) {
            entity.render(graphicsContext);
        }

        Player.getPlayer().render(graphicsContext);
    }
}
