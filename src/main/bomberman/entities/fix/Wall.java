package entities.fix;

import entities.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall extends Entity {
    public Wall(int x, int y, Image wall) {
        x_pos = x;
        y_pos = y;
        this.image = wall;
    }

    public void update() {

    }

    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, x_pos * 48, y_pos * 48);
    }
}
