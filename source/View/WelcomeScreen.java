package View;

import com.sun.jdi.Mirror;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.Blob;

/**
 * Created by cheny2 on 3/1/17.
 */
public class WelcomeScreen extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox root = addContent();

        Scene scene = new Scene(root, 1000, 680);
        scene.getStylesheets().add(WelcomeScreen.class.getResource("static/WelcomeScreen.css").toExternalForm());
        primaryStage.setTitle("Welcome!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private static VBox addContent() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(200);
        Text sceneTitle = addTitle();
        Button startGameButton = addButton();
        box.getChildren().addAll(sceneTitle, startGameButton);

        return box;
    }

    private static Text addTitle() {
        Text titleText = new Text("ForTheKing");
        titleText.setId("titleText");

        Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);

        DropShadow ds = new DropShadow();
        ds.setColor(Color.MAROON);
        ds.setOffsetX(5);
        ds.setOffsetY(5);
        ds.setRadius(5);
        ds.setSpread(0.2);

        blend.setBottomInput(ds);

        DropShadow ds1 = new DropShadow();
        ds1.setColor(Color.web("#f13a00"));
        ds1.setRadius(20);
        ds1.setSpread(0.2);

        Blend blend2 = new Blend();
        blend2.setMode(BlendMode.MULTIPLY);

        InnerShadow is = new InnerShadow();
        is.setColor(Color.web("#feeb42"));
        is.setRadius(9);
        is.setChoke(0.8);
        blend2.setBottomInput(is);

        InnerShadow is1 = new InnerShadow();
        is1.setColor(Color.web("#f13a00"));
        is1.setRadius(5);
        is1.setChoke(0.4);
        blend2.setTopInput(is1);

        Reflection r = new Reflection();
        r.setFraction(0.9);

        Blend blend1 = new Blend();
        blend1.setMode(BlendMode.MULTIPLY);
        blend1.setBottomInput(ds1);
        blend1.setTopInput(blend2);

        blend.setTopInput(blend1);
        blend.setBottomInput(r);

        titleText.setEffect(blend);

        titleText.setFont(Font.loadFont("file:resources/fonts/Drifttype.ttf", 300));

        return titleText;
    }


    private static Button addButton() {
        Button startGameButton = new Button("PLAY!");
        return startGameButton;
    }

    public static void main(String[] args) {launch(args);}
}
