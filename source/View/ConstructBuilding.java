package View;

import Model.Building;
import Model.BuildingImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.MouseEvent;


/**
 * Created by cheny2 on 3/3/17.
 */
public class ConstructBuilding extends Application {

    private static Integer STARTTIME = 59;

    static Stage constructStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {

        constructStage = primaryStage;

        Font.loadFont(ConstructBuilding.class.getResource("resources/fonts/digital-7.ttf").toExternalForm(), 30);
        BorderPane root = new BorderPane();

        VBox leftPanels = new VBox();
        leftPanels.setSpacing(20);

        VBox currentBasePanel = addCurrectBasePanel();
        ScrollPane constructBuildingPanel = addBuildingsToConstructPanel();
        leftPanels.getChildren().addAll(currentBasePanel, constructBuildingPanel);

        VBox rightPanels = new VBox();
        rightPanels.setSpacing(200);
        VBox playerInfoPanel = addPlayerInfoPanel();
        VBox functionPanels = addFunctionPanel(primaryStage);
        rightPanels.getChildren().addAll(playerInfoPanel, functionPanels);

        root.setLeft(leftPanels);
        root.setRight(rightPanels);

        root.setPadding(new Insets(30, 50, 30, 50));

        Scene scene = new Scene(root, 1400, 1000);
        scene.getStylesheets().add(Fight.class.getResource("static/ConstructBuilding.css").toExternalForm());

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /* Construct the function panel */
    private static VBox addFunctionPanel(Stage fightStage) {
        VBox functionPanel = new VBox();
        functionPanel.setPadding(new Insets(10, 10, 10, 10));
        functionPanel.setSpacing(5);
        functionPanel.setAlignment(Pos.CENTER);
        functionPanel.setId("functionPanel");

        HBox timerContainer = new HBox();

        Label timerLabel = new Label("00:" + STARTTIME.toString());
        timerLabel.setId("timer");
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setFont(Font.font(null, 20));

        timerContainer.getChildren().addAll(timerLabel);
        timerContainer.setAlignment(Pos.CENTER);

        Button startTimerButton = new Button("Start Timer");
        startTimerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Timeline timeline = new Timeline();

                if (timeline != null) {
                    timeline.stop();
                }

                final Integer[] timeSeconds = {STARTTIME};

                timerLabel.setText("00:" + timeSeconds[0].toString());
                timeline = new Timeline();
                timeline.setCycleCount(Timeline.INDEFINITE);
                Timeline finalTimeline = timeline;
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1),
                                new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        timeSeconds[0]--;
                                        timerLabel.setText("00:" + timeSeconds[0].toString());
                                        if (timeSeconds[0] <= 0) {
                                            finalTimeline.stop();
                                        }
                                    }
                                }));
                timeline.playFromStart();
            }
        });
        HBox nextPlayerButtonContainer = new HBox();
        nextPlayerButtonContainer.setAlignment(Pos.CENTER);
        Button nextPlayerButton = new Button("FINISH");
        nextPlayerButton.setId("functionButton");

        nextPlayerButton.setOnAction(event -> {
            Fight fight = new Fight();

            try {
                fight.start(Fight.fightStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            constructStage.close();
        });

        nextPlayerButtonContainer.getChildren().addAll(nextPlayerButton);

        functionPanel.getChildren().addAll(timerContainer, startTimerButton, nextPlayerButtonContainer);
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

        Image temp = new Image("file:assets/swordmanT1/t1buildevil.gif");
        ImageView buildingImg = new ImageView(temp);
        buildingImg.setStyle("-fx-background-color: transparent");
        buildingImg.setFitWidth(180);
        buildingImg.setPreserveRatio(true);

        Text buildingInfoBlock = new Text("Minions Spawned: Cupcake Minion;" +
                "\nMinion Attack Speed:" +
                "\nMinion Move Speed:" +
                "\nMinion Shield");
        buildingInfoBlock.prefWidth(180);
        buildingInfoBlock.maxWidth(180);
        buildingInfoBlock.setFont(Font.font("Herculanum", 15));
        buildingInfoBlock.setWrappingWidth(180);
        buildingInfoBlock.prefHeight(180);
        buildingInfoBlock.minHeight(180);

        Rectangle placeHolder = new Rectangle(180, 190);
        placeHolder.setFill(Color.TRANSPARENT);
        placeHolder.setStroke(Color.TRANSPARENT);


        StackPane imageViewContainer = new StackPane(buildingImg);

        StackPane infoBlockContainer = new StackPane(placeHolder, buildingInfoBlock);
        infoBlockContainer.prefWidth(180);
        infoBlockContainer.maxWidth(180);
        infoBlockContainer.prefHeight(180);
        infoBlockContainer.minHeight(180);
        infoBlockContainer.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        Button buildingImgContainer = new Button("");
        buildingImgContainer.setId("buildingImgContainer");

        buildingImgContainer.setOnAction(event -> {
            Stage stagePopup = new Stage();
            VBox comp = new VBox();
            comp.setSpacing(20);
            comp.setAlignment(Pos.CENTER);
            Text confirmAction = new Text("Confirm Purchase?");
            Button confirmButton = new Button("Confirm");
            Button declineButton = new Button("Decline");
            HBox buttonHolder = new HBox();
            buttonHolder.setAlignment(Pos.CENTER);
            buttonHolder.setSpacing(40);
            buttonHolder.getChildren().addAll(confirmButton, declineButton);

            comp.getChildren().addAll(confirmAction, buttonHolder);

            Scene popupScene = new Scene(comp, 300, 200);
            stagePopup.setScene(popupScene);
            stagePopup.show();

            confirmButton.setOnAction(event1 -> {
                stagePopup.close();
            });

            declineButton.setOnAction(event1 -> {
                stagePopup.close();
            });
        });

        buildingImgContainer.graphicProperty().bind(
                Bindings.when(
                        buildingImgContainer.hoverProperty()
                ).then(infoBlockContainer).otherwise(imageViewContainer));

        VBox buildingInfoGen = addBuildingInfo();

        singleBuildingContainer.getChildren().addAll(buildingImgContainer, buildingInfoGen);

        singleBuildingContainer.setPadding(new Insets(5, 5, 5, 5));

        return singleBuildingContainer;
    }

    /* Construct the building info block */
    private static VBox addBuildingInfo() {
        VBox buildingInfo = new VBox();
        buildingInfo.setAlignment(Pos.CENTER);

        Text buildingName = new Text("Cupcake House");
        Text buildingPrice = new Text("$200");

        buildingName.setFont(Font.font("Herculanum", 20));
        buildingPrice.setFont(Font.font("Herculanum", 20));

        buildingInfo.getChildren().addAll(buildingName, buildingPrice);

        return buildingInfo;
    }

    /* Construct the playerInfo panel */
    private static VBox addPlayerInfoPanel() {
        VBox playerInfoPanel = new VBox();
        playerInfoPanel.setSpacing(20);
        playerInfoPanel.setPadding(new Insets(10, 10, 10, 10));
        playerInfoPanel.setId("playerInfoPanel");
        playerInfoPanel.setPrefSize(500, 800);

        Text titleText = new Text("Player Info: ");
        titleText.setFont(Font.font("Herculanum", FontWeight.EXTRA_BOLD, 30));

        Text playerInfo = new Text("Player Name: Yitong" +
                "\nPlayer Color: Red" +
                "\nPlayer Gold: 1000" +
                "\nPlayer Score: 600" +
                "\nPlayer Rank: #4" +
                "\nPlayer Team: Dark");

        playerInfo.setLineSpacing(10);
        playerInfo.setFont(Font.font("Herculanum", FontWeight.EXTRA_BOLD, 20));
        playerInfo.setFill(Color.WHITE);

        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.BASELINE_RIGHT);
        Button showMapButton = new Button("Show Map");
        showMapButton.setId("functionButton");
        buttonContainer.getChildren().addAll(showMapButton);

        playerInfoPanel.getChildren().addAll(titleText, playerInfo, buttonContainer);

        return playerInfoPanel;
    }

    private static VBox addCurrectBasePanel() {
        VBox currentBasePanel = new VBox();
        currentBasePanel.setPadding(new Insets(0, 10, 10, 10));
        currentBasePanel.setStyle("-fx-border-radius: 10 10 0 0;\n" +
                " -fx-background-radius: 10 10 0 0;");
        currentBasePanel.setId("currentBaseContainer");

        Text title = new Text("Existing Buildings");
        title.setFont(Font.font("Herculanum", FontWeight.EXTRA_BOLD, 40));
        title.setFill(Color.WHITE);
        StackPane currentBase = addCurrentBase();

        currentBasePanel.getChildren().addAll(title, currentBase);

        return currentBasePanel;
    }

    private static StackPane addCurrentBase() {
        StackPane currentBase = new StackPane();

//        ImageView baseBg = new ImageView(ConstructBuilding.class.getResource("static/landscape.png").toExternalForm());
//        baseBg.setFitWidth(400);
//        baseBg.setFitHeight(400);

//        GridPane baseGrid = addBaseGrid();

        GridPane baseBuilding = addBaseBuildings();

//        baseGrid.setPadding(new Insets(20, 0, 0, 50));
        baseBuilding.setPadding(new Insets(20, 0, 0, 50));

        currentBase.getChildren().addAll(baseBuilding);

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

//        baseBuildings.getRowConstraints().add(new RowConstraints(100));
//        baseBuildings.getColumnConstraints().add(new ColumnConstraints(100));

        for (int row = 0; row < 5; row ++) {
            for (int col = 0; col < 5; col ++) {
                Circle placeHolder = new Circle(50);
                placeHolder.setFill(Color.TRANSPARENT);
                placeHolder.setStroke(Color.BLACK);
                placeHolder.getStrokeDashArray().addAll(5d, 5d, 5d, 5d);
                Button clickable = new Button("", placeHolder);
                baseBuildings.add(clickable, col, row);
                clickable.setOnAction(event ->  {
                    placeHolder.setStroke(Color.RED);
                });
            }
        }

        return baseBuildings;
    }


    private static class baseClickable extends Button {
        private baseClickable() {

        }

        private baseClickable(int row, int col) {
            this.colNum = col;
            this.rowNum = row;
        }

        public int getRowNum() {
            return rowNum;
        }

        private int rowNum;

        public int getColNum() {
            return colNum;
        }

        private int colNum;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
