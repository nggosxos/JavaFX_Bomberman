package entities.fix;

import constants.Constant;
import entities.Entity;
import entities.RectangleBox;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Grass extends Entity {
    public Grass(int x, int y, Image grass) {
        super(x, y, grass);
        boundedBox = new RectangleBox(x, y, Constant.BLOCK_SIZE, Constant.BLOCK_SIZE);
    }

    public Grass(int x, int y) {
        super(x, y, Sprite.grass);
        boundedBox = new RectangleBox(x, y, Constant.BLOCK_SIZE, Constant.BLOCK_SIZE);
    }

    public void update() {

    }
}
