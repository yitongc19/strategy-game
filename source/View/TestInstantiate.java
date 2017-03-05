package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by cheny2 on 3/5/17.
 */
public class TestInstantiate extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        returnScene newScene = new returnScene();

        Scene scene = newScene.getScene();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
