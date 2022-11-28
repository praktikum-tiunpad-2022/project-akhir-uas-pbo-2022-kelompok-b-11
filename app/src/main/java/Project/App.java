
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
    //Button for each tile
    private static Button[][] button = new Button[nCell][nCell];
    //button style custom
    private static String buttonStyle = "-fx-background-radius: 10px; -fx-font-size:40; -fx-background-color: Orange; -fx-text-fill: white; -fx-border-color: Tomato; -fx-border-radius: 10;";
    private static String hoverStyle = "-fx-background-radius: 10px; -fx-font-size:40; -fx-background-color: Maroon; -fx-text-fill: white; -fx-border-color: Tomato; -fx-border-radius: 10;";
    private static String nonButtonStyle = "-fx-background-radius: 10px; -fx-font-size:40; -fx-background-color: White; -fx-text-fill: White;";

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
        
        play.setOnAction(e-> {
            stage.setScene(gameScene);
            time.startTimer();});
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

        Cell(int y, int x) {
            button[y][x] = new Button();
            setTranslateX(x * cellSize);
            setTranslateY(y * cellSize);
            button[y][x].setPrefSize(cellSize, cellSize);

            int num = board[y][x];
            if (num != -1) {
                button[y][x].setText(String.valueOf(num));
                button[y][x].setStyle(buttonStyle);
                button[y][x].setOnMouseEntered(e -> button[y][x].setStyle(hoverStyle));
                button[y][x].setOnMouseExited(e -> button[y][x].setStyle(buttonStyle));
            } else {
                button[y][x].setStyle(nonButtonStyle);
            }
            
            button[y][x].setOnAction(e -> swapTheTile(x, y));
            getChildren().add(button[y][x]);
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

    public static void swapTheTile(int x, int y) {
        if (board[y][x] != -1) {
            //swap right
            if (x+1 < nCell && board[y][x+1] == -1) {
                buttonSet(x, x+1, y, y);
            }
            //swap left 
            else if (x-1 >= 0 && board[y][x-1] == -1) {
                buttonSet(x, x-1, y, y);
            }
            //swap up
            else if (y-1 >= 0 && board[y-1][x] == -1) {
                buttonSet(x, x, y, y-1);
            }
            //swap down
            else if (y+1 < nCell && board[y+1][x] == -1) {
                buttonSet(x, x, y, y+1);
            }
        }
    }

    public static void buttonSet(int x1, int x2, int y1, int y2) {
        int temp = board[y1][x1];
        board[y1][x1] = board[y2][x2];
        board[y2][x2] = temp;
        button[y1][x1].setText("");
        button[y1][x1].setStyle(nonButtonStyle);
        button[y1][x1].setOnMouseEntered(e -> button[y1][x1].setStyle(nonButtonStyle));
        button[y1][x1].setOnMouseExited(e -> button[y1][x1].setStyle(nonButtonStyle));
        button[y2][x2].setText(String.valueOf(board[y2][x2]));
        button[y2][x2].setStyle(buttonStyle);
        button[y2][x2].setOnMouseEntered(e -> button[y2][x2].setStyle(hoverStyle));
        button[y2][x2].setOnMouseExited(e -> button[y2][x2].setStyle(buttonStyle));
    }
    public static void main(String[] args) {
        launch(args);
    }

}
