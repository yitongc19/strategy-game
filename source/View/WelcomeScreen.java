package View;

import com.sun.jdi.Mirror;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.sql.Blob;

/**
 * Created by cheny2 on 3/1/17. Initial screen with options to start the game or see a help screen
 */
public class WelcomeScreen extends Application{

    private static final String BOOTSTRAP_PREFIX = "http://getbootstrap.com/components/#";
    static Stage welcomeStage;

    @Override
    /* Construct scene and stage to display the view
     */
    public void start(Stage primaryStage) throws Exception {

        final WebView webView = new WebView();
        welcomeStage = primaryStage;
//
//        Parent root = FXMLLoader.load(getClass().getResource("welcomeScreen.fxml"));

        VBox root = addContent(webView);

        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(WelcomeScreen.class.getResource("static/WelcomeScreen.css").toExternalForm());

        primaryStage.setTitle("Welcome!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /* add the main content */
    private static VBox addContent(WebView webView) {
        VBox box = new VBox();
        box.prefWidth(600);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(300);
//        Text sceneTitle = addTitle();
        Text titleHolder = new Text("       " + "\n          ");
        VBox buttonContainer = new VBox();
        buttonContainer.setSpacing(20);
        buttonContainer.setAlignment(Pos.CENTER);
        Button startGameButton = addStartButton(webView);
        Button helpButton = addHelpButton();
        helpButton.prefHeight(Double.MAX_VALUE);
        helpButton.prefWidth(Double.MAX_VALUE);
        buttonContainer.getChildren().addAll(startGameButton, helpButton);
        box.getChildren().addAll(titleHolder, buttonContainer);

        return box;
    }

    /* add the game title */
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


    /* construct the start game button */
    private static Button addStartButton(WebView webView) {
        Button startGameButton = new StartButton("START", webView);

        startGameButton.setOnAction(event -> {
            InitiateGame initiateGame = new InitiateGame();
            initiateGame.start(initiateGame.initiateStage);
            welcomeStage.close();
        });
        return startGameButton;
    }

    private static class StartButton extends Button {
        public StartButton(String textOnButton, final WebView webView) {
            setText(textOnButton);

            setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    webView.getEngine().load(BOOTSTRAP_PREFIX + textOnButton);
                }
            });
        }
    }

    private static Button addHelpButton() {
        Button helpButton = new Button("HELP");

        helpButton.setOnAction(event -> {
            Help help = new Help();
            try {
                help.start(Help.helpStage);
            }
            catch (Exception e) {
            }
            welcomeStage.close();
        });

        return helpButton;
    }

    public static void main(String[] args) {launch(args);}
}
