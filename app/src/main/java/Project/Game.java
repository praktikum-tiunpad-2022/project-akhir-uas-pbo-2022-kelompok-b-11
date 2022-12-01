
package Project;

import java.util.Random;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class Game {

    private static final int nCell = 4;
    private static final int cellSize = 100;
    private static Random rand = new Random();
    //Declare 1D array to store 1-16 num
    private static int[] array = new int[nCell * nCell];
    //Declare 2D array to store 1-16 num
    private static int[][] board = new int[nCell][nCell];
    //Button for each tile
    private static Button[][] button = new Button[nCell][nCell];
    //to check if player won
    private static boolean win;
    //button style custom
    private static String buttonStyle = "-fx-background-radius: 10px; -fx-font-size:40; -fx-background-color: Orange; -fx-text-fill: white; -fx-border-color: Tomato; -fx-border-radius: 10;";
    private static String hoverStyle = "-fx-background-radius: 10px; -fx-font-size:40; -fx-background-color: Maroon; -fx-text-fill: white; -fx-border-color: Tomato; -fx-border-radius: 10;";
    private static String nonButtonStyle = "-fx-background-radius: 10px; -fx-font-size:40; -fx-background-color: White; -fx-text-fill: White;";

    public static void startGame(Stage stage) {
        stage.setTitle("Game Of Fifteen Project");

        Timer time = new Timer();
        time.startTimer();

        Button newButton = new Button("New Game");
        newButton.setPadding(new Insets(10, 10, 10,10));
        newButton.setOnAction(e -> {
            App newGameApp = new App();
            newGameApp.start(stage);
        });
        
        Button resetButton = new Button("Reset");
        resetButton.setPadding(new Insets(10, 10, 10,10));
        
        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(newButton, resetButton);
        VBox layout = new VBox();
        layout.getChildren().addAll(buttonLayout, time.getTimeLabel(), createContent());
        layout.setAlignment(Pos.CENTER);
        
        Scene gameScene = new Scene(layout, 420, 500);
        gameScene.setFill(Color.web("#FFFFFF"));
        stage.setScene(gameScene);
        
        resetButton.setOnAction(e -> {
            time.stopTimer();
            startGame(stage);});

        stage.setResizable(false);
        stage.show();
    }

    public static Parent createContent() {
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

    public static boolean backToNewGame() {
        return true;
    }

    public static void shuffleBoard() {
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

    public static boolean isWin() {
        if (board[nCell-1][nCell-1] != -1) { return false; }
        
        int count = 1;
        for(int i=0;i<nCell;i++)
        {
            for(int j=0;j<nCell;j++)
            {
                if(board[i][j]!=count && board[i][j]!=-1)
                {
                    return false;
                }
                count = count + 1;
            }
        }
        return true;
    }

    public static void swapTheTile(int x, int y) {
        win = isWin();
        if (win == false) {
            if (board[y][x] != -1) {
                //swap right
                if (x+1 < nCell && board[y][x+1] == -1) {
                    buttonSwap(x, x+1, y, y);
                }
                //swap left 
                else if (x-1 >= 0 && board[y][x-1] == -1) {
                    buttonSwap(x, x-1, y, y);
                }
                //swap up
                else if (y-1 >= 0 && board[y-1][x] == -1) {
                    buttonSwap(x, x, y, y-1);
                }
                //swap down
                else if (y+1 < nCell && board[y+1][x] == -1) {
                    buttonSwap(x, x, y, y+1);
                }
            }
        }
        win = isWin();
        if (win == true) {
            winContent();
        }
    }

    public static void buttonSwap(int x1, int x2, int y1, int y2) {
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

    public static void winContent() {
        Stage winStage = new Stage();
        winStage.setTitle("Win");
        Label winLabel = new Label("You solved the puzzle");
        Scene winScene = new Scene(winLabel, 100, 100);
        winStage.setScene(winScene);
        winStage.show();
    }

}

