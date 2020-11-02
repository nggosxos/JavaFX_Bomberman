import com.sun.prism.paint.Color;
import graphics.Sprite;
import graphics.SpriteSheet;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import levels.Map;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image((Main.class.getResourceAsStream("icon/icon.png"))));
        primaryStage.setTitle("Checking");
        Map.initScene();
        Scene scene = Map.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
    }
}
