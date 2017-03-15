package View;

import Controller.GameController;
import Model.PlayerImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.control.Menu;

import java.util.*;
import java.util.Map;

/**
 * Created by cheny2 on 3/1/17.
 */
public class InitiateGame extends Application{

    static Stage initiateStage = new Stage();
    public GameController controller;
    private static List<Map<String, Object>> playeInfoList = new ArrayList<>();

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
    private VBox addPanel() {
        VBox panel = new VBox();
        panel.setSpacing(30);
        Text instruction = addInstruction();

        VBox choooseNumPlayer = addPlayerNumChoice();

        Button start = new Button("Start");
        start.setId("startButton");

        start.setOnAction(event -> {
            for (Map<String, Object> player : playeInfoList) {
                Paint color = (Paint) player.get("playerColor");
                TextField nameInput = (TextField) player.get("playerNameInput");
                Integer teamNum = (Integer) player.get("teamNum");
                String name = nameInput.getText();

                PlayerImpl newPlayer = new PlayerImpl(teamNum, name, color);
                controller.addPlayer(newPlayer);
            }

            System.out.println(controller.getPlayers().get(0).getPlayerName());
            ConstructBuilding constructBuilding = new ConstructBuilding(this.controller);
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
        instruction.setFont(Font.font("", FontWeight.BOLD, 25));

        return instruction;
    }

    /* Container for the number of players buttons */
    private VBox addPlayerNumChoice() {
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

        numTwo.setOnAction((ActionEvent event) -> {
            numTwo.setDisable(true);
            numFour.setDisable(true);
            numSix.setDisable(true);
            numEight.setDisable(true);
            this.controller = new GameController(2);
            playerInfoGroup.getChildren().addAll(addPlayers(2, controller));
        });

        numFour.setOnAction(event -> {
            numTwo.setDisable(true);
            numFour.setDisable(true);
            numSix.setDisable(true);
            numEight.setDisable(true);
            this.controller = new GameController(4);
            playerInfoGroup.getChildren().addAll(addPlayers(4, controller));
        });

        numSix.setOnAction(event -> {
            numTwo.setDisable(true);
            numFour.setDisable(true);
            numSix.setDisable(true);
            numEight.setDisable(true);
            this.controller = new GameController(6);
            playerInfoGroup.getChildren().addAll(addPlayers(6, controller));
        });

        numEight.setOnAction(event -> {
            numTwo.setDisable(true);
            numFour.setDisable(true);
            numSix.setDisable(true);
            numEight.setDisable(true);
            this.controller = new GameController(8);
            playerInfoGroup.getChildren().addAll(addPlayers(8, controller));
        });



        return playerInfoGroup;
    }

    /* the number button constructor */
    private static Button addNumButton(int num) {
        Button numButton = new Button(Integer.toString(num));
//        numButton.setPrefSize(20, 20);
        return numButton;
    }

    /* construct the panel to add team and player info */
    private static VBox addPlayers(int numPlayers, GameController controller) {
        VBox teamPanel = new VBox();
        teamPanel.setSpacing(10);
        teamPanel.setPrefWidth(800);
        teamPanel.setAlignment(Pos.CENTER);

        Text instruction = new Text("Now type in your user name and choose a color");
        instruction.setFont(Font.font(null, FontWeight.BOLD, 20));

        HBox teams = new HBox();
        teams.setAlignment(Pos.CENTER);
        teams.setSpacing(30);

        VBox team1 = new VBox();
        Text team1Title = new Text("Team 1");
        team1Title.setFont(Font.font(null, FontWeight.BOLD, 22));
        VBox team1Players = playerOneSide(numPlayers, "team1", controller);
        team1.getChildren().addAll(team1Title, team1Players);

        VBox team2 = new VBox();
        Text team2Title = new Text("Team 2");
        team2Title.setFont(Font.font(null, FontWeight.BOLD, 22));
        VBox team2Players = playerOneSide(numPlayers, "team2", controller);
        team2.getChildren().addAll(team2Title, team2Players);

        Text vsText = new Text("vs");
        vsText.setFont(Font.font("Verdana", 20));

        teams.getChildren().addAll(team1, vsText, team2);

        teamPanel.getChildren().addAll(instruction, teams);

        return teamPanel;
    }

    /* construct the four players on one side */
    private static VBox playerOneSide(int numPlayers, String teamname, GameController controller) {
        VBox playerContainer = new VBox();
        playerContainer.setPadding(new Insets(20, 20, 20, 20));
        playerContainer.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 1.2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;");
        playerContainer.setSpacing(5);

        for (int i = 1; i < numPlayers; i = i +2) {
            HBox onePlayer = new HBox();
            if (teamname.equals("team1")) {
                onePlayer = OnePlayer(i, controller);
            } else if (teamname.equals("team2")) {
                onePlayer = OnePlayer(i + 1, controller);
            }
            onePlayer.setMaxWidth(Double.MAX_VALUE);
            playerContainer.getChildren().add(onePlayer);
        }

        return playerContainer;
    }

    /* construct one player with the input textbox for player name and a color choice*/
    private static HBox OnePlayer(int playerSeq, GameController controller) {
        java.util.Map<String, Object> playerInfo = new HashMap<>();

        int teamNum = 0;

        if (playerSeq % 2 == 1) {
            teamNum = 1;
        } else {
            teamNum = 2;
        }

        playerInfo.put("teamNum", teamNum);

        HBox onePlayer = new HBox();
        onePlayer.setAlignment(Pos.CENTER);
        onePlayer.setMaxWidth(Double.MAX_VALUE);
        onePlayer.setSpacing(2);

        Label playerNum = playerNum(playerSeq);
        playerNum.setFont(Font.font(null, FontWeight.BOLD, 16));
        playerNum.setMaxWidth(Double.MAX_VALUE);
        playerNum.setMaxHeight(Double.MAX_VALUE);
        TextField playerName = enterName();

        playerInfo.put("playerNameInput", playerName);

        playerName.setMaxWidth(Double.MAX_VALUE);
        playerName.setMaxHeight(Double.MAX_VALUE);

        final Paint[] colorForOne = {Color.TRANSPARENT};

        HBox playerColor = new HBox();

        MenuBar menuBar = new MenuBar();

        Rectangle defaultColor = new Rectangle(20, 15);
        defaultColor.setStroke(Color.GRAY);
        defaultColor.setFill(Color.GREY);
        Menu changeColorMenu = new Menu("", defaultColor);

        List<Paint> colorList = new ArrayList<>();

        colorList.add(Color.rgb(153, 204, 204));
        colorList.add(Color.rgb(153, 179, 255));
        colorList.add(Color.rgb(179, 153, 255));
        colorList.add(Color.rgb(204, 153, 204));
        colorList.add(Color.rgb(230, 153, 179));
        colorList.add(Color.rgb(255, 153, 153));
        colorList.add(Color.rgb(255, 179, 128));
        colorList.add(Color.rgb(255, 204, 153));
        colorList.add(Color.rgb(255, 230, 128));
        colorList.add(Color.rgb(255, 255, 128));
        colorList.add(Color.rgb(230, 230, 153));
        colorList.add(Color.rgb(153, 204, 153));

        for (Paint color : colorList) {
            MenuItem item = new MenuItem("", new Rectangle(20, 15, color));
            item.setOnAction(event -> {
                defaultColor.setFill(color);
                defaultColor.setStroke(Color.TRANSPARENT);
                colorForOne[0] = color;
            });
            changeColorMenu.getItems().add(item);
        }
        playerInfo.put("playerColor", colorForOne[0]);

        menuBar.getMenus().addAll(changeColorMenu);
        playerColor.getChildren().add(menuBar);


        playerColor.setMaxWidth(Double.MAX_VALUE);
        playerColor.setMaxHeight(Double.MAX_VALUE);

        onePlayer.getChildren().addAll(playerNum, playerName, playerColor);

        playeInfoList.add(playerInfo);

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

    public static void main(String[] args) {launch(args);}
}
