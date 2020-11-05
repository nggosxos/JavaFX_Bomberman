package entities.fix;

import constants.Constant;
import entities.Entity;
import entities.RectangleBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Brick extends Entity {
    public Brick(int x, int y, Image brick) {
        super(x, y, brick);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE, Constant.SCALED_SIZE);
    }

    public void update() {

    }
}
