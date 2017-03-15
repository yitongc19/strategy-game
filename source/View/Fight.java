package View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import Model.*;
import Controller.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Stack;


/**
 * Created by cheny2 on 3/3/17.
 */
public class Fight extends Application{

    static Stage fightStage = new Stage();
    public GameController controller;

    public Fight(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        System.out.println(controller.getNumPlayerTeam1());
        System.out.println(controller.getPlayers().get(0).getBuildings().size());
        fightStage = primaryStage;

        BorderPane root = new BorderPane();

        FightController control = new FightController();
        CombatManager manager = new CombatManager();

        GameController newGame = this.controller;
        newGame.setUpGame();
        List<PlayerImpl> players = newGame.getPlayers();

        control.setPlayers(players);

        Canvas canvas = new Canvas( 1400, 1120 );
        Canvas canvas1 = new Canvas( 1400, 1120 );
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        GraphicsContext graphics1 = canvas1.getGraphicsContext2D();

        ScrollPane battleLog = addBattleLog(primaryStage, manager, control, graphics, graphics1);
        VBox battleField = addBattleField(this.controller, canvas, canvas1);

        root.setRight(battleLog);
        root.setCenter(battleField);

        Scene scene = new Scene(root, 1800, 1330);
        scene.getStylesheets().add(Fight.class.getResource("static/Fight.css").toExternalForm());

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        control.showBuildings(graphics1);
    }

    /*
    Construct the battelField container pane with all the elements.
     */
    private static VBox addBattleField(GameController controller, Canvas canvas, Canvas canvas1) {
        VBox battleFieldContainer = new VBox();

        HBox titleContainer = new HBox();
        titleContainer.setAlignment(Pos.CENTER);
        Text title = addTitle();

        title.setFont(Font.font(null, FontWeight.BOLD, 30));
        title.setId("titleLine");
        ScrollPane map = addMap(canvas, canvas1);
        VBox buffPanel = addBuffPanel(controller);

        titleContainer.getChildren().addAll(title);
        battleFieldContainer.getChildren().addAll(titleContainer, map, buffPanel);

        return battleFieldContainer;
    }

    /* Construct the map to display*/
    private static ScrollPane addMap(Canvas canvas, Canvas canvas1) {
        ScrollPane mapContainer = new ScrollPane();

        mapContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        mapContainer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mapContainer.prefWidth(1400);

        StackPane laneContainer = new StackPane();
        Image temp = new Image("file:assets/resourcesImg/landscape.png");
        ImageView laneBackground = new ImageView(temp);
        laneBackground.setFitWidth(1400);
        laneBackground.setFitHeight(1120);

        VBox fourLaneMap = new VBox();

        StackPane lane1 = addNormalLane(Color.LIGHTGREEN, Color.LIGHTBLUE);

        StackPane lane2 = addNormalLane(Color.LIGHTCORAL, Color.LIGHTCYAN);
        StackPane kingLane = addKingLane();
        StackPane lane3 = addNormalLane(Color.LIGHTPINK, Color.LIGHTGOLDENRODYELLOW);
        StackPane lane4 = addNormalLane(Color.LIGHTYELLOW, Color.LIGHTGRAY);

        fourLaneMap.getChildren().addAll(lane1, addLaneSeparator(), lane2,
                addLaneSeparator(), kingLane, addLaneSeparator(), lane3, addLaneSeparator(), lane4);

        laneContainer.getChildren().addAll(laneBackground, fourLaneMap, canvas, canvas1);
        mapContainer.setContent(laneContainer);

        return mapContainer;
    }
//
//    public static void drawMinions(List<PlayerImpl> players) {
//        //for each player, for each minion, draw everything
//    }

    /* Construct a lane separator */
    private static Rectangle addLaneSeparator() {
        Rectangle laneSeparator = new Rectangle(1400, 20);
        laneSeparator.setFill(Color.rgb(255, 179, 102));
        laneSeparator.setStroke(Color.TRANSPARENT);
        return laneSeparator;
    }
    /* Construct the king Lane*/
    private static StackPane addKingLane() {
        StackPane laneHolder = new StackPane();
        HBox lane = new HBox();

        ImageView king1 = addKing("file:assets/resourcesImg/king1.png");
        Rectangle road = addRoad(920, 240);
        ImageView king2 = addKing("file:assets/resourcesImg/king2.png");

        lane.getChildren().addAll(king1, road, king2);
        laneHolder.getChildren().add(lane);

        return laneHolder;
    }

