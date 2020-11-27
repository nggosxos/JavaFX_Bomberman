package entities.fix.powerup;

import entities.fix.Powerup;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;

public class PowerupFlames extends Powerup {
    public PowerupFlames(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public PowerupFlames(int x, int y) {
        super(x, y, Sprite.powerup_flames);
    }

    public void checkPlayerCollision() {
        if (isColliding(Map.getPlayer())) {
            Map.getPlayer().increaseFlame();
            remove();
        }
    }
}
