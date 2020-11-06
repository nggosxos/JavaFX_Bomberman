package entities.bomb;

import entities.AnimatedEntity;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bomb extends AnimatedEntity {
    private int countDownTime = 120;

    private int removeTime = 30;

    private final int explosionTime = 50;

    private boolean ableToCross = true;

    private boolean exploded = false;

    public Bomb(int x, int y, Image boom) {
        super(x, y, boom);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        if (exploded) {
            image = Sprite.playSpriteAnimation(Sprite.bomb_exploded
                    , Sprite.bomb_exploded_1, Sprite.bomb_exploded_2, animate, 30);
        } else {
            image = Sprite.playSpriteAnimation(Sprite.bomb
                    , Sprite.bomb_1, Sprite.bomb_2, animate, 50);
        }
        graphicsContext.drawImage(image, x_pos, y_pos);
    }

    public void update() {
        if (countDownTime > 0) {
            countDownTime--;
        } else {
            if (!exploded) {
                //explosion
                exploded = true;
            } else {
                //expolsion_update
            }

            if (removeTime > 0) {
                removeTime--;
            } else {
                remove();
            }
        }
        animation();
    }
}
