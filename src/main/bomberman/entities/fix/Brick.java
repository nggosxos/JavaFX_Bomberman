package entities.fix;

import constants.Constant;
import entities.AnimatedEntity;
import entities.Entity;
import entities.RectangleBox;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import levels.Map;

public class Brick extends AnimatedEntity {

    private int removeTime = 30;

    private boolean exploded = false;

    public Brick(int x, int y, Image brick) {
        super(x, y, brick);
        boundedBox = new RectangleBox(x, y, Constant.BLOCK_SIZE, Constant.BLOCK_SIZE);
    }

    public Brick(int x, int y) {
        super(x, y, Sprite.brick);
        boundedBox = new RectangleBox(x, y, Constant.BLOCK_SIZE, Constant.BLOCK_SIZE);
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
                if ('*' == Map.mapMatrix[y_pos / Constant.BLOCK_SIZE][x_pos / Constant.BLOCK_SIZE]) {
                    Map.mapMatrix[y_pos / Constant.BLOCK_SIZE][x_pos / Constant.BLOCK_SIZE] = ' ';
                }
            }
        }
        animation();
        playAnimation();
    }

}
