package entities.bomb;

import entities.AnimatedEntity;
import javafx.scene.image.Image;

public class Bomb extends AnimatedEntity {
    private int countDownTime = 180;

    private int removeTime = 30;

    private boolean ableToCross = true;
    private boolean exploded = false;

    public Bomb(int x, int y, Image boom) {
        super(x, y, boom);
    }

    public void update() {

    }
}
