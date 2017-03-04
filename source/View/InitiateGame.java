package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.control.Menu;

/**
 * Created by cheny2 on 3/1/17.
 */
public class InitiateGame extends Application{

    @Override
    public void start(Stage primaryStage) {
        VBox root = addPanel();
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 1000, 650);
        scene.getStylesheets().add(InitiateGame.class.getResource("InitiateGame.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private static VBox addPanel() {
        VBox panel = new VBox();

        Text instruction = addInstruction();

        TilePane choooseNumPlayer = addPlayerNumChoice();
        choooseNumPlayer.setAlignment(Pos.CENTER);

        HBox teamPanel = addTeams();
        teamPanel.setAlignment(Pos.CENTER);

        Button start = new Button("Start");

        panel.getChildren().addAll(instruction, choooseNumPlayer, teamPanel, start);

        return panel;
    }

    private static Text addInstruction() {
        Text instruction = new Text("Please choose the number of players:");
        return instruction;
    }

    private static TilePane addPlayerNumChoice() {
        TilePane playerNumChoice = new TilePane();
        playerNumChoice.setPadding(new Insets(2,2,2,2));
        playerNumChoice.setHgap(3);
        playerNumChoice.setPrefColumns(4);

        Button numTwo = addNumButton(2);
        Button numFour = addNumButton(4);
        Button numSix = addNumButton(6);
        Button numEight = addNumButton(8);

        playerNumChoice.getChildren().addAll(numTwo, numFour, numSix, numEight);

        return playerNumChoice;
    }

    private static Button addNumButton(int num) {
        Button numButton = new Button(Integer.toString(num));
        return numButton;
    }
    private static HBox addTeams() {
        HBox teamPanel = new HBox();

        VBox team1 = new VBox();
        Text team1Title = new Text("Team 1");
        VBox team1Players = playerOneSide();
        team1.getChildren().addAll(team1Title, team1Players);

        VBox team2 = new VBox();
        Text team2Title = new Text("Team 2");
        VBox team2Players = playerOneSide();
        team2.getChildren().addAll(team2Title, team2Players);

        Text vsText = new Text("vs");
        vsText.setFill(Color.WHITE);
        vsText.setFont(Font.font("Verdana", 20));

        teamPanel.getChildren().addAll(team1, vsText, team2);

        return teamPanel;
    }

    private static VBox playerOneSide() {
        VBox playerContainer = new VBox();

        HBox playerOne = OnePlayer(1);
        HBox playerTwo = OnePlayer(2);
        HBox playerThree = OnePlayer(3);
        HBox playerFour = OnePlayer(4);

        playerContainer.getChildren().addAll(playerOne, playerTwo, playerThree, playerFour);

        return playerContainer;
    }

    private static HBox OnePlayer(int playerSeq) {
        HBox onePlayer = new HBox();
        Text playerNum = playerNum(playerSeq);
        TextField playerName = enterName();
        HBox playerColor = chooseColor();

        onePlayer.getChildren().addAll(playerNum, playerName, playerColor);

        return onePlayer;
    }

    private static Text playerNum(int num) {
        Text player = new Text();
        player.setFill(Color.WHITE);
        player.setText("Player " + Integer.toString(num));

        return player;
    }

    private static TextField enterName() {
        TextField enterName = new TextField();
        enterName.prefWidth(20);
        return enterName;
    }


    private static HBox chooseColor() {
        HBox pane = new HBox();

        MenuBar menuBar = new MenuBar();

        Circle menuCircle = new Circle(8);
        menuCircle.setStroke(Color.BLACK);
        menuCircle.setFill(Color.WHITE);
        Menu changeColorMenu = new Menu("", menuCircle);

        MenuItem red = new MenuItem("", new Circle(8, Color.RED));
        changeColorMenu.getItems().add(red);

        MenuItem blue = new MenuItem("", new Circle(8, Color.BLUE));
        changeColorMenu.getItems().add(blue);

        MenuItem green = new MenuItem("", new Circle(8, Color.GREEN));
        changeColorMenu.getItems().add(green);

        MenuItem yellow = new MenuItem("", new Circle(8, Color.YELLOW));
        changeColorMenu.getItems().add(yellow);

        menuBar.getMenus().addAll(changeColorMenu);
        pane.getChildren().add(menuBar);

        return pane;
    }

    public static void main(String[] args) {launch(args);}
}
