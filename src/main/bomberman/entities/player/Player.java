package entities.player;

import constants.Constant;
import constants.Direction;
import entities.AnimatedEntity;
import entities.Entity;
import entities.RectangleBox;
import entities.fix.Wall;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import levels.Map;

public class Player extends AnimatedEntity {
    private int bombCount;
    private boolean isMoving;
    private Direction currentDirection;

    public Player(int x, int y, Image player) {
        super(x, y, player);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE - 10, Constant.SCALED_SIZE);
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
                    if (!checkCollisions(x_pos, y_pos - steps)) {
                        y_pos -= steps;
                        currentDirection = Direction.UP;
                        playAnimation();
                    } else {
                        isMoving = false;
                        image = Sprite.player_up;
                    }
                    break;
                case DOWN:
                    if (!checkCollisions(x_pos, y_pos + steps)) {
                        y_pos += steps;
                        currentDirection = Direction.DOWN;
                        playAnimation();
                    } else {
                        isMoving = false;
                        image = Sprite.player_down;
                    }
                    break;
                case LEFT:
                    if (!checkCollisions(x_pos - steps, y_pos)) {
                        x_pos -= steps;
                        currentDirection = Direction.LEFT;
                        playAnimation();
                    } else {
                        isMoving = false;
                        image = Sprite.player_left;
                    }
                    break;
                case RIGHT:
                    if (!checkCollisions(x_pos + steps, y_pos)) {
                        x_pos += steps;
                        currentDirection = Direction.RIGHT;
                        playAnimation();
                    } else {
                        isMoving = false;
                        image = Sprite.player_right;
                    }
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
    public boolean checkCollisions(int x, int y) {
        boundedBox.setPosition(x, y);
        for (Entity entity : Map.getBoardLayer()) {
            if (entity instanceof Wall && isColliding(entity)) {
                boundedBox.setPosition(x_pos, y_pos);
                return true;
            }
        }
        boundedBox.setPosition(x_pos, y_pos);
        return false;
    }
}
