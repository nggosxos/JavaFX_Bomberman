package entities.enemy;

import entities.enemy.moving.MovingEnemy;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Pass extends Enemy {

    public Pass(int x, int y, Image doll) {
        super(x, y, doll);
        score = 4000;
        speed = 3;
        movingEnemy = new MovingEnemy(MovingEnemy.IQ.MEDIUM,
                        ableToPassBrick, ableToPassWall);
    }

    public Pass(int x, int y) {
        super(x, y, Sprite.pass_right);
        score = 4000;
        speed = 3;
        movingEnemy = new MovingEnemy(MovingEnemy.IQ.MEDIUM,
                        ableToPassBrick, ableToPassWall);
    }

    public void playAnimation() {
        if (alive) {
            switch (currentDirection) {
                case UP:
                case RIGHT:
                    image = Sprite.playSpriteAnimation(Sprite.pass_right
                            , Sprite.pass_right_2, Sprite.pass_right_2, animate, 60);
                    break;
                case DOWN:
                case LEFT:
                    image = Sprite.playSpriteAnimation(Sprite.pass_left
                            , Sprite.pass_left_1, Sprite.pass_left_2, animate, 60);
                    break;
            }
        }
        else {
            image = Sprite.playSpriteAnimation(Sprite.mob_dead_1
                    , Sprite.mob_dead_2, Sprite.mob_dead_3, animate, 30);
        }
    }

    public Image getUpImage() {
        return Sprite.pass_right;
    }

    public Image getDownImage() {
        return Sprite.pass_left;
    }

    public Image getRightImage() {
        return Sprite.pass_right;
    }

    public Image getLeftImage() {
        return Sprite.pass_left;
    }

}
