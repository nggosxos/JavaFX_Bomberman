package entities.bomb;

import constants.Constant;
import constants.Direction;
import entities.AnimatedEntity;
import entities.Entity;
import entities.RectangleBox;
import entities.fix.Brick;
import entities.player.Player;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;
import sound.SoundEffect;

public class Bomb extends AnimatedEntity {
    private int countDownTime = 120;

    private int removeTime = 30;

    private final int explosionTime = 50;

    private Player player = Player.getPlayer();

    private boolean allowToPass = true;

    private boolean exploded = false;

    private ExplosionDirection[] explosions;

    private BombExplosion explosion;

    public Bomb(int x, int y, Image boom) {
        super(x, y, boom);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE, Constant.SCALED_SIZE);
    }

    public Bomb(int x, int y) {
        super(x, y, Sprite.bomb);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE, Constant.SCALED_SIZE);
    }

    public void update() {
        if (countDownTime > 0) {
            countDownTime--;
        } else {
            if (!exploded) {
                setExplosions();
                exploded = true;
                new SoundEffect("/music/explosion.wav").play(false);
            }
            if (removeTime > 0) {
                removeTime--;
            } else {
                Map.mapMatrix[y_node][x_node] = ' ';
                remove();
            }
        }
        animation();
        playAnimation();
        setAllowToPass();
    }


    public void playAnimation() {
        if (exploded) {
            image = Sprite.playSpriteAnimation(Sprite.bomb_exploded
                    , Sprite.bomb_exploded_1, Sprite.bomb_exploded_2, animate, 30);
        } else {
            image = Sprite.playSpriteAnimation(Sprite.bomb
                    , Sprite.bomb_1, Sprite.bomb_2, animate, 50);
        }
    }


    public void setExplosions() {

        explosions = new ExplosionDirection[4];
        Entity entity = Map.getFixedEntityAt(x_pos, y_pos);
        if (entity instanceof Brick) {
            ((Brick) entity).setExploded();
        }
        for (int i = 0; i < explosions.length; i++) {
            explosions[i] = new ExplosionDirection(x_pos, y_pos, Direction.dir[i], Player.getPlayer().getBombRadius());
            for (int j = 0; j < explosions[i].getExplosions().length; j++) {
                Map.getTopLayer().add(explosions[i].getExplosions()[j]);
            }
        }
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setAllowToPass() {
        if (!isColliding(Player.getPlayer())) {
            allowToPass = false;
        }
    }

    public boolean allowToPass() {
        return allowToPass;
    }
}
