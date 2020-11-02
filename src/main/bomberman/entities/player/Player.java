package entities.player;

import constants.Direction;
import controller.GameLoop;
import entities.AnimatedEntity;
import entities.Entity;
import graphics.Animations;
import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends AnimatedEntity {
    private int bombCount;
    private boolean isMoving;
    private Direction currentDirection;
    private Image[] player_up = {Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2};
    public Player(int x, int y, Image player) {
        x_pos = x;
        y_pos = y;
        image = player;
    }

    public void update() {

    }

    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, x_pos + image.getWidth() / 2, y_pos + image.getHeight() / 2);
    }

    public void move(int steps, Direction direction) {
        if (steps == 0) {
            isMoving = false;
            return;
        } else {
            isMoving = true;
            switch (direction) {
                case UP:
                    y_pos -= steps;
                    currentDirection = Direction.UP;
                    playAnimation();
                    break;
                case DOWN:
                    y_pos += steps;
                    currentDirection = Direction.DOWN;
                    playAnimation();
                    break;
                case LEFT:
                    x_pos -= steps;
                    currentDirection = Direction.LEFT;
                    playAnimation();
                    break;
                case RIGHT:
                    x_pos += steps;
                    currentDirection = Direction.RIGHT;
                    playAnimation();
                    break;
                default:
                    break;
            }
        }
    }

    private void playAnimation() {
        switch(currentDirection) {
            case UP:
                if (isMoving) {
                    //image = playAnimation(player_up, 0.3);
                    //image = Sprite.playSpriteAnimation(Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                } else {
                    image = Sprite.player_up;
                }
                break;
            case DOWN:
                if (isMoving) {
                    //image = Sprite.playSpriteAnimation(Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                }
                else {
                    image = Sprite.player_down;
                }
                break;
            case RIGHT:
                if (isMoving) {
                    //image = Sprite.playSpriteAnimation(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                } else {
                    image = Sprite.player_right;
                }
                break;
            case LEFT:
                if (isMoving) {

                    //image = Sprite.playSpriteAnimation(Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                } else {
                    image = Sprite.player_left;
                }
                break;
        }
    }

    private static Image playAnimation(Image[] images, double speed) {
        double time = GameLoop.getCurrentAnimationTime();
        return images[findCurrentFrame(time, images.length, speed)];
    }

    private static int findCurrentFrame(double time, int nFrames, double speed) {
        return (int) (time % (nFrames * speed) * speed);
    }
}
