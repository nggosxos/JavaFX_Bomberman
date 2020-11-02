package constants;

public class Constant {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 50;

    public static final int SCENE_WIDTH = 600;
    public static final int SCENE_HEIGHT = 600;

    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 600;

    public static final String GAME_NAME = "BOMBERMAN_TEAM27";
    public static final String GAME_VER = "ver1.0.0.0";

    public static final int SCALE_RATIO = 3;
    public static final int DEFAULT_SIZE = 16;
    public static final int SCALED_SIZE = DEFAULT_SIZE * SCALE_RATIO;

    public static final int TRANSPARENT_COLOR = 0xffff00ff;

    public static final int PLAYER_STEP = 2;
    public static final int ENEMY_STEP = 1;

    public static final int SPRITE_SHEET_SIZE = 256;
    public static final String spritePath = "textures/classic.png";

    public static final String mapPath = "levels/map.txt";
    public static final double LINE_WIDTH = 2;


    public static enum GAMESTATE {
        RUNNING, PAUSED, GAMEOVER
    };
}
