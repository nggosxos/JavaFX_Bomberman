package entities.fix.powerup;

import entities.fix.Powerup;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;

public class PowerupBombs extends Powerup {

    public PowerupBombs(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public PowerupBombs(int x, int y) {
        super(x, y, Sprite.powerup_bombs);
    }

    public void checkPlayerCollision() {
        if (isColliding(Map.getPlayer())) {
            Map.getPlayer().increaseBombs();
            remove();
        }
    }
}
