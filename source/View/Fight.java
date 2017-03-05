package View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.omg.CORBA.TIMEOUT;

/**
 * Created by cheny2 on 3/3/17.
 */
public class Fight extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        ScrollPane battleLog = addBattleLog();
        VBox battleField = addBattleField();

        root.setRight(battleLog);
        root.setCenter(battleField);

        Scene scene = new Scene(root, 2000, 1000);
        scene.getStylesheets().add(Fight.class.getResource("static/Fight.css").toExternalForm());

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*
    Construct the battelField container pane with all the elements.
     */
    private static VBox addBattleField() {
        VBox battleFieldContainer = new VBox();

        HBox titleContainer = new HBox();
        titleContainer.setAlignment(Pos.CENTER);
        Text title = addTitle();

        title.setFont(Font.font(null, FontWeight.BOLD, 30));
        ScrollPane map = addMap();
        VBox buffPanel = addBuffPanel();

        titleContainer.getChildren().addAll(title);
        battleFieldContainer.getChildren().addAll(titleContainer, map, buffPanel);

        return battleFieldContainer;
    }

    /* Construct the map to display*/
    private static ScrollPane addMap() {
        ScrollPane mapContainer = new ScrollPane();

        mapContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        mapContainer.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        StackPane laneContainer = new StackPane();
        ImageView laneBackground = new ImageView(Fight.class.getResource("static/backgroundFight.gif").toExternalForm());
        laneBackground.setFitWidth(1400);
        laneBackground.setFitHeight(1040);

        VBox fourLaneMap = new VBox();

        StackPane lane1 = addNormalLane(Color.LIGHTGREEN, Color.LIGHTBLUE);
        StackPane lane2 = addNormalLane(Color.LIGHTCORAL, Color.LIGHTCYAN);
        StackPane kingLane = addKingLane();
        StackPane lane3 = addNormalLane(Color.LIGHTPINK, Color.LIGHTGOLDENRODYELLOW);
        StackPane lane4 = addNormalLane(Color.LIGHTYELLOW, Color.LIGHTGRAY);

        fourLaneMap.getChildren().addAll(lane1, lane2, kingLane, lane3, lane4);

        laneContainer.getChildren().addAll(laneBackground, fourLaneMap);
        mapContainer.setContent(laneContainer);

        return mapContainer;
    }
//
//    public static void drawMinions(List<PlayerImpl> players) {
//        //for each player, for each minion, draw everything
//    }

    /* Construct the king Lane*/
    private static StackPane addKingLane() {
        StackPane laneHolder = new StackPane();
        HBox lane = new HBox();

        ImageView king1 = addKing("static/crown.png");
        Rectangle road = addRoad(920, 240);
        ImageView king2 = addKing("static/devil.png");

        lane.getChildren().addAll(king1, road, king2);
        laneHolder.getChildren().add(lane);

        return laneHolder;
    }

    /* Construct the king base */
    private static ImageView addKing(String url) {
        ImageView kingImageView = new ImageView(Fight.class.getResource(url).toExternalForm());
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

        GridPane base1 = addBase(color1);
        Rectangle road = addRoad(1000, 200);
        GridPane base2 = addBase(color2);

        lane.getChildren().addAll(base1, road, base2);
        laneHolder.getChildren().add(lane);

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
        base.getColumnConstraints().add(new ColumnConstraints(40));
        base.getRowConstraints().add(new RowConstraints(40));

        for (int col = 0; col < 5; col ++) {
            for (int row = 0; row < 5; row ++) {
                addBuilding(base, col, row, Color.TRANSPARENT);
            }
        }

        return base;
    }

    /* Construct a building in a base */
    private static void addBuilding(GridPane pane, int columnNum, int rowNum, Paint baseColor) {
        ImageView buildingBase = new ImageView(Fight.class.getResource("static/bdbase.gif").toExternalForm());
        buildingBase.setFitHeight(40);
        buildingBase.setFitWidth(40);

//        Circle placeHolder = new Circle(20);
//        placeHolder.setStroke(baseColor);
//        placeHolder.setStrokeDashOffset(5d);
        pane.add(buildingBase, columnNum, rowNum);
    }

    /* Construct the game title*/
    private static Text addTitle() {
        Text gameTitle = new Text("BattleField");
        gameTitle.setFill(Color.BEIGE);
        gameTitle.setId("gameTitle");
        return gameTitle;
    }

    /* Construct the buff panel*/
    private static VBox addBuffPanel() {
        VBox buffPanel = new VBox();
        buffPanel.prefHeight(200);
        buffPanel.setAlignment(Pos.CENTER);

        Text buffTitle = new Text("SHOW YOUR LOYALTY!");
        buffTitle.setFont(Font.font(null, 30));
        buffTitle.setFill(Color.WHITE);

        HBox buffButtons = addBuffs();

        buffPanel.getChildren().addAll(buffTitle, buffButtons);

        return buffPanel;
    }

    /* add the buff buttons*/
    private static HBox addBuffs() {
        HBox buffButtonContainer = new HBox();
        buffButtonContainer.setAlignment(Pos.CENTER);
        buffButtonContainer.setSpacing(400);

        GridPane buffTeam1 = addBuffButton("TEAM 1");
        GridPane buffTeam2 = addBuffButton("TEAM 2");

        buffButtonContainer.getChildren().addAll(buffTeam1, buffTeam2);

        return buffButtonContainer;
    }

    /* Construct buff buttons*/
    private static GridPane addBuffButton(String team) {
        GridPane buffButton = new GridPane();

        Text teamName = new Text(team);
        teamName.setFill(Color.WHITE);
        teamName.setFont(Font.font(null, 25));

        Button buff = new Button("BUFF");

        buffButton.add(teamName, 0, 0);
        buffButton.add(buff, 1, 1);

        return buffButton;
    }

    /* Construct the battleLogs */
    private static ScrollPane addBattleLog() {
        ScrollPane battleLogContainer = new ScrollPane();
        battleLogContainer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        battleLogContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        VBox battleLog = new VBox();
        battleLog.setStyle("-fx-background-color: white");

        Text battleLogTitle = new Text("BattleLog");

        HBox msg1 = updateMessage("Yitong", "Minions march forward!", Color.RED);
        HBox msg2 = updateMessage("Russell", "Minions march forward!", Color.GREEN);
        HBox msg3 = updateMessage("Yitong", "For the KING", Color.RED);

        Button startButton = new Button("Start");
        startButton.setId("startButton");

        battleLog.getChildren().addAll(battleLogTitle, msg1, msg2, msg3, startButton);

        battleLogContainer.setContent(battleLog);
        return battleLogContainer;
    }

    private static HBox updateMessage(String playerName, String msg, Paint playerColor) {
        HBox message = new HBox();

        Text player = new Text(playerName + ": ");
        player.setFill(playerColor);

        Text playerStatus = new Text(msg);

        message.getChildren().addAll(player, playerStatus);

        return message;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
