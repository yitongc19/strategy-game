package View;

import javafx.application.Application;
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

        Image temp = new Image("file:assets/resourcesImg/ForTheKingWelcome.jpg");
        ImageView background = new ImageView(temp);

        VBox content = new VBox();
        content.maxWidth(750);
        content.maxHeight(450);
        content.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 1.2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;" +
                "-fx-background-color: #69bad2;");
        content.setAlignment(Pos.CENTER);

        Label title = new Label("HOW TO PLAY");
        title.setFont(Font.font(null, 30));

        ScrollPane ruleContainer = new ScrollPane();
        VBox rules = new VBox();
        Text rule1 = new Text("This is a PVP game. Players are divided into two teams, the light and the dark. \n" +
                "Each two players from the two teams will be placed at two ends of the same lane on the map " +
                "and they will fight against each other. To fight, construct buildings in the building phase. " +
                "Different buildings will automatically spawn different minions with distinctive properties in " +
                "the fighting phase to fight against the enemy minions. Different buildings, of course, cost " +
                "different amounts of gold. Gold can be earned by killing enemy minions. Choose the buildings " +
                "wisely by considering the buildings of your opponent.");
        rule1.setWrappingWidth(900);


        rules.getChildren().addAll(rule1);
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

        root.getChildren().addAll(background, content);

        Scene scene = new Scene(root, 1000, 600);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
