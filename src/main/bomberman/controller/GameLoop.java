package controller;

import constants.Constant;
import entities.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import levels.Map;

public class GameLoop {

    private static boolean isRunning = false;
    private static boolean isPaused = true;

    static double currentAnimationTime;
    static double previousAnimationTime;
    static double deltaTime;
    final  static long startNanoTime = System.currentTimeMillis();

    public static void start(final GraphicsContext graphicsContext) {
        isRunning = true;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                previousAnimationTime = currentAnimationTime;
                currentAnimationTime = (now - System.currentTimeMillis()) / 1000000000.0;
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
        for (Entity entity : Map.getFixedObject()) {
            entity.update();
        }
        for (Entity entity : Map.getMovingObject()) {
            entity.update();
        }
    }

    public static void renderGame(GraphicsContext graphicsContext) {
        for (Entity entity : Map.getFixedObject()) {
            entity.render(graphicsContext);
        }
        for (Entity entity : Map.getMovingObject()) {
            entity.render(graphicsContext);
        }
    }

    public static double getCurrentAnimationTime() {
        return currentAnimationTime;
    }
}
