package entities.player;

import constants.Constant;
import controller.InputManager;
import entities.Entity;
import entities.MovingEntity;
import entities.RectangleBox;
import entities.bomb.Bomb;
import entities.mob.Enemy;
import entities.powerup.Powerup;
import entities.powerup.PowerupBombs;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import levels.Map;

import java.util.ArrayList;
import java.util.List;

public class Player extends MovingEntity {
    private int bombCount = 1;

    private List<Entity> bombList = new ArrayList<Entity>();
    private List<Entity> powerupList = new ArrayList<Entity>();

    public Player(int x, int y, Image player) {
        super(x, y, player);
        boundedBox = new RectangleBox(x, y, Constant.SCALED_SIZE - 10, Constant.SCALED_SIZE);
        alive = true;
    }

    public void update() {
        animation();
        checkEnemyCollision();
        if (!alive) {
            if (passAwayTime > 0) {
                passAwayTime--;
            } else {
                if (removeTime > 0) {
                    removeTime--;
                } else {
                    remove();
                }
            }
        }
        InputManager.playerMovementHandler();
    }

    @Override
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, x_pos, y_pos);
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
            image = Sprite.playSpriteAnimation(Sprite.player_dead, Sprite.player_dead_1, Sprite.player_dead_2, animate, 40);
        }
    }

    public void checkEnemyCollision() {
        for (Entity entity : Map.getTopLayer()) {
            if (entity instanceof Enemy && isColliding(entity)) {
                die();
            }
        }
    }

    public void die() {
        alive = false;
    }

    public void placeBomb() {
        if (bombCount > 0) {
            Bomb bomb = new Bomb(((x_pos + Constant.SCALED_SIZE / 2) / Constant.SCALED_SIZE) * Constant.SCALED_SIZE
                    , ((y_pos + Constant.SCALED_SIZE / 2) / Constant.SCALED_SIZE) * Constant.SCALED_SIZE, Sprite.bomb);
            Map.getMidLayer().add(bomb);
            bombList.add(bomb);
            bombCount--;
        }
        for (int i = 0; i < bombList.size(); i++) {
            if (bombList.get(i).isRemoved()) {
                bombList.remove(i);
                --i;
                bombCount++;
            }
        }
    }

    public void addPowerup(Powerup powerup) {
        powerupList.add(powerup);
        if (powerup instanceof PowerupBombs) {
            bombCount++;
        }
    }
}
