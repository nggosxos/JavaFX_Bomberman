package entities.player;

import constants.Constant;
import controller.InputManager;
import entities.Entity;
import entities.MovingEntity;
import entities.RectangleBox;
import entities.bomb.Bomb;
import entities.bomb.BombExplosion;
import entities.enemy.Enemy;
import graphics.Sprite;
import javafx.scene.image.Image;
import levels.Map;
import sound.SoundEffect;

import java.util.ArrayList;
import java.util.List;

public class Player extends MovingEntity {

    private static Player player = null;

    private int bombCount = 1;
    private int placedBombs;
    private int immortalTime = 100;
    private int bombRadius = 1;
    private int lifeCount = 100;

    private boolean ableToPassFlame = false;
    private boolean ableToPassBomb = false;
    private boolean speedBoosted = false;
    private static boolean canDie = false;

    private int timePassFlame = 500;
    private int timePassBomb = 500;
    private int timeSpeedBoosted = 500;
    private int timePassWall = 500;
    private int timePassBrick = 500;

    private int x_init, y_init;

    InputManager input;

    private final List<Bomb> bombList = new ArrayList<Bomb>();

    public Player(int x, int y, Image player) {
        super(x, y, player);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE - 10, Constant.SCALED_SIZE - 2);
        alive = true;
        input = new InputManager();
        x_init = x;
        y_init = y;
        speed = 2;
    }

    public Player(int x, int y) {
        super(x, y, Sprite.player_right);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE - 10, Constant.SCALED_SIZE - 2);
        alive = true;
        input = new InputManager();
        x_init = x;
        y_init = y;
        speed = 2;
    }

    public static Player setPlayer(int x, int y, boolean newOne) {
        if (player == null || newOne) {
            player = new Player(x, y);
        } else {
            player.setPosition(x, y);
        }
        canDie = false;
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
        try {
            input.playerMovementHandler();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (immortalTime > 0 && !canDie) {
            immortalTime--;
        } else {
            canDie = true;
        }
        x_node = x_pos / Constant.BLOCK_SIZE;
        y_node = y_pos / Constant.BLOCK_SIZE;
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
            image = Sprite.playSpriteAnimation(Sprite.player_dead
                    , Sprite.player_dead_1, Sprite.player_dead_2, animate, 30);
        }
    }

    public void setPosition(int x, int y) {
        x_pos = x;
        y_pos = y;
        boundedBox.setPosition(x, y);
    }

    @Override
    public boolean checkFriendlyCollisions(int x, int y) {
        boundedBox.setPosition(x, y);
        for (Entity entity : Map.getTopLayer()) {
            if (entity instanceof Bomb && isColliding(entity)
                    && !((Bomb) entity).allowToPass() && !ableToPassBomb) {
                boundedBox.setPosition(x_pos, y_pos);
                return false;
            }
        }
        if (x < Constant.BLOCK_SIZE || x > Map.CANVAS_WIDTH - Constant.BLOCK_SIZE) {
            boundedBox.setPosition(x_pos, y_pos);
            return false;
        }
        if (y < Constant.BLOCK_SIZE || y > Map.CANVAS_HEIGHT - Constant.BLOCK_SIZE) {
            boundedBox.setPosition(x_pos, y_pos);
            return false;
        }
        return super.checkFriendlyCollisions(x, y);
    }

    public void checkEnemyCollision() {
        for (Entity entity : Map.getEnemyLayer()) {
            if (entity instanceof Enemy && isColliding(entity) && canDie) {
                revival();
                break;
            }
        }
        for (Entity entity : Map.getTopLayer()) {
            if (entity instanceof BombExplosion
                    && isColliding(entity) && canDie && !ableToPassFlame) {
                revival();
                break;
            }
            if (entity instanceof Bomb && isColliding(entity)
                    && ((Bomb) entity).isExploded() && !ableToPassFlame) {
                revival();
                break;
            }
        }
    }

    private void revival() {
        if (lifeCount > 0) {
            immortalTime = 100;
            canDie = false;
            alive = true;
            lifeCount--;
            setPosition(x_init, y_init);
        } else {
            alive = false;
            remove();
        }
    }

    public void placeBomb() {
        int x_bomb = ((x_pos + Constant.SCALED_SIZE / 2) / Constant.SCALED_SIZE) * Constant.SCALED_SIZE;
        int y_bomb = ((y_pos + Constant.SCALED_SIZE / 2) / Constant.SCALED_SIZE) * Constant.SCALED_SIZE;
        boolean placeable = true;
        for (Entity bomb : bombList) {
            if (bomb.getX_pos() == x_bomb && bomb.getY_pos() == y_bomb) {
                placeable = false;
                break;
            }
        }
        if (placedBombs < bombCount && placeable && alive) {
            Bomb bomb = new Bomb(x_bomb, y_bomb);
            Map.getTopLayer().add(bomb);
            bombList.add(bomb);
            Map.mapMatrix[y_bomb / Constant.BLOCK_SIZE][x_bomb / Constant.BLOCK_SIZE] = '*';
            new SoundEffect("/music/place_bomb.wav").play(false);
        }
    }

    private void recountBombs() {
        placedBombs = bombList.size();
        for (int i = 0; i < bombList.size(); i++) {
            if (bombList.get(i).isRemoved()) {
                bombList.remove(i);
                --i;
            }
        }
    }

    public void setAbleToPassBomb() {
        ableToPassBomb = true;
    }

    public void setAbleToPassWall() {
        ableToPassBrick = true;
    }

    public void setAbleToPassFlame() {
        ableToPassFlame = true;
    }

    public void setSpeedBooster() {
        speed = 4;
        speedBoosted = true;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX_node() {
        return x_node;
    }

    public int getY_node() {
        return y_node;
    }

    public int getImmortalTime() {
        return immortalTime;
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

    public void setBombCount() {
        bombList.clear();
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
