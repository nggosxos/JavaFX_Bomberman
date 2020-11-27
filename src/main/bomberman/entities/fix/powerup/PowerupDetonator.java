package entities.fix.powerup;

import entities.fix.Powerup;
import graphics.Sprite;
import javafx.scene.image.Image;

public class PowerupDetonator extends Powerup {

    public PowerupDetonator(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public PowerupDetonator(int x, int y) {
        super(x, y, Sprite.powerup_detonator);
    }

    public void checkPlayerCollision() {

    }
}
