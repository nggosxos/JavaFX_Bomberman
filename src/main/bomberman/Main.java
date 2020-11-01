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

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image((Main.class.getResourceAsStream("icon/icon.png"))));
        primaryStage.setTitle("Checking");
        Group root = new Group();
        Scene scene = new Scene(root, 600, 600);
        Canvas canvas = new Canvas(600, 600);
        root.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Image image = SpriteSheet.tiles.getImage();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
        graphicsContext.drawImage(Sprite.powerup_bombs, 0, 0);

    }
}
