package entities.fix.powerup;

import entities.fix.Powerup;
import graphics.Sprite;
import javafx.scene.image.Image;

public class PowerupFlamePass extends Powerup {

    public PowerupFlamePass(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public PowerupFlamePass(int x, int y) {
        super(x, y, Sprite.powerup_flamepass);
    }

    public void checkPlayerCollision() {

    }
}