    /* Construct the king base */
    private static ImageView addKing(String url) {
        Image temp = new Image(url);
        ImageView kingImageView = new ImageView(temp);
        kingImageView.setFitHeight(240);
        kingImageView.setFitWidth(240);
        kingImageView.setSmooth(true);
        kingImageView.setCache(true);

        return kingImageView;
    }

    /* Construct a normal lane */
    private static StackPane addNormalLane(Paint color1, Paint color2) {
        StackPane laneHolder = new StackPane();
        HBox lane = new HBox();
        HBox laneBuildingBase = new HBox();

        GridPane buildingBase1 = addBase(Color.TRANSPARENT);
        GridPane buildingBase2 = addBase(Color.TRANSPARENT);
        GridPane base1 = addBaseLight(color1);
        Rectangle roadBase = addRoad(1000, 200);
        Rectangle road = addRoad(1000, 200);
        GridPane base2 = addBaseDark(color2);

        laneBuildingBase.getChildren().addAll(buildingBase1, roadBase, buildingBase2);
        lane.getChildren().addAll(base1, road, base2);
        laneHolder.getChildren().addAll(laneBuildingBase, lane);

        return laneHolder;
    }

    /* construct a road */
    private static Rectangle addRoad(int roadWidth, int roadHeight) {
        Rectangle road = new Rectangle(roadWidth, roadHeight);

        road.setFill(Color.TRANSPARENT);
        return road;
    }

    /* Construct bases in a normal lane */
    private static GridPane addBase(Paint baseColor) {
        GridPane base = new GridPane();
        base.setAlignment(Pos.CENTER);
        base.getColumnConstraints().add(new ColumnConstraints(50));
        base.getRowConstraints().add(new RowConstraints(50));

        for (int col = 0; col < 4; col ++) {
            for (int row = 0; row < 4; row ++) {
                addBuilding(base, col, row, Color.TRANSPARENT, "file:assets/swordmanT1/buildbase.gif");
            }
        }
        return base;
    }

    private static GridPane addBaseLight(Paint baseColor) {
        GridPane base = new GridPane();
        base.setAlignment(Pos.CENTER);
        base.getColumnConstraints().add(new ColumnConstraints(50));
        base.getRowConstraints().add(new RowConstraints(50));
        /*
        addBuilding(base, 0, 0, Color.TRANSPARENT, "file:assets/swordmanT1/t1buildgood.gif");
        addBuilding(base, 1, 1, Color.TRANSPARENT, "file:assets/swordmanT1/t1buildgood.gif");
        addBuilding(base, 2, 2, Color.TRANSPARENT, "file:assets/swordmanT1/t1buildgood.gif");
        addBuilding(base, 3, 3, Color.TRANSPARENT, "file:assets/swordmanT1/t1buildgood.gif");
        */
        return base;
    }

    private static GridPane addBaseDark(Paint baseColor) {
        GridPane base = new GridPane();
        base.setAlignment(Pos.CENTER);
        base.getColumnConstraints().add(new ColumnConstraints(50));
        base.getRowConstraints().add(new RowConstraints(50));
        /*
        addBuilding(base, 0, 0, Color.TRANSPARENT, "file:assets/swordmanT1/t1buildevil.gif");
        addBuilding(base, 1, 1, Color.TRANSPARENT, "file:assets/swordmanT1/t1buildevil.gif");
        addBuilding(base, 2, 2, Color.TRANSPARENT, "file:assets/swordmanT1/t1buildevil.gif");
        addBuilding(base, 3, 3, Color.TRANSPARENT, "file:assets/swordmanT1/t1buildevil.gif");
        */
        return base;
    }

    /* Construct a building in a base */
    private static void addBuilding(GridPane pane, int columnNum, int rowNum, Paint baseColor, String imagePath) {
        Image temp = new Image(imagePath, 50, 50, true, true);
        ImageView buildingBase = new ImageView(temp);
        buildingBase.setFitHeight(50);
        buildingBase.setFitWidth(50);

//        Circle placeHolder = new Circle(20);
//        placeHolder.setStroke(baseColor);
//        placeHolder.setStrokeDashOffset(5d);
        pane.add(buildingBase, columnNum, rowNum);
    }

    /* Construct the game title*/
    private static Text addTitle() {
        Text gameTitle = new Text("BattleField");
        gameTitle.setFont(Font.font("American Typewriter", 30));
        gameTitle.setId("gameTitle");
        return gameTitle;
    }

