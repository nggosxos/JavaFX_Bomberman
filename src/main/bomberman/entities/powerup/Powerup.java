package entities.powerup;

import constants.Constant;
import entities.Entity;
import entities.RectangleBox;
import entities.player.Player;
import javafx.scene.image.Image;

public abstract class Powerup extends Entity {

    private boolean activated;

    public Powerup(int x, int y, Image powerup) {
        super(x, y, powerup);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE, Constant.SCALED_SIZE);
        activated = false;
    }

    public boolean collidePlayer(Entity entity) {
        if (entity instanceof Player && isColliding(entity)) {
            //((Player) entity).addPowerup(this);
            remove();
            activated = true;
            return true;
        }
        return false;
    }
}
