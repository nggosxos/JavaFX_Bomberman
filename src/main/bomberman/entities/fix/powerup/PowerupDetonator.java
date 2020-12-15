package entities.fix.powerup;

import constants.Constant;
import entities.fix.Powerup;
import entities.player.Player;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;
import sound.SoundEffect;

public class PowerupDetonator extends Powerup {

    public PowerupDetonator(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public PowerupDetonator(int x, int y) {
        super(x, y, Sprite.powerup_detonator);
    }

    public void checkPlayerCollision() {
        if (isColliding(Player.getPlayer())) {
            Map.mapMatrix[y_pos / Constant.BLOCK_SIZE][x_pos / Constant.BLOCK_SIZE] = ' ';
            new SoundEffect("/music/power_up.wav").play(false);
            remove();
        }
    }
}
