
package Project;

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class App extends Application {

    private static final int nCell = 4;
    private static final int cellSize = 100;
    Random rand = new Random();
    //Declare 1D array to store 1-16 num
    private static int[] array = new int[nCell * nCell];
    //Declare 2D array to store 1-16 num
    private static int[][] board = new int[nCell][nCell];
    //Button to start the game
    private static Button play;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game Of Fifteen Project");
        Scene startScene = new Scene (home());
        stage.setScene(startScene);

        Timer time = new Timer();
        
        Button newButton = new Button("New Game");
        newButton.setPadding(new Insets(10, 10, 10,10));
        newButton.setOnAction(e -> {
            time.stopTimer();
            start(stage);});

        VBox layout = new VBox();
        layout.getChildren().addAll(newButton, time.getTimeLabel(), createContent());
        layout.setAlignment(Pos.CENTER);

        Scene gameScene = new Scene(layout, 420, 500);
        gameScene.setFill(Color.web("#FFFFFF"));
        
        play.setOnAction(e-> stage.setScene(gameScene));
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

    public Parent createContent() {
        GridPane root = new GridPane();
        root.setPrefSize(cellSize * nCell, cellSize * nCell);
        root.setPadding(new Insets(10, 10, 10, 10));

        //When game start : suffle
        shuffleBoard();

        for (int y = 0; y < nCell; y++) {
            for (int x = 0; x < nCell; x++) {
                Cell tile = new Cell(x, y);
                root.getChildren().add(tile);
            }
        }
        return root;
    }

    private static class Cell extends StackPane {
        private Button button;
        private String buttonStyle = "-fx-background-radius: 10px; -fx-font-size:40; -fx-background-color: Orange; -fx-text-fill: white; -fx-border-color: Tomato; -fx-border-radius: 10;";
        private String hoverStyle = "-fx-background-radius: 10px; -fx-font-size:40; -fx-background-color: Maroon; -fx-text-fill: white; -fx-border-color: Tomato; -fx-border-radius: 10;";

        Cell(int y, int x) {
            button = new Button();
            setTranslateX(x * cellSize);
            setTranslateY(y * cellSize);
            button.setPrefSize(cellSize, cellSize);

            int num = board[y][x];
            if (num != -1) {
                button.setText(String.valueOf(num));
                button.setStyle(buttonStyle);
                button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
                button.setOnMouseExited(e -> button.setStyle(buttonStyle));
            } else {
                button.setStyle("-fx-background-radius: 10px; -fx-font-size:40; -fx-background-color: White; -fx-text-fill: White;");
            }
            
            getChildren().add(button);
        }
    }

    public void shuffleBoard() {
        //Assign the array
        for (int i = 0; i < (nCell*nCell); i++) {
            array[i] = i+1;
        }
        array[15] = -1;

        //Suffle the aray
        for (int i = 0; i < (nCell*nCell); i++) {
            int randomIndex = rand.nextInt(16);
            int temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }

        //Store 1D array to 2D array
        int count = 0;
        for (int y = 0; y < nCell; y++) {
            for (int x = 0; x < nCell; x++) {
                board[y][x] = array[count];
                count += 1;
            }
        } 
    }

    public boolean isSolved() {
        if (array[array.length] != -1) { return false; }

        for (int i = 0; i < array.length; i++) {
            if (array[i] != (i+1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
