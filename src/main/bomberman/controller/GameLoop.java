package controller;

import constants.Constant;
import entities.Entity;
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

        Map.removeEntity();
        for (Entity entity : Map.getBottomLayer()) {
            entity.update();
        }
        for (Entity entity : Map.getMidLayer()) {
            entity.update();
        }
        for (Entity entity : Map.getTopLayer()) {
            entity.update();
        }
        Map.getPlayer().update();
    }

    public static void renderGame(GraphicsContext graphicsContext) {
        for (Entity entity : Map.getBoardLayer()) {
            entity.render(graphicsContext);
        }

        for (Entity entity : Map.getBottomLayer()) {
            entity.render(graphicsContext);
        }
        for (Entity entity : Map.getMidLayer()) {
            entity.render(graphicsContext);
        }
        for (Entity entity : Map.getTopLayer()) {
            entity.render(graphicsContext);
        }
        Map.getPlayer().render(graphicsContext);
    }
}
