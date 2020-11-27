package entities.fix;

import constants.Constant;
import entities.AnimatedEntity;
import entities.Entity;
import entities.RectangleBox;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Brick extends AnimatedEntity {

    private int removeTime = 30;

    private boolean exploded = false;

    public Brick(int x, int y, Image brick) {
        super(x, y, brick);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE, Constant.SCALED_SIZE);
    }

    public Brick(int x, int y) {
        super(x, y, Sprite.brick);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE, Constant.SCALED_SIZE);
    }

    public void playAnimation() {
        if (!exploded) {
            image = Sprite.brick;
        } else {
            image = Sprite.playSpriteAnimation(Sprite.brick_exploded
                    , Sprite.brick_exploded_1, Sprite.brick_exploded_2, animate, 30);
        }
    }

    public void setExploded() {
        exploded = true;
    }

    public void update() {
        if (exploded) {
            if (removeTime > 0) {
                removeTime--;
            } else {
                remove();
            }
        }
        animation();
        playAnimation();
    }

}
