package entities.enemy;

import entities.enemy.moving.MovingEnemy;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Ovapi extends Enemy {

    public Ovapi(int x, int y, Image doll) {
        super(x, y, doll);
        ableToPassBrick = true;
        score = 2000;
        speed = 1;
        movingEnemy = new MovingEnemy(MovingEnemy.IQ.MEDIUM,
                ableToPassBrick, ableToPassWall);
    }

    public Ovapi(int x, int y) {
        super(x, y, Sprite.ovapi_right);
        ableToPassBrick = true;
        score = 2000;
        speed = 1;
        movingEnemy = new MovingEnemy(MovingEnemy.IQ.MEDIUM,
                ableToPassBrick, ableToPassWall);
    }


    public void playAnimation() {
        if (alive) {
            switch (currentDirection) {
                case UP:
                case RIGHT:
                    image = Sprite.playSpriteAnimation(Sprite.ovapi_right
                            , Sprite.ovapi_right_2, Sprite.ovapi_right_2, animate, 60);
                    break;
                case DOWN:
                case LEFT:
                    image = Sprite.playSpriteAnimation(Sprite.ovapi_left
                            , Sprite.ovapi_left_1, Sprite.ovapi_left_2, animate, 60);
                    break;
            }
        }
        else {
            image = Sprite.playSpriteAnimation(Sprite.mob_dead_1
                    , Sprite.mob_dead_2, Sprite.mob_dead_3, animate, 30);
        }
    }

    public Image getUpImage() {
        return Sprite.ovapi_right;
    }

    public Image getDownImage() {
        return Sprite.ovapi_left;
    }

    public Image getRightImage() {
        return Sprite.ovapi_right;
    }

    public Image getLeftImage() {
        return Sprite.ovapi_left;
    }

}