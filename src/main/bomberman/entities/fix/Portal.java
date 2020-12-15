package entities.fix;

import entities.player.Player;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;
import sound.SoundEffect;

public class Portal extends Powerup {
    public Portal(int x, int y, Image portal) {
        super(x, y, portal);
    }

    public Portal(int x, int y) {
        super(x, y, Sprite.portal);
    }

    public void checkPlayerCollision() {
        if (isColliding(Player.getPlayer()) && Map.getEnemyLayer().size() == 0) {
            new SoundEffect("/music/power_up.wav").play(false);
            Map.nextLevel();
        }
    }

    public void update() {
        checkPlayerCollision();
    }
}