    /* Construct the buff panel*/
    private static VBox addBuffPanel(GameController controller) {
        VBox buffPanel = new VBox();
        buffPanel.prefHeight(200);
        buffPanel.setAlignment(Pos.CENTER);

        Text buffTitle = new Text("SHOW YOUR LOYALTY!");
        buffTitle.setFont(Font.font("American Typewriter", 30));

        HBox buffButtons = addBuffs(controller);

        buffPanel.getChildren().addAll(buffTitle, buffButtons);

        return buffPanel;
    }

    /* add the buff buttons*/
    private static HBox addBuffs(GameController controller) {
        HBox buffButtonContainer = new HBox();
        buffButtonContainer.setAlignment(Pos.CENTER);
        buffButtonContainer.setSpacing(400);
        buffButtonContainer.setPadding(new Insets(0, 0, 20, 0));

        GridPane buffTeam1 = addBuffButton("TEAM 1", controller);
        GridPane buffTeam2 = addBuffButton("TEAM 2", controller);

        buffButtonContainer.getChildren().addAll(buffTeam1, buffTeam2);

        return buffButtonContainer;
    }

    /* Construct buff buttons*/
    private static GridPane addBuffButton(String team, GameController controller) {
        GridPane buffButton = new GridPane();
        buffButton.setPadding(new Insets(40, 40, 40, 40));
        buffButton.setStyle( "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
        "-fx-border-color: darkred");

        Text teamName = new Text(team);
        teamName.setFont(Font.font("Herculanum", FontWeight.BOLD, 25));

        Button buff = new Button("BUFF");
        buff.setId("buffButton");

        buff.setOnAction(event -> {
            if (team.equals("TEAM 1")) {
                controller.setTotalBuffTeam1(controller.getTotalBuffTeam1() + 1);
                if (controller.getTotalBuffTeam1() >= controller.getNumPlayerTeam1() ) {
                    buff.setDisable(true);
                }
            } else if (team.equals("TEAM 2")) {
                controller.setTotalBuffTeam2(controller.getTotalBuffTeam2() + 1);
                if (controller.getTotalBuffTeam2() >= controller.getNumPlayerTeam2()) {
                    buff.setDisable(true);
                }
            }
        });

        buffButton.add(teamName, 0, 0);
        buffButton.add(buff, 1, 1);

        return buffButton;
    }

    /* Construct the battleLogs */
    private static ScrollPane addBattleLog(Stage curStage, CombatManager manager, FightController controller, GraphicsContext graphics, GraphicsContext graphics1) {

        ScrollPane tempContainer = new ScrollPane();

        BorderPane battleLog = new BorderPane();
        battleLog.prefHeight(1300);
        battleLog.prefWidth(300);

        VBox battleLogContent = new VBox();

        battleLog.setStyle("-fx-background-color: white");

        Text battleLogTitle = new Text("BattleLog");
        battleLogTitle.setFont(Font.font(null, 20));

        TextArea ta = new TextArea();
        ta.setPrefWidth(300);
        ta.setPrefHeight(1200);
        ta.setMaxWidth(300);
        ta.setMinHeight(1200);
        ta.setFont(Font.font("American Typewriter", 14));

        Console console = new Console(ta);

        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        System.setErr(ps);

        Button startButton = addStartButton(curStage, manager, controller, graphics, graphics1);
        startButton.setId("startButton");

        battleLogContent.getChildren().addAll(battleLogTitle, ta);

        battleLog.setCenter(battleLogContent);
        battleLog.setBottom(startButton);

        tempContainer.setContent(battleLog);
        return tempContainer;
    }

    private static HBox updateMessage(String playerName, String msg, Paint playerColor) {
        HBox message = new HBox();

        Text player = new Text(playerName + ": ");
        player.setFill(playerColor);
        player.setFont(Font.font("Times New Roman", 16));

        Text playerStatus = new Text(msg);
        playerStatus.setFont(Font.font("Times New Roman", 16));

        message.getChildren().addAll(player, playerStatus);

        return message;
    }

    private static Button addStartButton(Stage curStage, CombatManager manager, FightController controller, GraphicsContext graphics, GraphicsContext graphics1) {
        Button startFightButton = new Button("Start");
        startFightButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.runFight(manager, graphics, graphics1);
            }
        });
        return startFightButton;
    }

    public static class Console extends OutputStream {

        private TextArea output;

        public Console(TextArea ta) {
            this.output = ta;
        }

        @Override
        public void write(int i) throws IOException {
            output.appendText(String.valueOf((char) i));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
