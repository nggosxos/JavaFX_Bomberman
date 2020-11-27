package entities.fix;

import constants.Constant;
import entities.Entity;
import entities.RectangleBox;
import entities.player.Player;
import javafx.scene.image.Image;
import levels.Map;

public abstract class Powerup extends Entity {

    public Powerup(int x, int y, Image powerup) {
        super(x, y, powerup);
        boundedBox = new RectangleBox(x + 12, y + 12, Constant.SCALED_SIZE - 12, Constant.SCALED_SIZE - 12);
    }

    public abstract void checkPlayerCollision();

    public void update() {
        checkPlayerCollision();
    }
}
