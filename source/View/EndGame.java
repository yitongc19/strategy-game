package View;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Observable;

/**
 * Created by cheny2 on 3/3/17.
 */
public class EndGame extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = addContent();
        Scene scene = new Scene(root, 1200, 600);
        scene.getStylesheets().add(EndGame.class.getResource("EndGame.css").toExternalForm());

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*
    Construct the main content of this page.
     */
    private static VBox addContent() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);

        HBox title = addTitle("LIGHT");
        HBox resultPanel = addResultPanels();
        HBox buttonPanel = addOperatingButtons();

        root.getChildren().addAll(title, resultPanel, buttonPanel);
        return root;
    }

    /*
    Construct the title text.
     */
    private static HBox addTitle(String winningTeam) {
        HBox title = new HBox();
        title.setAlignment(Pos.CENTER);
        Text titleText = new Text("TEAM " + winningTeam + " WIN");
        titleText.setFont(Font.font("Times New Roman", 40));

        Blend blend = new Blend();
        blend.setMode(BlendMode.MULTIPLY);

        DropShadow ds = new DropShadow();
        ds.setColor(Color.rgb(254, 235, 66, 0.3));
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

        Blend blend1 = new Blend();
        blend1.setMode(BlendMode.MULTIPLY);
        blend1.setBottomInput(ds1);
        blend1.setTopInput(blend2);

        blend.setTopInput(blend1);

        title.setEffect(blend);

        titleText.setFill(Color.WHITE);
        title.getChildren().add(titleText);
        return title;
    }

    /* Construct the result panel, this displays both the score and the achievements */
    private static HBox addResultPanels() {
        HBox resultPanels = new HBox();
        resultPanels.setAlignment(Pos.CENTER);
        resultPanels.setSpacing(100);

        VBox scorePanel = addResultList("Score");
        VBox achievementPanel = addResultList("Achievements");

        resultPanels.getChildren().addAll(scorePanel, achievementPanel);
        return resultPanels;
    }

    /* Constructs the two lists that will be displayed to the user, the score list,
    ranked high to low and the achievement list, which displays the winner of different
    achievements.
     */
    private static VBox addResultList(String title) {
        VBox scorePanel = new VBox();

        HBox scorePanelTitle = new HBox();
        scorePanelTitle.setAlignment(Pos.CENTER);
        Text scorePanelTitleText = new Text(title);
        scorePanelTitleText.setFill(Color.WHITE);
        scorePanelTitleText.setFont(Font.font("Times New Roman", 30));
        scorePanelTitle.getChildren().add(scorePanelTitleText);

        ListView<String> scoreList = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(
                "Player1: ", "Player2: ", "Player3: "
        );
        scoreList.setItems(items);
        scoreList.setMaxHeight(Control.USE_PREF_SIZE);
        scoreList.setPrefWidth(200);

        scorePanel.getChildren().addAll(scorePanelTitle, scoreList);

        return scorePanel;
    }

    /* Constructs the two operating buttons, one that allows the user to replay the
    game and the other allow the user to quit
     */
    private static HBox addOperatingButtons() {
        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setSpacing(300);

        ImageView replayImage = loadImage("restartButton.png");
        ImageView quitImage = loadImage("quitButton.png");

        Button replayButton = new Button("", replayImage);
        Button quitButton = new Button("", quitImage);

        quitButton.setOnAction(event -> {
            System.exit(0);
        });

        buttonContainer.getChildren().addAll(replayButton, quitButton);

        return buttonContainer;
    }

    private static ImageView loadImage(String url) {
        ImageView resultImage = new ImageView(EndGame.class.getResource(url).toExternalForm());
        resultImage.setFitWidth(25);
        resultImage.setPreserveRatio(true);
        resultImage.setSmooth(true);
        resultImage.setCache(true);

        return resultImage;
    }

    public static void main(String[] args) {launch(args);
    }
}
