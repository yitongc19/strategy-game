package View;

import Model.Building;
import Model.BuildingImpl;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Created by cheny2 on 3/3/17.
 */
public class ConstructBuilding extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();

        VBox leftPanels = new VBox();

        VBox currentBasePanel = addCurrectBasePanel();
        ScrollPane constructBuildingPanel = addBuildingsToConstructPanel();
        leftPanels.getChildren().addAll(currentBasePanel, constructBuildingPanel);

        VBox rightPanels = new VBox();
        rightPanels.setSpacing(200);
        VBox playerInfoPanel = addPlayerInfoPanel();
        VBox functionPanels = addFunctionPanel();
        rightPanels.getChildren().addAll(playerInfoPanel, functionPanels);

        root.setLeft(leftPanels);
        root.setRight(rightPanels);

        Scene scene = new Scene(root, 1400, 1000);
        scene.getStylesheets().add(Fight.class.getResource("static/ConstructBuilding.css").toExternalForm());

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /* Construct the function panel */
    private static VBox addFunctionPanel() {
        VBox functionPanel = new VBox();
        functionPanel.setAlignment(Pos.CENTER);

        HBox timerContainer = new HBox();
        Text timer = new Text("Timer PlaceHolder");
        timerContainer.getChildren().addAll(timer);
        timerContainer.setAlignment(Pos.CENTER);
        HBox nextPlayerButtonContainer = new HBox();
        nextPlayerButtonContainer.setAlignment(Pos.CENTER);
        Button nextPlayerButton = new Button("Next Player");

        nextPlayerButtonContainer.getChildren().addAll(nextPlayerButton);

        functionPanel.getChildren().addAll(timerContainer, nextPlayerButtonContainer);
        return functionPanel;
    }

    /* Construct the buldingsToConstruct panel */
    private static ScrollPane addBuildingsToConstructPanel() {
        ScrollPane constructBuildingPanel = new ScrollPane();
        constructBuildingPanel.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        constructBuildingPanel.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        GridPane buildingContainer = addBuildingContainer();

        constructBuildingPanel.setContent(buildingContainer);

        return constructBuildingPanel;
    }

    /* Construct the building container */
    private static GridPane addBuildingContainer() {
        GridPane buildingContainer = new GridPane();
        buildingContainer.setHgap(40);
        buildingContainer.setVgap(60);

        for (int row = 0; row < 3; row ++) {
            for (int col = 0; col < 3; col ++) {
                VBox singleBuilding = addSingleBuilding();
                buildingContainer.add(singleBuilding, col, row);
            }
        }

        return buildingContainer;
    }
    /*
    private static VBox addSingleBuilding(BuildingImpl building) {
        VBox singleBuildingContainer = new VBox();

        ImageView buildingImage = new ImageView(building.getImagePath());
        return singleBuildingContainer;
    }

    */

    /* Construct a single building in the existing building panel */
    private static VBox addSingleBuilding() {
        VBox singleBuildingContainer = new VBox();

        ImageView buildingImg = new ImageView(ConstructBuilding.class.getResource("static/evilbdt1.gif").toExternalForm());
        buildingImg.setFitWidth(200);
        buildingImg.setPreserveRatio(true);

        Text buildingName = new Text("CupCake House");
        Text buildingCost = new Text("200");

        singleBuildingContainer.getChildren().addAll(buildingImg, buildingName, buildingCost);

        return singleBuildingContainer;
    }

    /* Construct the playerInfo panel */
    private static VBox addPlayerInfoPanel() {
        VBox playerInfoPanel = new VBox();
        playerInfoPanel.setSpacing(20);
        playerInfoPanel.setPadding(new Insets(5, 5, 5, 5));
        playerInfoPanel.setStyle("-fx-border-style: solid");
        playerInfoPanel.setPrefSize(500, 500);

        Text titleText = new Text("Player Info: ");

        Text playerName = new Text("Player Name: Yitong");

        Text playerColor = new Text("Player Color: Red");

        Text playerGold = new Text("Player Gold: 1000");

        Text playerScore = new Text("Player Score: 600");

        Text playerRank = new Text("Player Rank: #4");

        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.BASELINE_RIGHT);
        Button showMapButton = new Button("Show Map");
        buttonContainer.getChildren().addAll(showMapButton);

        playerInfoPanel.getChildren().addAll(titleText, playerName, playerColor, playerGold,
                playerScore, playerRank, buttonContainer);

        return playerInfoPanel;
    }

    private static VBox addCurrectBasePanel() {
        VBox currentBasePanel = new VBox();
        currentBasePanel.setPadding(new Insets(10, 10, 10, 10));

        Text title = new Text("Existing Buildings");
        StackPane currentBase = addCurrentBase();

        currentBasePanel.getChildren().addAll(title, currentBase);

        return currentBasePanel;
    }

    private static StackPane addCurrentBase() {
        StackPane currentBase = new StackPane();

        GridPane baseGrid = addBaseGrid();

        GridPane baseBuilding = addBaseBuildings();

        baseGrid.setPadding(new Insets(20, 0, 0, 100));
        baseBuilding.setPadding(new Insets(20, 0, 0, 100));

        currentBase.getChildren().addAll(baseGrid, baseBuilding);

        return currentBase;
    }

    private static GridPane addBaseGrid() {
        GridPane baseGrid = new GridPane();

        baseGrid.getRowConstraints().add(new RowConstraints(100));
        baseGrid.getColumnConstraints().add(new ColumnConstraints(100));

        for (int row = 0; row < 5; row ++) {
            for (int col = 0; col < 5; col ++) {
                Rectangle grid = new Rectangle(100, 100);
                grid.setFill(Color.TRANSPARENT);
                grid.setStroke(Color.BLACK);
                grid.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
                baseGrid.add(grid, col, row);
            }
        }

        return baseGrid;
    }

    private static GridPane addBaseBuildings() {
        GridPane baseBuildings = new GridPane();

        baseBuildings.getRowConstraints().add(new RowConstraints(100));
        baseBuildings.getColumnConstraints().add(new ColumnConstraints(100));

        for (int row = 0; row < 5; row ++) {
            for (int col = 0; col < 5; col ++) {
                Circle placeHolder = new Circle(50);
                placeHolder.setFill(Color.TRANSPARENT);
                placeHolder.setStroke(Color.BLACK);
                placeHolder.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
                baseBuildings.add(placeHolder, col, row);
            }
        }

        return baseBuildings;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
