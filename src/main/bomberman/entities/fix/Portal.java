package entities.fix;

import entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Portal extends Entity {
    public Portal(int x, int y, Image portal) {
        x_pos = x;
        y_pos = y;
        image = portal;
    }

    public void update() {

    }
}
