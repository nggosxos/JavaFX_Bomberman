package entities.enemy;

import constants.Constant;
import constants.Direction;
import entities.player.Player;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image oneal) {
        super(x, y, oneal);
    }

    public Oneal(int x, int y) {
        super(x, y, Sprite.oneal_right);
    }

    public void update() {
        super.update();
        tracingPlayer();
    }

    public void playAnimation() {
        if (alive) {
            switch (currentDirection) {
                case UP:
                case RIGHT:
                    image = Sprite.playSpriteAnimation(Sprite.oneal_right
                            , Sprite.oneal_right_2, Sprite.oneal_right_2, animate, 60);
                    break;
                case DOWN:
                case LEFT:
                    image = Sprite.playSpriteAnimation(Sprite.oneal_left
                            , Sprite.oneal_left_1, Sprite.oneal_left_2, animate, 60);
                    break;
            }
        }
        else {
            image = Sprite.playSpriteAnimation(Sprite.mob_dead_1
                    , Sprite.mob_dead_2, Sprite.mob_dead_3, animate, 30);
        }

    }

    public void tracingPlayer() {
        Player player = Map.getPlayer();
        int pX = (player.getX_pos() / Constant.SCALED_SIZE) * Constant.SCALED_SIZE;
        int pY = (player.getY_pos() / Constant.SCALED_SIZE) * Constant.SCALED_SIZE;

        if (x_pos > pX) {
            move(1, Direction.LEFT);
        } else if (x_pos < pX) {
            move(1, Direction.RIGHT);
        }

        if (y_pos > pY) {
            move(1, Direction.UP);
        } else if (y_pos < pY) {
            move(1, Direction.DOWN);
        }
    }

    public Image getUpImage() {
        return Sprite.oneal_right;
    }

    public Image getDownImage() {
        return Sprite.oneal_left;
    }

    public Image getRightImage() {
        return Sprite.oneal_right;
    }

    public Image getLeftImage() {
        return Sprite.oneal_left;
    }
}
