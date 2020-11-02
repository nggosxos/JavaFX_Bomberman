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
}
