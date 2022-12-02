
package Project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class App extends Application {

    private static Button play;
    private static TextField name;
    Player player = new Player();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game Of Fifteen Project");
        Scene startScene = new Scene (home(player));
        stage.setScene(startScene);
        play.setOnAction(e -> {
            player.setName(name.getText());
            player.setMoves(0);
            Game.startGame(stage, player);
            });
        
        stage.setResizable(false);
        stage.show();
    }

    public Parent home(Player player) {
        VBox root = new VBox(10);
        Label tittle = new Label("Welcome to 15 Puzzle Game");
        Label desc = new Label("Enter your name");

        name = new TextField();
        name.setMaxWidth(200);
        name.setPromptText("Name");
        play = new Button("Play");

        root.getChildren().addAll(tittle, desc, name, play);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20, 20, 20, 20));
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
