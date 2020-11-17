package entities.mob;

import constants.Direction;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Ballom extends Enemy{
    public Ballom(int x, int y, Image ballom) {
        super(x, y, ballom);
    }

    public void update() {

    }

    public void playAnimation() {
        if (alive) {
            switch (currentDirection) {
                case UP:
                case RIGHT:
                    image = Sprite.playSpriteAnimation(Sprite.ballom_right
                            , Sprite.ballom_right_2, Sprite.ballom_right_2, animate, 60);
                    break;
                case DOWN:
                case LEFT:
                    image = Sprite.playSpriteAnimation(Sprite.ballom_left
                            , Sprite.ballom_left_1, Sprite.ballom_left_2, animate, 60);
            }
        }
        else {
            image = Sprite.playSpriteAnimation(Sprite.mob_dead_1
                    , Sprite.mob_dead_2, Sprite.mob_dead_3, animate, 20);
        }
    }

    public Image getUpImage() {
        return Sprite.ballom_right;
    }

    public Image getDownImage() {
        return Sprite.ballom_left;
    }

    public Image getRightImage() {
        return Sprite.ballom_right;
    }

    public Image getLeftImage() {
        return Sprite.ballom_left;
    }
}
