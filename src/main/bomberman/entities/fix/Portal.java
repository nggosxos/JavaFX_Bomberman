package entities.fix;

import constants.Constant;
import entities.Entity;
import entities.RectangleBox;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;

public class Portal extends Entity {
    public Portal(int x, int y, Image portal) {
        super(x, y, portal);
        boundedBox = new RectangleBox(x + 12, y + 12, Constant.SCALED_SIZE - 12, Constant.SCALED_SIZE - 12);
    }

    public Portal(int x, int y) {
        super(x, y, Sprite.portal);
        boundedBox = new RectangleBox(x + 12, y + 12, Constant.SCALED_SIZE - 12, Constant.SCALED_SIZE - 12);
    }

    public void checkPlayerCollision() {
        if (Map.getEnemyLayer().size() == 0 && isColliding(Map.getPlayer())) {
            Map.nextLevel();
        }
    }

    public void update() {
        checkPlayerCollision();
    }
}
