package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by cheny2 on 3/3/17.
 */
public class Fight extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        VBox battleLog = addBattleLog();
        VBox battleField = addBattleField();

        root.setRight(battleLog);
        root.setCenter(battleField);

        Scene scene = new Scene(root, 1200, 780);
        scene.getStylesheets().add(Fight.class.getResource("Fight.css").toExternalForm());

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static VBox addBattleField() {
        VBox battleFieldContainer = new VBox();

        Text title = addTitle();
        VBox map = addMap();
        VBox buffPanel = addBuffPanel();

        battleFieldContainer.getChildren().addAll(title, map, buffPanel);

        return battleFieldContainer;
    }

    private static VBox addMap() {
        VBox fourLaneMap = new VBox();

        StackPane lane1 = addNormalLane(Color.LIGHTGREEN, Color.LIGHTBLUE);
        StackPane lane2 = addNormalLane(Color.LIGHTCORAL, Color.LIGHTCYAN);
        StackPane kingLane = addKingLane();
        StackPane lane3 = addNormalLane(Color.LIGHTPINK, Color.LIGHTGOLDENRODYELLOW);
        StackPane lane4 = addNormalLane(Color.LIGHTYELLOW, Color.LIGHTGRAY);

        fourLaneMap.getChildren().addAll(lane1, lane2, kingLane, lane3, lane4);

        return fourLaneMap;
    }
//
//    public static void drawMinions(List<PlayerImpl> players) {
//        //for each player, for each minion, draw everything
//    }

    private static StackPane addKingLane() {
        StackPane laneHolder = new StackPane();
        HBox lane = new HBox();

        ImageView king1 = addKing("black_cat_face.png");
        Rectangle road = addRoad(760, 120);
        ImageView king2 = addKing("cat_face_yellow_eyes.png");

        lane.getChildren().addAll(king1, road, king2);
        laneHolder.getChildren().add(lane);

        return laneHolder;
    }

    private static ImageView addKing(String url) {
        ImageView kingImageView = new ImageView(Fight.class.getResource(url).toExternalForm());
        kingImageView.setFitHeight(120);
        kingImageView.setFitWidth(120);
        kingImageView.setSmooth(true);
        kingImageView.setCache(true);

        return kingImageView;
    }

    private static StackPane addNormalLane(Paint color1, Paint color2) {
        StackPane laneHolder = new StackPane();
        HBox lane = new HBox();

        GridPane base1 = addBase(color1);
        Rectangle road = addRoad(800, 100);
        GridPane base2 = addBase(color2);

        lane.getChildren().addAll(base1, road, base2);
        laneHolder.getChildren().add(lane);

        return laneHolder;
    }

    private static Rectangle addRoad(int roadWidth, int roadHeight) {
        Rectangle road = new Rectangle(roadWidth, roadHeight);
        road.setStroke(Color.BURLYWOOD);
        road.setStrokeType(StrokeType.CENTERED);

        return road;
    }
    private static GridPane addBase(Paint baseColor) {
        GridPane base = new GridPane();
        base.setAlignment(Pos.CENTER);
        base.getColumnConstraints().add(new ColumnConstraints(20));
        base.getRowConstraints().add(new RowConstraints(20));

        addBuilding(base, 0, 0, baseColor);
        addBuilding(base, 1, 0, baseColor);
        addBuilding(base, 2, 0, baseColor);
        addBuilding(base, 3, 0, baseColor);
        addBuilding(base, 4, 0, baseColor);

        addBuilding(base, 0, 1, baseColor);
        addBuilding(base, 0, 2, baseColor);
        addBuilding(base, 0, 3, baseColor);
        addBuilding(base, 0, 4, baseColor);
        addBuilding(base, 4, 4, baseColor);

        return base;
    }

    private static void addBuilding(GridPane pane, int columnNum, int rowNum, Paint baseColor) {
        Circle placeHolder = new Circle(10, baseColor);
        pane.add(placeHolder, columnNum, rowNum);
    }

    private static Text addTitle() {
        Text gameTitle = new Text("BattleField");
        gameTitle.setFill(Color.BEIGE);
        gameTitle.setId("gameTitle");
        return gameTitle;
    }

    private static VBox addBuffPanel() {
        VBox buffPanel = new VBox();

        buffPanel.setStyle("-fx-background-color: #dcca8a");

        Text buffTitle = new Text("SHOW YOUR LOYALTY!");
        buffTitle.setFill(Color.WHITE);

        HBox buffButtons = addBuffs();

        buffPanel.getChildren().addAll(buffTitle, buffButtons);

        return buffPanel;
    }

    private static HBox addBuffs() {
        HBox buffButtonContainer = new HBox();

        GridPane buffTeam1 = addBuffButton("TEAM 1");
        GridPane buffTeam2 = addBuffButton("TEAM 2");

        buffButtonContainer.getChildren().addAll(buffTeam1, buffTeam2);

        return buffButtonContainer;
    }

    private static GridPane addBuffButton(String team) {
        GridPane buffButton = new GridPane();

        Text teamName = new Text(team);
        teamName.setFill(Color.WHITE);

        Button buff = new Button("BUFF");

        buffButton.add(teamName, 0, 0);
        buffButton.add(buff, 1, 1);

        return buffButton;
    }

    private static VBox addBattleLog() {
        VBox battleLog = new VBox();
        battleLog.setStyle("-fx-background-color: white");

        Text battleLogTitle = new Text("BattleLog");

        HBox msg1 = updateMessage("Yitong", "Minions march forward!", Color.RED);
        HBox msg2 = updateMessage("Russell", "Minions march forward!", Color.GREEN);
        HBox msg3 = updateMessage("Yitong", "For the KING", Color.RED);

        battleLog.getChildren().addAll(battleLogTitle, msg1, msg2, msg3);

        return battleLog;
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
