package entities.mob;

import constants.Direction;
import entities.player.Player;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;

public class Kondoria extends Enemy {

    private int updateTime = 100;
    int pX = x_pos, pY = y_pos;
    public Kondoria(int x, int y, Image kondoria) {
        super(x, y, kondoria);
    }

    public void update() {
        animation();
        Player player = Map.getPlayer();
        if (updateTime > 0) {
            updateTime--;
        } else {
            updateTime = 150;
            pX = (player.getX_pos() / 48) * 48;
            pY = (player.getY_pos() / 48) * 48;
        }
        tracingPlayer();
    }

    public void playAnimation() {
        if (alive) {
            switch (currentDirection) {
                case UP:
                case RIGHT:
                    image = Sprite.playSpriteAnimation(Sprite.kondoria_right
                            , Sprite.kondoria_right_2, Sprite.kondoria_right_2, animate, 60);
                    break;
                case DOWN:
                case LEFT:
                    image = Sprite.playSpriteAnimation(Sprite.kondoria_left
                            , Sprite.kondoria_left_1, Sprite.kondoria_left_2, animate, 60);
            }
        }
        else {
            image = Sprite.playSpriteAnimation(Sprite.mob_dead_1
                    , Sprite.mob_dead_2, Sprite.mob_dead_3, animate, 20);
        }

    }

    public void tracingPlayer() {
        if (x_pos > pX) {
            move(1, Direction.LEFT);
        } else if (x_pos < pX) {
            move(1, Direction.RIGHT);
        } else {
            if (y_pos > pY) {
                move(1, Direction.UP);
            } else if (y_pos < pY) {
                move(1, Direction.DOWN);
            }
        }
        boundedBox.setPosition(x_pos, y_pos);
    }

    @Override
    public boolean checkFriendlyCollisions(int x, int y) {
        return true;
    }

    public Image getUpImage() {
        return Sprite.kondoria_right;
    }

    public Image getDownImage() {
        return Sprite.kondoria_left;
    }

    public Image getRightImage() {
        return Sprite.kondoria_right;
    }

    public Image getLeftImage() {
        return Sprite.kondoria_left;
    }

}
