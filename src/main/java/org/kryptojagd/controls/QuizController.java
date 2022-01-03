package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kryptojagd.level.countdown.CountdownTimer;

import java.util.concurrent.ExecutionException;

/**
 * Handles the corresponding fxml-file.
 * @author Michael Petermann, Sven Strasser
 * @version 1.0
 */
public class QuizController extends AbstractController {

    private CountdownTimer countdownTimer;

    /**
     * The remaining time for the countdown timer functionality.
     */
    private long actuelTime;

    @FXML
    private Button antwort1;

    @FXML
    private Button antwort2;

    @FXML
    private Button antwort3;

    @FXML
    private Label timer = new Label();

    public QuizController() throws InterruptedException, ExecutionException {
        //TODO: implement correct time handling, maybe the level must be ajusted.....
        this.countdownTimer = new CountdownTimer(20);
        updateTimer();
    }

    @FXML
    void klickAntwort1(ActionEvent event) {
        System.out.println("Antwort 1 wurde ausgewäht!");
        mainController.switchWindow("Levelabschluss.fxml");
    }

    @FXML
    void klickAntwort2(ActionEvent event) {
        System.out.println("Antwort 2 wurde ausgewäht!");
    }

    @FXML
    void klickAntwort3(ActionEvent event) {
        System.out.println("Antwort 3 wurde ausgewäht!");
    }

    /**
     * Updates the timer in the corresponding window.
     *
     * @author Sven Strasser
     */
    @FXML
    void updateTimer() {
        updateLabel.start();
    }

    /**
     * Thread starts process to update the label in fxml-file.
     */
    Thread updateLabel = new Thread(() -> {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        time.stop();
        KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
            timer.setText(countdownTimer.getActuelValue());
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    });
}
