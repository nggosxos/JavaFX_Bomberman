package controller;

import constants.Direction;
import entities.player.Player;
import javafx.scene.input.KeyCode;

import java.util.List;

public class InputManager {

    public void playerMovementHandler() {

        List<KeyCode> keyBoardInputs = EventHandler.getPlayerController();
        Player player = Player.getPlayer();

        if (keyBoardInputs.contains(KeyCode.UP) || keyBoardInputs.contains(KeyCode.W)) {
            player.move(player.getSpeed(), Direction.UP);
        }

        if (keyBoardInputs.contains(KeyCode.DOWN) || keyBoardInputs.contains(KeyCode.S)) {
            player.move(player.getSpeed(), Direction.DOWN);
        }

        if (keyBoardInputs.contains(KeyCode.LEFT) || keyBoardInputs.contains(KeyCode.A)) {
            player.move(player.getSpeed(), Direction.LEFT);
        }

        if (keyBoardInputs.contains(KeyCode.RIGHT) || keyBoardInputs.contains(KeyCode.D)) {
            player.move(player.getSpeed(), Direction.RIGHT);
        }

        if (!keyBoardInputs.contains(KeyCode.UP) && !keyBoardInputs.contains(KeyCode.W)
        && !keyBoardInputs.contains(KeyCode.DOWN) && !keyBoardInputs.contains(KeyCode.S)
        && !keyBoardInputs.contains(KeyCode.LEFT) && !keyBoardInputs.contains(KeyCode.A)
        && !keyBoardInputs.contains(KeyCode.RIGHT) && !keyBoardInputs.contains(KeyCode.D)) {
            player.move(0, Direction.DOWN);
        }

        if (keyBoardInputs.contains(KeyCode.SPACE)) {
            player.placeBomb();
        }
    }
}
