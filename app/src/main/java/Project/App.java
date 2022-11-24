
package Project;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    private static final int nCell = 4;
    private static final int cellSize = 100;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game Of Fifteen Project");

        Scene scene = new Scene(createContent());

        stage.setScene(scene);
        stage.show();
    }

    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(cellSize * nCell, cellSize * nCell);

        for (int y = 0; y < nCell; y++) {
            for (int x = 0; x < nCell; x++) {
                Cell tile = new Cell(x, y);
                root.getChildren().add(tile);
            }
        }
        return root;
    }

    private static class Cell extends StackPane {
        private Rectangle bg;

        Cell(int x, int y) {
            setTranslateX(x * cellSize);
            setTranslateY(y * cellSize);

            bg = new Rectangle(cellSize, cellSize, Color.YELLOW);
            bg.setStroke(Color.WHITE);

            getChildren().add(bg);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
