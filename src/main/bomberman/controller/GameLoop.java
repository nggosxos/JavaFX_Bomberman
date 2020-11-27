package controller;

import constants.Constant;
import entities.Entity;
import entities.player.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import levels.Map;

public class GameLoop {

    private static boolean running = false;
    private static boolean paused = true;
    private static boolean endgame = false;

    public static void start(final GraphicsContext graphicsContext) {

        running = true;
        paused = false;
        endgame = false;
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

    public static void updateGame() {

        Map.life.setText("Life: " + Map.getPlayer().getLifeCount());
        Map.bombs.setText("Bomb: " + Map.getPlayer().getRemainBombs());
        Map.score.setText("Score: " + Map.gameScore);

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

    public static void renderGame(GraphicsContext graphicsContext) {

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

        Map.getPlayer().render(graphicsContext);
    }
}
