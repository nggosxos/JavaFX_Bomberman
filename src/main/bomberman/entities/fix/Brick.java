package entities.fix;

import entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Brick extends Entity {
    public Brick(int x, int y, Image brick) {
        x_pos = x;
        y_pos = y;
        image = brick;
    }

    public void update() {

    }

    public void render(GraphicsContext graphicsContext) {

    }
}
