package levels;

import constants.Constant;
import controller.EventHandler;
import controller.GameLoop;
import entities.Entity;
import entities.fix.Grass;
import entities.fix.Wall;
import entities.player.Player;
import graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Map {
    static Scene scene;
    static Group root;
    static Canvas canvas;
    static GraphicsContext graphicsContext;
    private static boolean sceneStarted;
    static Player player;

    private static final List<Entity> movingObject = new ArrayList<Entity>();
    private static final List<Entity> fixedObject = new ArrayList<Entity>();

    static {
        sceneStarted = false;
    }

    private static void initGame() {
        root = new Group();
        scene = new Scene(root, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
        canvas = new Canvas(Constant.CANVAS_WIDTH, Constant.CANVAS_HEIGHT);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        GameLoop.start(getGraphicsContext());
        createMap();
        EventHandler.attachEventHandler(scene);
    }

    public static void createMap() {
        int maxX = 10;
        int maxY = 10;
        //demo map
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Entity object;
                if (y == 0 || y == maxY - 1 || x == 0 || x == maxX - 1) {
                    object = new Wall(x, y, Sprite.wall);
                } else {
                    object = new Grass(x, y, Sprite.grass);
                }
                fixedObject.add(object);
            }
        }
        setPlayer(new Player(1, 1, Sprite.player_right));

    }

    public static void initScene() {
        if (!sceneStarted) {
            initGame();
            sceneStarted = true;
        }
    }

    public static Scene getScene() {
        return scene;
    }

    public static GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Map.player = player;
        movingObject.add(player);
    }

    public static boolean addMovingObject(Entity entity) {
        if (!movingObject.contains(entity)) {
            movingObject.add(entity);
            return true;
        } else {
            return false;
        }
    }

    public static List<Entity> getMovingObject() {
        return movingObject;
    }

    public static List<Entity> getFixedObject() {
        return fixedObject;
    }
}
