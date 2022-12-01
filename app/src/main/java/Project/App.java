
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

    boolean createNew;

    private static Button play;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game Of Fifteen Project");
        Scene startScene = new Scene (home());
        stage.setScene(startScene);
        play.setOnAction(e -> {
            Game.startGame(stage);
            createNew = Game.backToNewGame();});
        
        if (createNew == true){
            start(stage);
        }
        
        stage.setResizable(false);
        stage.show();
    }

    public Parent home() {
        VBox root = new VBox(10);
        Label tittle = new Label("Welcome to 15 Puzzle Game");
        Label desc = new Label("Enter your name");

        TextField name = new TextField();
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
