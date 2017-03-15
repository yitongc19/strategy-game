package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by cheny2 on 3/14/17.
 */
public class Help extends Application {

    static Stage helpStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {

        helpStage = primaryStage;

        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);


        VBox content = new VBox();
        content.maxWidth(750);
        content.maxHeight(450);
        content.setSpacing(10);
        content.setPadding(new Insets(10, 10, 10, 10));
        content.setAlignment(Pos.CENTER);

        Label title = new Label("HOW TO PLAY");
        title.setFont(Font.font(null, 30));

        ScrollPane ruleContainer = new ScrollPane();

        VBox rules = new VBox();
        rules.setPadding(new Insets(5, 5, 5, 5));
        rules.setSpacing(10);
        rules.setAlignment(Pos.CENTER);
        Text rulePart1 = new Text("This is a PVP game. Players are divided into two teams, the light and the dark. \n" +
                "Each two players from the two teams will be placed at two ends of the same lane on the map " +
                "and they will fight against each other.");
        rulePart1.setWrappingWidth(950);
        rulePart1.setFont(Font.font(null, 16));
        Image baseTemp = new Image("file:assets/resourcesImg/baseSimulation.jpeg");
        ImageView baseSim = new ImageView(baseTemp);
        Text rulePart2 = new Text("\nTo fight, construct buildings in the building phase. " +
                "Different buildings will automatically spawn different minions with distinctive properties in " +
                "the fighting phase to fight against the enemy minions. Different buildings, of course, cost " +
                "different amounts of gold. Gold can be earned by killing enemy minions. Choose the buildings " +
                "wisely by considering the buildings of your opponent.");
        rulePart2.setWrappingWidth(950);
        rulePart2.setFont(Font.font(null, 16));
        Image kingTemp = new Image("file:assets/resourcesImg/KingSim.png");
        ImageView kingSim = new ImageView(kingTemp);
        Text rulePart3 = new Text("Each team also has a king that they need to protect. Minions who survive " +
                "to reach the opponent base on their own lane will be transported to the king lane to protect and " +
                "fight for the king. The ultimate mission for players on the same team is to cooperate with each " +
                "other and kill the enemy king.");
        rulePart3.setWrappingWidth(950);
        rulePart3.setFont(Font.font(null, 16));
        Text rulePart4 = new Text("Now go and fight for the king!");
        rulePart4.setFont(Font.font(null, 20));

        rules.getChildren().addAll(rulePart1, baseSim, rulePart2, rulePart3, kingSim, rulePart4);
        ruleContainer.setContent(rules);


        Button backButton = new Button("BACK TO HOME");
        backButton.setOnAction(event -> {
            WelcomeScreen welcomeScreen = new WelcomeScreen();
            try {
                welcomeScreen.start(WelcomeScreen.welcomeStage);
            } catch (Exception e) {

            }
            helpStage.close();
        });

        content.getChildren().addAll(title, ruleContainer, backButton);

        root.getChildren().addAll(content);

        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(Help.class.getResource("static/Help.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
