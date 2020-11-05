package controller;

import constants.Constant;
import entities.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import levels.Map;

public class GameLoop {

    private static boolean running = false;
    private static boolean paused = true;

    public static void start(final GraphicsContext graphicsContext) {
        running = true;
        paused = false;
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
        InputManager.playerMovementHandler();
        for (Entity entity : Map.getBoardLayer()) {
            entity.update();
        }
        for (Entity entity : Map.getBottomLayer()) {
            entity.update();
        }
        for (Entity entity : Map.getMidLayer()) {
            entity.update();
        }
        for (Entity entity : Map.getTopLayer()) {
            entity.update();
        }
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
    }
}
