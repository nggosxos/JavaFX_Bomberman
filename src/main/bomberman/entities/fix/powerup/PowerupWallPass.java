package entities.fix.powerup;

import entities.fix.Powerup;
import graphics.Sprite;
import javafx.scene.image.Image;

public class PowerupWallPass extends Powerup {

    public PowerupWallPass(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public PowerupWallPass(int x, int y) {
        super(x, y, Sprite.powerup_wallpass);
    }

    public void checkPlayerCollision() {

    }
}
