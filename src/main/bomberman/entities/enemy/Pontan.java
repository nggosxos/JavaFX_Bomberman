package entities.enemy;

import entities.enemy.moving.MovingEnemy;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Pontan extends Enemy {

    public Pontan(int x, int y, Image pontan) {
        super(x, y, pontan);
        score = 8000;
        speed = 3;
        ableToPassWall = true;
        movingEnemy = new MovingEnemy(MovingEnemy.IQ.MEDIUM,
                ableToPassBrick, ableToPassWall);

    }

    public Pontan(int x, int y) {
        super(x, y, Sprite.pontan_right);
        score = 8000;
        speed = 3;
        ableToPassWall = true;
        movingEnemy = new MovingEnemy(MovingEnemy.IQ.MEDIUM,
                ableToPassBrick, ableToPassWall);
    }

    public void playAnimation() {
        if (alive) {
            switch (currentDirection) {
                case UP:
                case RIGHT:
                    image = Sprite.playSpriteAnimation(Sprite.pontan_right
                            , Sprite.pontan_right_2, Sprite.pontan_right_2, animate, 20);
                    break;
                case DOWN:
                case LEFT:
                    image = Sprite.playSpriteAnimation(Sprite.pontan_left
                            , Sprite.pontan_left_1, Sprite.pontan_left_2, animate, 20);
                    break;
            }
        }
        else {
            image = Sprite.playSpriteAnimation(Sprite.mob_dead_1
                    , Sprite.mob_dead_2, Sprite.mob_dead_3, animate, 30);
        }
    }

    public Image getUpImage() {
        return Sprite.pontan_right;
    }

    public Image getDownImage() {
        return Sprite.pontan_left;
    }

    public Image getRightImage() {
        return Sprite.pontan_right;
    }

    public Image getLeftImage() {
        return Sprite.pontan_left;
    }

}
