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

/**
 * The class controls a window of a multiple choice task.
 *
 * @author Sonja, Michail, Sven
 */
public class MultipleChoiceController extends AbstractController{

    private CountdownTimer countdownTimer;
    private final Level level = mainController.getCurrentLevel();

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

    /**
     * Initializes a MultipleChoiceController
     *
     * Sets the Question of the MultipleChoiceTask
     * and sets their answers
     *
     */
    @FXML
    public void initialize(){
        QuestionField.setText(level.getCurrentMultipleChoiceTask().getQuestion());
        String[] possibilities = level.getCurrentMultipleChoiceTask().getPossibilities();
        for (String answer : possibilities) {
            answer1.setText(answer);
        }
        this.countdownTimer = new CountdownTimer(20);
        updateTimer();
    }

    @FXML
    void clickAnswer1(ActionEvent event) {
        mainController.taskSucceeded = level.proofMultipleChoice(answer1.getText());
        mainController.switchWindow("TaskFinished.xml");
    }

    @FXML
    void clickAnswer2(ActionEvent event) {
        mainController.taskSucceeded = level.proofMultipleChoice(answer1.getText());
        mainController.switchWindow("TaskFinished.xml");
    }

    @FXML
    void clickAnswer3(ActionEvent event) {
        mainController.taskSucceeded = level.proofMultipleChoice(answer1.getText());
        mainController.switchWindow("TaskFinished.xml");
    }

    /**
     * Updates the {@link MultipleChoiceController#timer} every second in the corresponding fxml-file.
     */
    @FXML
    void updateTimer() {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        time.stop();
        KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
            timer.setText(countdownTimer.getCurrentValue());
            if (Integer.parseInt(countdownTimer.getCurrentValue()) == 0) {
                mainController.switchWindow("Entschluesselung.fxml");
                time.stop();
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }
}
