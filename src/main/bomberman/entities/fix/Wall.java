package entities.fix;

import constants.Constant;
import entities.Entity;
import entities.RectangleBox;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Wall extends Entity {
    public Wall(int x, int y, Image wall) {
        super(x, y, wall);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE, Constant.SCALED_SIZE);
    }

    public Wall(int x, int y) {
        super(x, y, Sprite.wall);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE, Constant.SCALED_SIZE);
    }

    public void update() {

    }
}
