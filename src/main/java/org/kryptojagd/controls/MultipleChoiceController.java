package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.countdown.CountdownTimer;

import java.util.concurrent.ExecutionException;
/**
 * The class controls a window of a multipleChoiceTask
 *
 * @author Sonja, Michail, Sven
 */
public class MultipleChoiceController extends AbstractController{

    private CountdownTimer countdownTimer;

    /**
     * The remaining time for the countdown timer functionality.
     */
    private long actuelTime;

    @FXML
    private Label timer = new Label();

    @FXML
    private Label QuestionField;

    @FXML
    private Button answer1;

    @FXML
    private Button answer2;

    @FXML
    private Button answer3;

    private final Level level = mainController.getCurrentLevel();

    /**
     * Initializes a MultipleChoiceController
     *
     * Sets the Question of the MultipleChoiceTask
     * and sets their answers
     *
     */
    @FXML
    public void initialize(){
        //TODO: not working in the moment. Caused an exception.
        /*QuestionField.setText(level.getCurrentMultipleChoiceTask().getQuestion());
        String[] possibilities = level.getCurrentMultipleChoiceTask().getPossibilities();
        for (String answer : possibilities) {
            answer1.setText(answer);
        }*/
        //TODO: implement correct time handling, maybe the level must be ajusted.....
        this.countdownTimer = new CountdownTimer(20);
        updateTimer();
    }


    @FXML
    void clickAnswer1(ActionEvent event) {
        level.proofMultipleChoice(answer1.getText());
    }

    @FXML
    void clickAnswer2(ActionEvent event) {
        level.proofMultipleChoice(answer2.getText());
    }

    @FXML
    void clickAnswer3(ActionEvent event) {
        level.proofMultipleChoice(answer3.getText());
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
