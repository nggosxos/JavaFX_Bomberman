package entities.bomb;

import entities.AnimatedEntity;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.peer.ScrollbarPeer;

public class Bomb extends AnimatedEntity {
    private final int countDownTime = 180;

    private final int removeTime = 30;

    private final int explosionTime = 50;

    private boolean ableToCross = true;

    private boolean exploded = false;

    public Bomb(int x, int y, Image boom) {
        super(x, y, boom);
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        if (exploded) {
            image = Sprite.bomb_exploded;
        } else {
            image = Sprite.playSpriteAnimation(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 50);
        }
        graphicsContext.drawImage(image, x_pos, y_pos);
    }

    public void update() {
        animation();
    }
}
