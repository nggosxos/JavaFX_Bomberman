package entities.fix.powerup;

import entities.Entity;
import entities.fix.Powerup;
import graphics.Sprite;
import javafx.scene.image.Image;

public class PowerupSpeed extends Powerup {

    public PowerupSpeed(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public PowerupSpeed(int x, int y) {
        super(x, y, Sprite.powerup_speed);
    }

    public void checkPlayerCollision() {

    }

}
