package View;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.control.Menu;

/**
 * Created by cheny2 on 3/1/17.
 */
public class InitiateGame extends Application{

    static Stage initiateStage = new Stage();

    @Override
    public void start(Stage primaryStage) {

        initiateStage = primaryStage;

        VBox root = addPanel();
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 1000, 650);
        scene.getStylesheets().add(InitiateGame.class.getResource("static/InitiateGame.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /* adding the root panel */
    private static VBox addPanel() {
        VBox panel = new VBox();
        panel.setSpacing(30);
        Text instruction = addInstruction();

        VBox choooseNumPlayer = addPlayerNumChoice();

        Button start = new Button("Start");

        start.setOnAction(event -> {
            ConstructBuilding constructBuilding = new ConstructBuilding();
            try {
                constructBuilding.start(ConstructBuilding.constructStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            initiateStage.close();
        });

        panel.getChildren().addAll(instruction, choooseNumPlayer, start);

        return panel;
    }

    /* constructing the instruction on the top */
    private static Text addInstruction() {
        Text instruction = new Text("Please choose the number of players:");
        //The dropshadow effect
        Reflection r = new Reflection();
        r.setFraction(0.7f);


        instruction.setEffect(r);
        instruction.setCache(true);
        instruction.setX(10.0f);
        instruction.setY(270.0f);
        instruction.setFill(Color.WHITE);
        instruction.setFont(Font.font(null, FontWeight.BOLD, 32));

        return instruction;
    }

    /* Container for the number of players buttons */
    private static VBox addPlayerNumChoice() {
        VBox playerInfoGroup = new VBox();
        playerInfoGroup.setSpacing(10);

        TilePane playerNumChoice = new TilePane();
        playerNumChoice.setAlignment(Pos.CENTER);
        playerNumChoice.setPadding(new Insets(20, 0, 0 , 0));

        playerNumChoice.setHgap(10);
        playerNumChoice.setPrefColumns(4);

        Button numTwo = addNumButton(2);
        numTwo.setId("numButton");
        Button numFour = addNumButton(4);
        numFour.setId("numButton");
        Button numSix = addNumButton(6);
        numSix.setId("numButton");
        Button numEight = addNumButton(8);
        numEight.setId("numButton");

        playerNumChoice.getChildren().addAll(numTwo, numFour, numSix, numEight);
        playerInfoGroup.getChildren().add(playerNumChoice);

        numTwo.setOnAction(event -> {
            playerInfoGroup.getChildren().addAll(addPlayers(2));
            numTwo.setDisable(true);
            numFour.setDisable(true);
            numSix.setDisable(true);
            numEight.setDisable(true);
        });

        numFour.setOnAction(event -> {
            playerInfoGroup.getChildren().addAll(addPlayers(4));
            numTwo.setDisable(true);
            numFour.setDisable(true);
            numSix.setDisable(true);
            numEight.setDisable(true);
        });

        numSix.setOnAction(event -> {
            playerInfoGroup.getChildren().addAll(addPlayers(6));
            numTwo.setDisable(true);
            numFour.setDisable(true);
            numSix.setDisable(true);
            numEight.setDisable(true);
        });

        numEight.setOnAction(event -> {
            playerInfoGroup.getChildren().addAll(addPlayers(8));
            numTwo.setDisable(true);
            numFour.setDisable(true);
            numSix.setDisable(true);
            numEight.setDisable(true);
        });



        return playerInfoGroup;
    }

    /* the number button constructor */
    private static Button addNumButton(int num) {
        Button numButton = new Button(Integer.toString(num));
        return numButton;
    }

    /* construct the panel to add team and player info */
    private static HBox addPlayers(int numPlayers) {
        HBox teamPanel = new HBox();
        teamPanel.setAlignment(Pos.CENTER);
        teamPanel.setSpacing(30);

        VBox team1 = new VBox();
        Text team1Title = new Text("Team 1");
        team1Title.setFont(Font.font(null, FontWeight.BOLD, 25));
        VBox team1Players = playerOneSide(numPlayers, "team1");
        team1.getChildren().addAll(team1Title, team1Players);

        VBox team2 = new VBox();
        Text team2Title = new Text("Team 2");
        team2Title.setFont(Font.font(null, FontWeight.BOLD, 25));
        VBox team2Players = playerOneSide(numPlayers, "team2");
        team2.getChildren().addAll(team2Title, team2Players);

        Text vsText = new Text("vs");
        vsText.setFill(Color.WHITE);
        vsText.setFont(Font.font("Verdana", 20));

        teamPanel.getChildren().addAll(team1, vsText, team2);

        return teamPanel;
    }

    /* construct the four players on one side */
    private static VBox playerOneSide(int numPlayers, String teamname) {
        VBox playerContainer = new VBox();
        playerContainer.setSpacing(5);

        for (int i = 1; i < numPlayers; i = i +2) {
            HBox onePlayer = new HBox();
            if (teamname.equals("team1")) {
                onePlayer = OnePlayer(i);
            } else if (teamname.equals("team2")) {
                onePlayer = OnePlayer(i + 1);
            }
            onePlayer.setMaxWidth(Double.MAX_VALUE);
            playerContainer.getChildren().add(onePlayer);
        }

        return playerContainer;
    }

    /* construct one player with the input textbox for player name and a color choice*/
    private static HBox OnePlayer(int playerSeq) {
        HBox onePlayer = new HBox();
        onePlayer.setAlignment(Pos.CENTER);
        onePlayer.setMaxWidth(Double.MAX_VALUE);
        onePlayer.setSpacing(2);

        Label playerNum = playerNum(playerSeq);
        playerNum.setTextFill(Color.WHITE);
        playerNum.setFont(Font.font(null, FontWeight.BOLD, 16));
        playerNum.setMaxWidth(Double.MAX_VALUE);
        playerNum.setMaxHeight(Double.MAX_VALUE);
        TextField playerName = enterName();
        playerName.setMaxWidth(Double.MAX_VALUE);
        playerName.setMaxHeight(Double.MAX_VALUE);
        HBox playerColor = chooseColor();
        playerColor.setMaxWidth(Double.MAX_VALUE);
        playerColor.setMaxHeight(Double.MAX_VALUE);

        onePlayer.getChildren().addAll(playerNum, playerName, playerColor);

        return onePlayer;
    }

    /* construct the player name text */
    private static Label playerNum(int num) {
        Label player = new Label();
        player.setText("Player " + Integer.toString(num));

        return player;
    }

    /* construct the textbox to enter player name*/
    private static TextField enterName() {
        TextField enterName = new TextField();
        enterName.prefWidth(20);
        return enterName;
    }

    /* construct the color choice options menu*/
    private static HBox chooseColor() {
        HBox pane = new HBox();

        MenuBar menuBar = new MenuBar();

        Circle menuCircle = new Circle(8);
        menuCircle.setStroke(Color.BLACK);
        menuCircle.setFill(Color.WHITE);
        Menu changeColorMenu = new Menu("", menuCircle);

        MenuItem red = new MenuItem("", new Circle(8, Color.RED));
        red.setOnAction(event -> {
            menuCircle.setFill(Color.RED);
        });
        changeColorMenu.getItems().add(red);

        MenuItem blue = new MenuItem("", new Circle(8, Color.BLUE));
        blue.setOnAction(event -> {
            menuCircle.setFill(Color.BLUE);
        });
        changeColorMenu.getItems().add(blue);

        MenuItem green = new MenuItem("", new Circle(8, Color.GREEN));
        green.setOnAction(event -> {
            menuCircle.setFill(Color.GREEN);
        });
        changeColorMenu.getItems().add(green);

        MenuItem yellow = new MenuItem("", new Circle(8, Color.YELLOW));
        yellow.setOnAction(event -> {
            menuCircle.setFill(Color.YELLOW);
        });
        changeColorMenu.getItems().add(yellow);

        menuBar.getMenus().addAll(changeColorMenu);
        pane.getChildren().add(menuBar);

        return pane;
    }

    public static void main(String[] args) {launch(args);}
}
