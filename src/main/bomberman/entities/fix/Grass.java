package entities.fix;

import entities.Entity;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Grass extends Entity {
    public Grass(int x, int y, Image grass) {
        x_pos = x;
        y_pos = y;
        this.image = grass;
    }

    public void update() {

    }

    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, x_pos * 48, y_pos * 48);
    }
}
