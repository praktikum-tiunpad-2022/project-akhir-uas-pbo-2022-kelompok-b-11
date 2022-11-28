package Project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Label;

public class Timer {
    private static Label label;
    private int count;
    private Timeline timeline;

    public Timer() {
        label = new Label();
        count = 1;
    }

    private void incrementLabel() {
        label.setText(String.format("%,d", count++));
    }

    public Label getTimeLabel() {
        return label;
    }

    public void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> incrementLabel()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();
    }

    public void stopTimer() {
        timeline.stop();
    }
}
