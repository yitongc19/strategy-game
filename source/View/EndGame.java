package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
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
        Scene scene = new Scene(root, 1200, 700);
        scene.getStylesheets().add(EndGame.class.getResource("EndGame.css").toExternalForm());

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

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

    private static HBox addTitle(String winningTeam) {
        HBox title = new HBox();
        title.setAlignment(Pos.CENTER);
        Text titleText = new Text("TEAM " + winningTeam + " WIN");
        titleText.setStyle("-fx-font-size: 40");
        titleText.setFill(Color.WHITE);
        title.getChildren().add(titleText);
        return title;
    }

    private static HBox addResultPanels() {
        HBox resultPanels = new HBox();
        resultPanels.setAlignment(Pos.CENTER);
        resultPanels.setSpacing(100);

        VBox scorePanel = addResultList("Score");
        VBox achievementPanel = addResultList("Achievements");

        resultPanels.getChildren().addAll(scorePanel, achievementPanel);
        return resultPanels;
    }

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

    private static HBox addOperatingButtons() {
        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setSpacing(300);

        ImageView replayImage = loadImage("restartButton.png");
        ImageView quitImage = loadImage("quitButton.png");

        Button replayButton = new Button("", replayImage);
        Button quitButton = new Button("", quitImage);

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
