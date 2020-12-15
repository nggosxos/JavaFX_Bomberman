package levels;

import constants.Constant;
import controller.EventHandler;
import controller.GameLoop;
import entities.Entity;
import entities.enemy.*;
import entities.fix.Brick;
import entities.fix.Grass;
import entities.fix.Portal;
import entities.fix.Wall;
import entities.fix.powerup.*;
import entities.player.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

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

    public static Label score;
    public static Label life;
    public static Label powerup;
    public static Label bombs;
    public static Label levels;
    public static Label enemies;

    public static char[][] myMap;
    public static char[][] mapMatrix;
    private static final List<Enemy> enemyLayer = new ArrayList<Enemy>();
    private static final List<Entity> topLayer = new ArrayList<Entity>();
    private static final List<Entity> midLayer = new ArrayList<Entity>();
    private static final List<Entity> boardLayer = new ArrayList<Entity>();
    public static int CANVAS_WIDTH;
    public static int CANVAS_HEIGHT;

    private static int currentLevel = 1;

    public static int gameScore = 0;

    public static int mapWidth;
    public static int mapHeight;
    public static int mapLevel;


    static {
        sceneStarted = false;
    }

    private static void initGame() {
        root = new Group();
        scene = new Scene(root, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
        canvas = new Canvas();
        initLabel();
        root.getChildren().addAll(canvas, score, life, powerup, bombs, levels, enemies);
        graphicsContext = canvas.getGraphicsContext2D();
        createMap(currentLevel);
        GameLoop.start(getGraphicsContext());
        EventHandler.attachEventHandler(scene);
    }

    private static void initLabel() {
        score = new Label("Score");
        score.setFont(new Font("", 18));
        score.setPrefWidth(150);
        score.setPrefHeight(25);

        enemies = new Label("Enemies");
        enemies.setFont(new Font("", 18));
        enemies.setPrefWidth(100);
        enemies.setPrefHeight(25);
        enemies.setLayoutX(150);

        life = new Label("Life");
        life.setFont(new Font("", 18));
        life.setPrefWidth(100);
        life.setPrefHeight(25);
        life.setLayoutX(250);

        bombs = new Label("Bomb");
        bombs.setFont(new Font("", 18));
        bombs.setPrefWidth(100);
        bombs.setPrefHeight(25);
        bombs.setLayoutX(350);

        powerup = new Label("");
        powerup.setFont(new Font("", 18));
        powerup.setPrefWidth(100);
        powerup.setPrefHeight(25);
        powerup.setLayoutX(450);

        levels = new Label("");
        levels.setFont(new Font("", 18));
        levels.setPrefWidth(100);
        levels.setPrefHeight(25);
        levels.setLayoutX(550);
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

    public static void createMap(int level) {
        levels.setText("Level: " + level);
        clearMap();
        loadMapFile("/levels/Level" + level + ".txt");
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                char c = myMap[i][j];
                addEntity(c, j * Constant.SCALED_SIZE, i * Constant.SCALED_SIZE);
            }
        }
        canvas.setHeight(CANVAS_HEIGHT);
        canvas.setWidth(CANVAS_WIDTH);
    }

    public static void setCameraView() {
        if (player.getX_pos() < Constant.SCENE_WIDTH / 2) {
            canvas.setLayoutX(0);
        } else if (player.getX_pos() > CANVAS_WIDTH - Constant.SCENE_WIDTH / 2) {
            canvas.setLayoutX(Constant.SCENE_WIDTH - CANVAS_WIDTH);
        } else {
            canvas.setLayoutX(Constant.SCENE_WIDTH / 2.0 - player.getX_pos());
        }
        if (player.getY_pos() < Constant.SCENE_HEIGHT / 2) {
            canvas.setLayoutY(0);
        } else if (player.getY_pos() > CANVAS_HEIGHT - Constant.SCENE_HEIGHT / 2) {
            canvas.setLayoutY(Constant.SCENE_HEIGHT - CANVAS_HEIGHT);
        } else {
            canvas.setLayoutY(Constant.SCENE_HEIGHT / 2.0 - player.getY_pos());
        }
    }

    public static void clearMap() {
        graphicsContext.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        enemyLayer.clear();
        topLayer.clear();
        midLayer.clear();
        boardLayer.clear();
    }

    public static void nextMap() {
        if (currentLevel <= 5) {
            currentLevel += 1;
        } else {
            currentLevel = 1;
        }
    }

    public static void nextLevel() {
        clearMap();
        nextMap();
        createMap(currentLevel);
        player.setBombCount();
    }

    public static void resetLevel() {
        createMap(currentLevel);
    }

    public static void nextLevelByFn(int next) {
        createMap(next);
    }

    public static void removeEntity() {
        for (int i = 0; i < midLayer.size(); i++) {
            if (midLayer.get(i).isRemoved()) {
                midLayer.remove(i);
                --i;
            }
        }

        for (int i = 0; i < topLayer.size(); i++) {
            if (topLayer.get(i).isRemoved()) {
                topLayer.remove(i);
                --i;
            }
        }
        for (int i = 0; i < enemyLayer.size(); i++) {
            if (enemyLayer.get(i).isRemoved()) {
                gameScore += enemyLayer.get(i).getScore();
                enemyLayer.remove(i);
                --i;
            }
        }
    }

    public static List<Entity> getBoardLayer() {
        return boardLayer;
    }

    public static List<Entity> getMidLayer() {
        return midLayer;
    }

    public static List<Entity> getTopLayer() {
        return topLayer;
    }

    public static List<Enemy> getEnemyLayer() {
        return enemyLayer;
    }

    public static void addEntity(char c, int x, int y) {
        switch(c) {
            //maze
            case '#':
                boardLayer.add(new Wall(x, y));
                break;
            case '*':
                boardLayer.add(new Grass(x, y));
                topLayer.add(new Brick(x, y));
                break;
            case 'x':
                boardLayer.add(new Grass(x, y));
                midLayer.add(new Portal(x, y));
                topLayer.add(new Brick(x, y));
                break;
            case ' ':
                boardLayer.add(new Grass(x, y));
                break;
            //powerups
            case 'b':
                boardLayer.add(new Grass(x, y));
                midLayer.add(new PowerupBombs(x, y));
                topLayer.add(new Brick(x, y));
                break;
            case 's':
                boardLayer.add(new Grass(x, y));
                midLayer.add(new PowerupSpeed(x, y));
                topLayer.add(new Brick(x, y));
                break;
            case 'f':
                boardLayer.add(new Grass(x, y));
                midLayer.add(new PowerupFlames(x, y));
                topLayer.add(new Brick(x, y));
                break;
            case 'd':
                boardLayer.add(new Grass(x, y));
                midLayer.add(new PowerupDetonator(x, y));
                topLayer.add(new Brick(x, y));
                break;
            case 'w':
                boardLayer.add(new Grass(x, y));
                midLayer.add(new PowerupWallPass(x, y));
                topLayer.add(new Brick(x, y));
                break;
            case 'm':
                boardLayer.add(new Grass(x, y));
                midLayer.add(new PowerupFlamePass(x, y));
                topLayer.add(new Brick(x, y));
                break;
            case 'n':
                boardLayer.add(new Grass(x, y));
                midLayer.add(new PowerupBombPass(x, y));
                topLayer.add(new Brick(x, y));
                break;
            //player
            case 'p':
                boardLayer.add(new Grass(x, y));
                player = Player.setPlayer(x, y, false);
                break;
            //enemies
            case '1':
                boardLayer.add(new Grass(x, y));
                enemyLayer.add(new Ballom(x, y));
                break;
            case '2':
                boardLayer.add(new Grass(x, y));
                enemyLayer.add(new Oneal(x, y));
                break;
            case '3':
                boardLayer.add(new Grass(x, y));
                enemyLayer.add(new Doll(x, y));
                break;
            case '4':
                boardLayer.add(new Grass(x, y));
                enemyLayer.add(new Minvo(x, y));
                break;
            case '5':
                boardLayer.add(new Grass(x, y));
                enemyLayer.add(new Kondoria(x, y));
                break;
            case '6':
                boardLayer.add(new Grass(x, y));
                enemyLayer.add(new Ovapi(x, y));
                break;
            case '7':
                boardLayer.add(new Grass(x, y));
                enemyLayer.add(new Pass(x, y));
                break;
            case '8':
                boardLayer.add(new Grass(x, y));
                enemyLayer.add(new Pontan(x, y));
                break;
        }
    }

    public static void loadMapFile(String filePath) {
        try {
            URL fileMapPath = Map.class.getResource(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileMapPath.openStream()));
            String data = reader.readLine();
            StringTokenizer tokens = new StringTokenizer(data);
            mapLevel = Integer.parseInt(tokens.nextToken());
            mapHeight = Integer.parseInt(tokens.nextToken());
            mapWidth = Integer.parseInt(tokens.nextToken());
            CANVAS_HEIGHT = mapHeight * Constant.BLOCK_SIZE;
            CANVAS_WIDTH = mapWidth * Constant.BLOCK_SIZE;
            myMap = new char[mapHeight][mapWidth];
            mapMatrix = new char[mapHeight][mapWidth];
            for (int i = 0; i < mapHeight; i++) {
                char[] temp = reader.readLine().toCharArray();
                for (int j = 0; j < mapWidth; j++) {
                    myMap[i][j] = temp[j];
                    if ('#' == temp[j] || '*' == temp[j] || 'x' == temp[j]
                            || 'b' == temp[j] || 'f' == temp[j] || 's' == temp[j]
                            || 'w' == temp[j] || 'm' == temp[j] || 'n' == temp[j]) {
                        mapMatrix[i][j] = temp[j];
                    } else {
                        mapMatrix[i][j] = ' ';
                    }
                }
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static Entity getFixedEntityAt(int x, int y) {
        for (Entity entity : boardLayer) {
            if (entity instanceof Wall && entity.getX_pos() == x && entity.getY_pos() == y) {
                return entity;
            }
        }
        for (Entity entity : topLayer) {
            if (entity instanceof Brick && entity.getX_pos() == x && entity.getY_pos() == y) {
                return entity;
            }
        }

        return null;
    }

}
