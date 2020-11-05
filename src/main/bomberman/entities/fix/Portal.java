package entities.fix;

import constants.Constant;
import entities.Entity;
import entities.RectangleBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Portal extends Entity {
    public Portal(int x, int y, Image portal) {
        super(x, y, portal);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE, Constant.SCALED_SIZE);
    }

    public void update() {

    }
}
