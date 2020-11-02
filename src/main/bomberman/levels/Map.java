package levels;

import constants.Constant;
import controller.EventHandler;
import controller.GameLoop;
import entities.Entity;
import entities.fix.Brick;
import entities.fix.Grass;
import entities.fix.Portal;
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

    private static final List<Entity> topLayer = new ArrayList<Entity>();
    private static final List<Entity> midLayer = new ArrayList<Entity>();
    private static final List<Entity> boardLayer = new ArrayList<Entity>();

    private int mapWidth;
    private int mapHeight;
    private int mapLevel;


    static {
        sceneStarted = false;
    }

    private static void initGame() {
        root = new Group();
        scene = new Scene(root, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
        canvas = new Canvas(Constant.CANVAS_WIDTH, Constant.CANVAS_HEIGHT);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.drawImage(Sprite.player_left_2, 10, 10);
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
                boardLayer.add(object);
            }
        }
        setPlayer(new Player(0, 0, Sprite.player_right));

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
        topLayer.add(player);
    }

    public static boolean addMovingObject(Entity entity) {
        if (!topLayer.contains(entity)) {
            topLayer.add(entity);
            return true;
        } else {
            return false;
        }
    }

    public static List<Entity> getTopLayer() {
        return topLayer;
    }

    public static List<Entity> getBoardLayer() {
        return boardLayer;
    }

    public static List<Entity> getMidLayer() {
        return midLayer;
    }

    public void addEntity(char c, int x, int y) {
        switch(c) {
            case '#':
                boardLayer.add(new Wall(x, y, Sprite.wall));
                break;
            case 'b':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                midLayer.add(new Brick(x, y, Sprite.brick));
                break;
            case 's':
                break;
            case 'f':
                break;
            case '*':
                break;
            case 'x':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                midLayer.add(new Portal(x, y, Sprite.portal));
                topLayer.add(new Brick(x, y, Sprite.brick));
                break;
            case ' ':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                break;
            case 'p':
                topLayer.add(new Player(x, y, Sprite.player_right));
                break;
            case '1':
                break;
            case '2':
                break;
            case '3':
                break;
            case '4':
                break;
            case '5':
                break;
        }
    }
}
