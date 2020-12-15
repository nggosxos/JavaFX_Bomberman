package entities.fix.powerup;

import constants.Constant;
import entities.fix.Powerup;
import entities.player.Player;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;
import sound.SoundEffect;

public class PowerupSpeed extends Powerup {

    public PowerupSpeed(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public PowerupSpeed(int x, int y) {
        super(x, y, Sprite.powerup_speed);
    }

    public void checkPlayerCollision() {
        if (isColliding(Player.getPlayer())) {
            Player.getPlayer().setSpeedBooster();
            Map.mapMatrix[y_pos / Constant.BLOCK_SIZE][x_pos / Constant.BLOCK_SIZE] = ' ';
            new SoundEffect("/music/power_up.wav").play(false);
            remove();
        }
    }

}
