
package Project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class App extends Application {

    private static Button play;
    private static TextField name;
    Player player = new Player();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game Of Fifteen Project");
        Scene startScene = new Scene (home(player), 420, 620);
        stage.setScene(startScene);
        play.setOnAction(e -> {
            player.setName(name.getText());
            Game.startGame(stage, player);
            });
        
        stage.setResizable(false);
        stage.show();
    }

    public Parent home(Player player) {
        VBox root = new VBox(20);

        Text tittle = new Text("Welcome to 15 Puzzle Game");
        tittle.setFill(Color.WHITE);
        tittle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        tittle.setWrappingWidth(300);
        tittle.setTextAlignment(TextAlignment.CENTER);

        Text desc = new Text("Enter your name");
        desc.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        desc.setFill(Color.WHITE);

        name = new TextField();
        name.setMaxWidth(200);
        name.setPromptText("Name");

        play = new Button("Play");
        play.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        play.setStyle("-fx-background-radius: 10px; -fx-background-color: rgba(43, 43, 43, 0.6); -fx-text-fill: white; -fx-border-color: #454545; -fx-border-radius: 10;");
        play.setMaxWidth(200);
        play.setOnMouseEntered(e -> play.setStyle("-fx-background-radius: 10px; -fx-background-color: rgba(125, 125, 125, 0.6); -fx-text-fill: white;"));
        play.setOnMouseExited(e -> play.setStyle("-fx-background-radius: 10px; -fx-background-color: rgba(43, 43, 43, 0.6); -fx-text-fill: white; -fx-border-color: #454545; -fx-border-radius: 10;"));

        root.getChildren().addAll(tittle, desc, name, play);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setStyle("-fx-background-image: url('https://w0.peakpx.com/wallpaper/221/607/HD-wallpaper-like-heaven.jpg'); -fx-background-repeat: no-repeat; -fx-background-size: 620 620; -fx-background-position: center center;");
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
