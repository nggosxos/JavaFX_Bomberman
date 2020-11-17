package entities;

import constants.Direction;
import entities.fix.Brick;
import entities.fix.Wall;
import javafx.scene.image.Image;
import levels.Map;

public abstract class MovingEntity extends AnimatedEntity {
    protected Direction currentDirection;
    protected boolean isMoving;
    protected boolean alive;
    protected int passAwayTime = 30;
    protected int removeTime = 10;

    public MovingEntity(int x, int y, Image image) {
        super(x, y, image);
    }

    public void move(int steps, Direction direction) {
        if (alive) {
            if (steps == 0) {
                isMoving = false;
            } else {
                switch (direction) {
                    case UP:
                        if (checkFriendlyCollisions(x_pos, y_pos - steps)) {
                            y_pos -= steps;
                            currentDirection = Direction.UP;
                            playAnimation();
                            isMoving = true;
                        } else {
                            isMoving = false;
                            image = this.getUpImage();
                        }
                        break;
                    case DOWN:
                        if (checkFriendlyCollisions(x_pos, y_pos + steps)) {
                            y_pos += steps;
                            currentDirection = Direction.DOWN;
                            playAnimation();
                            isMoving = true;
                        } else {
                            isMoving = false;
                            image = this.getDownImage();
                        }
                        break;
                    case LEFT:
                        if (checkFriendlyCollisions(x_pos - steps, y_pos)) {
                            x_pos -= steps;
                            currentDirection = Direction.LEFT;
                            playAnimation();
                            isMoving = true;
                        } else {
                            isMoving = false;
                            image = this.getLeftImage();
                        }
                        break;
                    case RIGHT:
                        if (checkFriendlyCollisions(x_pos + steps, y_pos)) {
                            x_pos += steps;
                            currentDirection = Direction.RIGHT;
                            playAnimation();
                            isMoving = true;
                        } else {
                            isMoving = false;
                            image = this.getRightImage();
                        }
                        break;
                }
            }
        } else {
            playAnimation();
        }
    }

    public abstract void playAnimation();

    public boolean checkFriendlyCollisions(int x, int y) {
        boundedBox.setPosition(x, y);
        for (Entity entity : Map.getBoardLayer()) {
            if (entity instanceof Wall && isColliding(entity)) {
                boundedBox.setPosition(x_pos, y_pos);
                return false;
            }
        }
        for (Entity entity : Map.getTopLayer()) {
            if (entity instanceof Brick && isColliding(entity)) {
                boundedBox.setPosition(x_pos, y_pos);
                return false;
            }
        }
        boundedBox.setPosition(x_pos, y_pos);
        return true;
    }

    public abstract boolean checkBombCollision(int x, int y);

    public abstract Image getUpImage();

    public abstract Image getDownImage();

    public abstract Image getRightImage();

    public abstract Image getLeftImage();
}
