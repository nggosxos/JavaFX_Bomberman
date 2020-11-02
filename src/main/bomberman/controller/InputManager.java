package controller;

import constants.Constant;
import constants.Direction;
import entities.player.Player;
import javafx.scene.input.KeyCode;
import levels.Map;

import java.util.List;

public class InputManager {
    public static void playerMovementHandler() {
        List<KeyCode> keyBoardInputs = EventHandler.getPlayerController();
        Player player = Map.getPlayer();

        if (keyBoardInputs.contains(KeyCode.UP) || keyBoardInputs.contains(KeyCode.W)) {
            player.move(Constant.PLAYER_STEP, Direction.UP);
        }

        if (keyBoardInputs.contains(KeyCode.DOWN) || keyBoardInputs.contains(KeyCode.S)) {
            player.move(Constant.PLAYER_STEP, Direction.DOWN);
        }

        if (keyBoardInputs.contains(KeyCode.LEFT) || keyBoardInputs.contains(KeyCode.A)) {
            player.move(Constant.PLAYER_STEP, Direction.LEFT);
        }

        if (keyBoardInputs.contains(KeyCode.RIGHT) || keyBoardInputs.contains(KeyCode.D)) {
            player.move(Constant.PLAYER_STEP, Direction.RIGHT);
        }

        if (!keyBoardInputs.contains(KeyCode.UP) && !keyBoardInputs.contains(KeyCode.W)
        && !keyBoardInputs.contains(KeyCode.DOWN) && !keyBoardInputs.contains(KeyCode.S)
        && !keyBoardInputs.contains(KeyCode.LEFT) && !keyBoardInputs.contains(KeyCode.A)
        && !keyBoardInputs.contains(KeyCode.RIGHT) && !keyBoardInputs.contains(KeyCode.D)) {
            player.move(0, Direction.DOWN);
        }
        if (keyBoardInputs.contains(KeyCode.SPACE)) {

        }
    }
}
