package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by cheny2 on 3/3/17.
 */
public class ConstructBuilding extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1200, 780);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
