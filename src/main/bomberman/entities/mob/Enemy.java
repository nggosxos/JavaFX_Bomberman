package entities.mob;

import constants.Constant;
import constants.Direction;
import entities.AnimatedEntity;
import entities.Entity;
import entities.MovingEntity;
import entities.RectangleBox;
import entities.bomb.Bomb;
import entities.fix.Brick;
import entities.fix.Wall;
import entities.powerup.Powerup;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;

public abstract class Enemy extends MovingEntity {

    public Enemy(int x, int y, Image image) {
        super(x, y, image);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE, Constant.SCALED_SIZE);
        alive = true;
    }

    @Override
    public boolean checkBombCollision(int x, int y) {
        return false;
    }
}
