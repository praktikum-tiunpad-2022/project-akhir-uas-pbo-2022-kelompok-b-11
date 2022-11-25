
package Project;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.control.Control;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    private static final int nCell = 4;
    private static final int cellSize = 100;
    Random rand = new Random();
    //Declare 1D array to store 1-16 num
    private static int[] array = new int[nCell * nCell];
    //Declare 2D array to store 1-16 num
    private static int[][] board = new int[nCell][nCell];

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game Of Fifteen Project");

        Scene scene = new Scene(createContent());

        stage.setScene(scene);
        stage.show();
    }

    public Parent createContent() {
        GridPane root = new GridPane();
        root.setPrefSize(cellSize * nCell, cellSize * nCell);
        //When game start
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

        Cell(int y, int x) {
            int num = board[y][x];
            if (num != -1) {
                button = new Button(String.valueOf(num));
            } else {
                button = new Button();
            }

            setTranslateX(x * cellSize);
            setTranslateY(y * cellSize);

            button.setPrefSize(cellSize, cellSize);
            button.setStyle("-fx-background-radius: 10px;");
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
