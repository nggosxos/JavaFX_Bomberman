package entities.fix.powerup;

import constants.Constant;
import entities.fix.Powerup;
import entities.player.Player;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;
import sound.SoundEffect;

public class PowerupBombs extends Powerup {

    public PowerupBombs(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public PowerupBombs(int x, int y) {
        super(x, y, Sprite.powerup_bombs);
    }

    public void checkPlayerCollision() {
        if (isColliding(Player.getPlayer())) {
            Player.getPlayer().increaseBombs();
            Map.mapMatrix[y_pos / Constant.BLOCK_SIZE][x_pos / Constant.BLOCK_SIZE] = ' ';
            new SoundEffect("/music/power_up.wav").play(false);
            remove();
        }
    }
}
