package entities.mob;

import graphics.Sprite;
import javafx.scene.image.Image;

public class Minvo extends Enemy {
    public Minvo(int x, int y, Image minvo) {
        super(x, y, minvo);
    }

    public void update() {
        animation();
    }

    public void playAnimation() {
        if (alive) {
            switch (currentDirection) {
                case UP:
                case RIGHT:
                    image = Sprite.playSpriteAnimation(Sprite.minvo_right
                            , Sprite.minvo_right_2, Sprite.minvo_right_2, animate, 60);
                    break;
                case DOWN:
                case LEFT:
                    image = Sprite.playSpriteAnimation(Sprite.minvo_left
                            , Sprite.minvo_left_1, Sprite.minvo_left_2, animate, 60);
            }
        }
        else {
            image = Sprite.playSpriteAnimation(Sprite.mob_dead_1
                    , Sprite.mob_dead_2, Sprite.mob_dead_3, animate, 20);
        }
    }

    public Image getUpImage() {
        return Sprite.minvo_right;
    }

    public Image getDownImage() {
        return Sprite.minvo_left;
    }

    public Image getRightImage() {
        return Sprite.minvo_right;
    }

    public Image getLeftImage() {
        return Sprite.minvo_left;
    }

}
