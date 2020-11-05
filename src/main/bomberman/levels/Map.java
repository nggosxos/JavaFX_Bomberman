package levels;

import constants.Constant;
import controller.EventHandler;
import controller.GameLoop;
import entities.Entity;
import entities.fix.Brick;
import entities.fix.Grass;
import entities.fix.Portal;
import entities.fix.Wall;
import entities.mob.Ballom;
import entities.mob.Doll;
import entities.mob.Minvo;
import entities.mob.Oneal;
import entities.player.Player;
import entities.powerup.PowerupBombs;
import entities.powerup.PowerupFlames;
import entities.powerup.PowerupSpeed;
import graphics.Sprite;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Map {
    static Scene scene;
    static Group root;
    static Canvas canvas;
    static GraphicsContext graphicsContext;
    private static boolean sceneStarted;
    static Player player;

    public static String[] myMap;
    private static final List<Entity> topLayer = new ArrayList<Entity>();
    private static final List<Entity> midLayer = new ArrayList<Entity>();
    private static final List<Entity> bottomLayer = new ArrayList<Entity>();
    private static final List<Entity> boardLayer = new ArrayList<Entity>();


    public static int mapWidth;
    public static int mapHeight;
    public static int mapLevel;


    static {
        sceneStarted = false;
    }

    private static void initGame() throws IOException {
        createMap();
        root = new Group();
        scene = new Scene(root, mapWidth * Constant.SCALED_SIZE, mapHeight * Constant.SCALED_SIZE);
        canvas = new Canvas(mapWidth * Constant.SCALED_SIZE, mapHeight * Constant.SCALED_SIZE);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        GameLoop.start(getGraphicsContext());
        EventHandler.attachEventHandler(scene);
    }

    public static void createMap() {
        loadMapFile("levels/Level1.txt");
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                char c = myMap[i].charAt(j);
                addEntity(c, j * Constant.SCALED_SIZE, i * Constant.SCALED_SIZE);
            }
            System.out.println();
        }
    }

    public static void initScene() throws IOException {
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
        if (!midLayer.contains(entity)) {
            midLayer.add(entity);
            return true;
        } else {
            return false;
        }
    }
    public static List<Entity> getBoardLayer() {
        return boardLayer;
    }

    public static List<Entity> getBottomLayer() {
        return bottomLayer;
    }

    public static List<Entity> getMidLayer() {
        return midLayer;
    }

    public static List<Entity> getTopLayer() {
        return topLayer;
    }

    public static void addEntity(char c, int x, int y) {
        switch(c) {
            case '#':
                boardLayer.add(new Wall(x, y, Sprite.wall));
                break;
            case 'b':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                bottomLayer.add(new PowerupBombs(x, y, Sprite.powerup_bombs));
                midLayer.add(new Brick(x, y, Sprite.brick));
                break;
            case 's':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                bottomLayer.add(new PowerupSpeed(x, y, Sprite.powerup_speed));
                midLayer.add(new Brick(x, y, Sprite.brick));
                break;
            case 'f':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                bottomLayer.add(new PowerupFlames(x, y, Sprite.powerup_flames));
                midLayer.add(new Brick(x, y, Sprite.brick));
                break;
            case '*':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                midLayer.add(new Brick(x, y, Sprite.brick));
                break;
            case 'x':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                bottomLayer.add(new Portal(x, y, Sprite.portal));
                midLayer.add(new Brick(x, y, Sprite.brick));
                break;
            case ' ':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                break;
            case 'p':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                setPlayer(new Player(x, y, Sprite.player_right));
                break;
            case '1':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                topLayer.add(new Ballom(x, y, Sprite.ballom_right));
                break;
            case '2':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                topLayer.add(new Oneal(x, y, Sprite.oneal_right));
                break;
            case '3':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                topLayer.add(new Doll(x, y, Sprite.doll_right));
                break;
            case '4':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                topLayer.add(new Minvo(x, y, Sprite.grass));
                break;
            case '5':
                boardLayer.add(new Grass(x, y, Sprite.grass));
                topLayer.add(new Grass(x, y, Sprite.kondoria_right));
                break;
        }
    }

    public static void loadMapFile(String filePath) {
        try {
            URL fileMapPath = Map.class.getResource("/" + filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileMapPath.openStream()));
            String data = reader.readLine();
            StringTokenizer tokens = new StringTokenizer(data);
            mapLevel = Integer.parseInt(tokens.nextToken());
            mapHeight = Integer.parseInt(tokens.nextToken());
            mapWidth = Integer.parseInt(tokens.nextToken());
            myMap = new String[mapHeight];
            for (int i = 0; i < mapHeight; i++) {
                myMap[i] = reader.readLine().substring(0, mapWidth);
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}
