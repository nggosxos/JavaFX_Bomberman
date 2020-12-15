package entities.fix.bonus;

import constants.Constant;
import entities.fix.Powerup;
import entities.player.Player;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;
import sound.SoundEffect;

public class Dezeniman extends Powerup {
    public Dezeniman(int x, int y, Image powerup) {
        super(x, y, powerup);
    }

    public Dezeniman(int x, int y) {
        super(x, y, Sprite.bonus_dezemiman_san);
    }

    @Override
    public void checkPlayerCollision() {
        if (isColliding(Player.getPlayer())) {
            Map.mapMatrix[y_pos / Constant.BLOCK_SIZE][x_pos / Constant.BLOCK_SIZE] = ' ';
            new SoundEffect("/music/power_up.wav").play(false);
            remove();
        }
    }
}
