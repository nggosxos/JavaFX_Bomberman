import constants.Constant;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import levels.Map;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon/icon.png")));
        primaryStage.setTitle(Constant.GAME_NAME + " " + Constant.GAME_VER);
        Map.initScene();
        Scene scene = Map.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
