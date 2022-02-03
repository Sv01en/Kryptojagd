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
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;

/**
 * The class controls a window of a multiple choice task.
 *
 * @author Sonja Kuklok, Michail Petermann, Sven Strasser, Leah Schlimm, Bartosz Treyde
 */
public class MultipleChoiceController extends AbstractController {

    private Level level = mainController.getCurrentLevel();
    private MultipleChoiceTask task = (MultipleChoiceTask) level.getCurrentTask();

    private CountdownTimer countdownTimer;

    @FXML
    private Label timer = new Label();

    @FXML
    private Label questionField;

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
    public void initialize() {
        questionField.setText(level.getCurrentMultipleChoiceTask().getQuestion());
        String[] possibilities = level.getCurrentMultipleChoiceTask().getPossibilities();
        answer1.setText(possibilities[0]);
        answer2.setText(possibilities[1]);
        answer3.setText(possibilities[2]);
        this.countdownTimer = new CountdownTimer(level.getTimeInSec());
        updateTimer();
    }

    /**
     * when you click on the button answer1,
     * it proves the answer and switches the window
     *
     * @param event
     */
    @FXML
    void clickAnswer1(ActionEvent event) {
        clickAnswer(1);
    }

    /**
     * when you click on the button answer2,
     * it proves the answer and switches the window
     *
     * @param event
     */
    @FXML
    void clickAnswer2(ActionEvent event) {
        clickAnswer(2);
    }

    /**
     * when you click on the button answer3,
     * it proves the answer and switches the window
     *
     * @param event
     */
    @FXML
    void clickAnswer3(ActionEvent event) {
       clickAnswer(3);
    }

    /**
     * Checks if the answer is correct.
     * @param answerNumber
     */
    private void clickAnswer(int answerNumber){
        if(answerNumber == 1) {
            mainController.multipleChoiceTaskSucceeded =  level.proveTask(answer1.getText());
        } else if(answerNumber == 2){
            mainController.multipleChoiceTaskSucceeded =  level.proveTask(answer2.getText());
        } else if(answerNumber == 3){
            mainController.multipleChoiceTaskSucceeded =  level.proveTask(answer3.getText());
        }

        String city = level.getCity();
        String css = "../css/" + city + ".css";

        mainController.switchWindowWithCSS(MainController.TASK_FINISHED_FXML, css);
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
            timer.setText(Integer.toString(level.getTimeInSec()));
            if (level.getTimeInSec() <= 0) {
                mainController.switchWindowWithCSS("TimeOver.fxml", "../css/startwindow.css");
                time.stop();
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }
}
