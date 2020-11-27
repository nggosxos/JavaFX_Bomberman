package entities.fix.powerup;

import entities.fix.Powerup;
import graphics.Sprite;
import javafx.scene.image.Image;

public class PowerupBombPass extends Powerup {

    public PowerupBombPass(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public PowerupBombPass(int x, int y) {
        super(x, y, Sprite.powerup_bombpass);
    }

    public void checkPlayerCollision() {

    }
}
