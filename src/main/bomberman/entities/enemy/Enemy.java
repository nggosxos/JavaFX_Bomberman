package entities.enemy;

import constants.Constant;
import entities.Entity;
import entities.MovingEntity;
import entities.RectangleBox;
import entities.bomb.BombExplosion;
import javafx.scene.image.Image;
import levels.Map;

public abstract class Enemy extends MovingEntity {

    protected int score;

    public Enemy(int x, int y, Image image) {
        super(x, y, image);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE, Constant.SCALED_SIZE);
        alive = true;
        score = 100;
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
    }

    public void checkBombCollision() {
        for (Entity entity : Map.getTopLayer()) {
            if (entity instanceof BombExplosion && isColliding(entity)) {
                alive = false;
                break;
            }
        }
    }
    public int getScore() {
        return score;
    }
}
