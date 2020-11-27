package entities.bomb;

import constants.Constant;
import constants.Direction;
import entities.Entity;
import entities.fix.Brick;
import entities.fix.Wall;
import javafx.scene.canvas.GraphicsContext;
import levels.Map;

public class ExplosionDirection {

    private int x_init, y_init;

    private int radius;

    private Direction dir;

    protected BombExplosion[] explosions;

    public ExplosionDirection(int x, int y, Direction dir, int r) {
        this.dir = dir;
        x_init = x;
        y_init = y;
        radius = r;
        explosions = new BombExplosion[calculateExplosionRadius()];
        createBombExplosion();
    }

    public int calculateExplosionRadius() {
        int r = 0;
        int x = x_init;
        int y = y_init;
        while (r < radius) {
            switch (dir) {
                case UP:
                    y -= Constant.SCALED_SIZE;
                    break;
                case DOWN:
                    y += Constant.SCALED_SIZE;
                    break;
                case LEFT:
                    x -= Constant.SCALED_SIZE;
                    break;
                case RIGHT:
                    x += Constant.SCALED_SIZE;
                    break;
            }
            Entity entity = Map.getFixedEntityAt(x, y);
            if (entity instanceof Wall) {
                break;
            }
            if (entity instanceof Brick) {
                ((Brick) entity).setExploded();
                break;
            }
            r++;
        }
        return r;
    }

    public void createBombExplosion() {
        boolean last = false;
        int x = x_init;
        int y = y_init;

        for (int i = 0; i < explosions.length; i++) {
            last = (i == explosions.length - 1);

            switch (dir) {
                case UP:
                    y -= Constant.SCALED_SIZE;
                    break;
                case DOWN:
                    y += Constant.SCALED_SIZE;
                    break;
                case LEFT:
                    x -= Constant.SCALED_SIZE;
                    break;
                case RIGHT:
                    x += Constant.SCALED_SIZE;
                    break;
            }


            explosions[i] = new BombExplosion(x, y, dir, last);
            Map.getTopLayer().add(explosions[i]);

        }

    }

    public BombExplosion[] getExplosions() {
        return explosions;
    }

    public void update() {
        for (BombExplosion explosion : explosions) {
            explosion.update();
        }
    }

}
