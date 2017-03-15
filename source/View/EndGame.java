package View;

import Controller.GameController;
import Model.PlayerImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
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

import java.time.Duration;
import java.util.*;

/**
 * Created by cheny2 on 3/3/17.
 */
public class EndGame extends Application {

    static GameController controller;

    public static Stage endStage = new Stage();

    public EndGame(GameController control) {
        this.controller = control;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        endStage = primaryStage;
        VBox root = addContent(this.controller);
        Scene scene = new Scene(root, 1200, 600);
        scene.getStylesheets().add(EndGame.class.getResource("static/EndGame.css").toExternalForm());

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Stage getEndStage() {
        return endStage;
    }

    /*
    Construct the main content of this page.
     */
    private static VBox addContent(GameController controller) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);

        HBox title = addTitle("LIGHT");
        HBox resultPanel = addResultPanels(controller);
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
        titleText.setFont(Font.font(null, 40));

        title.getChildren().add(titleText);
        return title;
    }

    /* Construct the result panel, this displays both the score and the achievements */
    private static HBox addResultPanels(GameController controller) {
        HBox resultPanels = new HBox();
        resultPanels.setAlignment(Pos.CENTER);
        resultPanels.setSpacing(100);

        VBox scorePanel = addResultList("Score", controller);
        VBox achievementPanel = addResultList("Achievements", controller);

        resultPanels.getChildren().addAll(scorePanel, achievementPanel);
        return resultPanels;
    }

    /* Constructs the two lists that will be displayed to the user, the score list,
    ranked high to low and the achievement list, which displays the winner of different
    achievements.
     */
    private static VBox addResultList(String title, GameController controller) {
        VBox scorePanel = new VBox();

        HBox scorePanelTitle = new HBox();
        scorePanelTitle.setAlignment(Pos.CENTER);
        Text scorePanelTitleText = new Text(title);
        scorePanelTitleText.setFont(Font.font(null, 30));
        scorePanelTitle.getChildren().add(scorePanelTitleText);

        List<PlayerImpl> playerList = controller.getPlayers();

        List<String> playersOrderedByScore = new ArrayList<>();
        Collections.sort(playerList, PlayerImpl.Comparators.SCORE);

        for (PlayerImpl player : playerList) {
            String infoDisplay = player.getPlayerName() + Integer.toString(player.getScore());
            playersOrderedByScore.add(infoDisplay);
        }

        ListView<String> scoreList = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();
        if (title.equals("Score")) {
            items = FXCollections.observableArrayList(playersOrderedByScore);
        } else if (title.equals("Achievements")) {

        }
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

        ImageView replayImage = loadImage("file:assets/resourcesImg/restartButton.png");
        ImageView quitImage = loadImage("file:assets/resourcesImg/quitButton.png");

        Button replayButton = new Button("", replayImage);
        replayButton.setOnAction(event -> {
            WelcomeScreen welcomeScreen = new WelcomeScreen();
            try {
                welcomeScreen.start(WelcomeScreen.welcomeStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            endStage.close();
        });
        Button quitButton = new Button("", quitImage);
        quitButton.setOnAction(event -> {
            Stage stagePopup = new Stage();
            VBox comp = new VBox();
            comp.setSpacing(20);
            comp.setAlignment(Pos.CENTER);
            Label thankYou = new Label("Thank you for playing the game!"
            +"\nConfirm exit?");
            thankYou.setAlignment(Pos.CENTER);
            thankYou.setFont(Font.font(null, 16));
            Button confirmExit = new Button("Yes");
            Button declineExit = new Button("No");
            HBox buttonHolder = new HBox();
            buttonHolder.setAlignment(Pos.CENTER);
            buttonHolder.setSpacing(40);
            buttonHolder.getChildren().addAll(confirmExit, declineExit);

            comp.getChildren().addAll(thankYou, buttonHolder);
            Scene popUpScene = new Scene(comp, 300, 200);
            stagePopup.setScene(popUpScene);
            stagePopup.show();

            confirmExit.setOnAction(event1 -> {
                stagePopup.close();
                endStage.close();
            });

            declineExit.setOnAction(event1 -> {
                stagePopup.close();
            });

//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            stagePopup.close();
//            endStage.close();
        });

        buttonContainer.getChildren().addAll(replayButton, quitButton);

        return buttonContainer;
    }

    private static ImageView loadImage(String url) {
        Image temp = new Image(url);
        ImageView resultImage = new ImageView(temp);
        resultImage.setFitWidth(25);
        resultImage.setPreserveRatio(true);
        resultImage.setSmooth(true);
        resultImage.setCache(true);

        return resultImage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
