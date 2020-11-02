package entities.player;

import constants.Direction;
import entities.AnimatedEntity;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends AnimatedEntity {
    private int bombCount;
    private boolean isMoving;
    private Direction currentDirection;
    public Player(int x, int y, Image player) {
        x_pos = x;
        y_pos = y;
        image = player;
    }

    public void update() {
        animation();
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, x_pos, y_pos);
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
                    image = Sprite.playSpriteAnimation(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                }
                break;
            case DOWN:
                if (isMoving) {
                    image = Sprite.playSpriteAnimation(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                }
                break;
            case RIGHT:
                if (isMoving) {
                    image = Sprite.playSpriteAnimation(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                }
                break;
            case LEFT:
                if (isMoving) {
                    image = Sprite.playSpriteAnimation(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                }
                break;
        }
    }
}
