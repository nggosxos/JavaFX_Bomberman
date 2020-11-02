package controller;

import constants.Constant;
import entities.Entity;
import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import levels.Map;

public class GameLoop {

    private static boolean running = false;
    private static boolean paused = true;

    static double currentAnimationTime;
    static double previousAnimationTime;
    static double deltaTime;
    final  static long startNanoTime = System.currentTimeMillis();

    public static void start(final GraphicsContext graphicsContext) {
        running = true;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                previousAnimationTime = currentAnimationTime;
                currentAnimationTime = (now - startNanoTime) / 1000000000.0;
                deltaTime = currentAnimationTime - previousAnimationTime;

                graphicsContext.clearRect(0, 0, Constant.CANVAS_WIDTH, Constant.CANVAS_HEIGHT);

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
        for (Entity entity : Map.getMidLayer()) {
            entity.render(graphicsContext);
        }
        for (Entity entity : Map.getTopLayer()) {
            entity.render(graphicsContext);
        }
    }

    public static double getCurrentAnimationTime() {
        return currentAnimationTime;
    }
}
