package entities.enemy;

import constants.Constant;
import entities.Entity;
import entities.MovingEntity;
import entities.RectangleBox;
import entities.bomb.Bomb;
import entities.bomb.BombExplosion;
import entities.enemy.moving.MovingEnemy;
import javafx.scene.image.Image;
import levels.Map;

public abstract class Enemy extends MovingEntity {

    protected int score;
    protected MovingEnemy movingEnemy;

    public Enemy(int x, int y, Image image) {
        super(x, y, image);
        boundedBox = new RectangleBox(x, y, Constant.BLOCK_SIZE, Constant.BLOCK_SIZE);
        alive = true;
    }

    @Override
    public void update() {
        animation();
        checkBombCollision();
        if (!alive) {
            if (passAwayTime > 0) {
                passAwayTime--;
            } else {
                remove();
            }
        }
        playAnimation();
        try {
            enemySmartMoving();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean checkFriendlyCollisions(int x, int y) {
        boundedBox.setPosition(x, y);
        for (Entity entity : Map.getTopLayer()) {
            if (entity instanceof Bomb && isColliding(entity)) {
                boundedBox.setPosition(x_pos, y_pos);
                return false;
            }
        }
        return super.checkFriendlyCollisions(x, y);
    }

    public void checkBombCollision() {
        for (Entity entity : Map.getTopLayer()) {
            if (entity instanceof BombExplosion && isColliding(entity)) {
                alive = false;
                break;
            }
        }
    }

    protected void enemySmartMoving() {
        if (aBigStep > 0 && movableSteps(speed, currentDirection)) {
            move(speed, currentDirection);
            aBigStep -= speed;
        } else {
            aBigStep = Constant.BLOCK_SIZE;
            switch (currentDirection) {
                case UP:
                    y_node -= 1;
                    break;
                case DOWN:
                    y_node += 1;
                    break;
                case LEFT:
                    x_node -= 1;
                    break;
                case RIGHT:
                    x_node += 1;
                    break;
            }
            currentDirection = movingEnemy.movingDirection(Map.mapMatrix, x_node, y_node);
        }
    }

    public int getScore() {
        return score;
    }
}
