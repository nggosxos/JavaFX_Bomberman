package entities.player;

import constants.Constant;
import controller.InputManager;
import entities.Entity;
import entities.MovingEntity;
import entities.RectangleBox;
import entities.bomb.Bomb;
import entities.bomb.BombExplosion;
import entities.enemy.Enemy;
import entities.fix.Powerup;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;

import java.util.ArrayList;
import java.util.List;

public class Player extends MovingEntity {

    private static Player player = null;

    private int bombCount = 1;
    private int placedBombs;

    private int bombRadius = 1;

    private int lifeCount = 3;

    private int revivalTime = 30;

    private boolean revived = true;

    private int x_init, y_init;

    InputManager input;

    private final List<Bomb> bombList = new ArrayList<Bomb>();
    private List<Powerup> powerupList = new ArrayList<Powerup>();

    public Player(int x, int y, Image player) {
        super(x, y, player);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE - 10, Constant.SCALED_SIZE - 2);
        alive = true;
        input = new InputManager();
        x_init = x;
        y_init = y;
    }

    public Player(int x, int y) {
        super(x, y, Sprite.player_right);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE - 10, Constant.SCALED_SIZE - 2);
        alive = true;
        input = new InputManager();
        x_init = x;
        y_init = y;
    }

    public static Player setPlayer(int x, int y) {
        if (player == null) {
            player = new Player(x, y);
        } else {
            player.setPosition(x, y);
        }
        return player;
    }

    public static Player getPlayer() {
        return player;
    }

    public void update() {
        animation();
        checkEnemyCollision();
        recountBombs();
        playAnimation();
        input.playerMovementHandler();
    }

    public void playAnimation() {
        if (alive) {
            switch(currentDirection) {
                case UP:
                    if (isMoving) {
                        image = Sprite.playSpriteAnimation(Sprite.player_up
                                , Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                    }
                    break;
                case DOWN:
                    if (isMoving) {
                        image = Sprite.playSpriteAnimation(Sprite.player_down
                                , Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                    }
                    break;
                case RIGHT:
                    if (isMoving) {
                        image = Sprite.playSpriteAnimation(Sprite.player_right
                                , Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                    }
                    break;
                case LEFT:
                    if (isMoving) {
                        image = Sprite.playSpriteAnimation(Sprite.player_left
                                , Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                    }
                    break;
            }
        } else {
            image = image = Sprite.playSpriteAnimation(Sprite.player_dead
                    , Sprite.player_dead_1, Sprite.player_dead_2, animate, 30);
        }
    }

    public void setPosition(int x, int y) {
        x_pos = x;
        y_pos = y;
        boundedBox.setPosition(x, y);
    }

    public void checkEnemyCollision() {
        for (Entity entity : Map.getEnemyLayer()) {
            if (entity instanceof Enemy && isColliding(entity)) {
                revival();
                break;
            }
        }
        for (Entity entity : Map.getTopLayer()) {
            if (entity instanceof BombExplosion && isColliding(entity)) {
                revival();
                break;
            }
            if (entity instanceof Bomb && isColliding(entity) && ((Bomb) entity).isExploded()) {
                revival();
                break;
            }
        }
    }

    public void revival() {
        if (lifeCount > 1) {
            alive = true;
            lifeCount--;
            setPosition(x_init, y_init);
        } else {
            alive = false;
            remove();
        }
    }

    public void die() {
        alive = false;
        revived = false;
    }

    public void placeBomb() {
        int x_bomb = ((x_pos + Constant.SCALED_SIZE / 2) / Constant.SCALED_SIZE) * Constant.SCALED_SIZE;
        int y_bomb = ((y_pos + Constant.SCALED_SIZE / 2) / Constant.SCALED_SIZE) * Constant.SCALED_SIZE;
        boolean placed = false;
        for (Entity bomb : bombList) {
            if (bomb.getX_pos() == x_bomb && bomb.getY_pos() == y_bomb) {
                placed = true;
                break;
            }
        }
        if (placedBombs < bombCount && !placed && alive) {
            Bomb bomb = new Bomb(x_bomb, y_bomb);
            Map.getTopLayer().add(bomb);
            bombList.add(bomb);
        }
    }

    public void recountBombs() {
        placedBombs = bombList.size();
        for (int i = 0; i < bombList.size(); i++) {
            if (bombList.get(i).isRemoved()) {
                bombList.remove(i);
                --i;
            }
        }
    }

    public int getBombRadius() {
        return bombRadius;
    }

    public void increaseFlame() {
        bombRadius++;
    }

    public void increaseBombs() {
        bombCount++;
    }

    public int getRemainBombs() {
        return bombCount - placedBombs;
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public Image getUpImage() {
        return Sprite.player_up;
    }

    public Image getDownImage() {
        return Sprite.player_down;
    }

    public Image getRightImage() {
        return Sprite.player_right;
    }

    public Image getLeftImage() {
        return Sprite.player_left;
    }
}
