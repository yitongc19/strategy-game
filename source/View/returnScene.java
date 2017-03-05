package View;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by cheny2 on 3/5/17.
 */
public class returnScene {

    private Scene scene;

    public returnScene() {

        StackPane root = new StackPane();
        Circle cir = new Circle(200, Color.BLACK);

        root.getChildren().addAll(cir);

        Scene targetScene = new Scene(root, 600, 600);

        scene = targetScene;
    }

    public Scene getScene() {
        return scene;
    }
}
