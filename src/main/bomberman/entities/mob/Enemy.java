package entities.mob;

import entities.AnimatedEntity;
import javafx.scene.image.Image;

public abstract class Enemy extends AnimatedEntity {
    public Enemy(int x, int y, Image image) {
        super(x, y, image);
    }
}
