package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kryptojagd.level.countdown.CountdownTimer;

public class MultipleChoiceController extends AbstractController {

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

    @FXML
    public void initialize() {
        questionField.setText(mainController.getCurrentLevel().getCurrentMultipleChoiceTask().getQuestion());
        String[] possibilities = mainController.getCurrentLevel().getCurrentMultipleChoiceTask().getPossibilities();
        answer1.setText(possibilities[0]);
        answer2.setText(possibilities[1]);
        answer3.setText(possibilities[2]);
        this.countdownTimer = new CountdownTimer(mainController.getCurrentLevel().getTimeInSec());
        updateTimer();
    }

    @FXML
    void clickAnswer1(ActionEvent event) {
        clickAnswer(1);
    }

    @FXML
    void clickAnswer2(ActionEvent event) {
        clickAnswer(2);
    }

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
            mainController.multipleChoiceTaskSucceeded = mainController.getCurrentLevel()
                    .proveMultipleChoice(answer1.getText());
        } else if(answerNumber == 2){
            mainController.multipleChoiceTaskSucceeded = mainController.getCurrentLevel()
                    .proveMultipleChoice(answer2.getText());
        } else if(answerNumber == 3){
            mainController.multipleChoiceTaskSucceeded = mainController.getCurrentLevel()
                    .proveMultipleChoice(answer3.getText());
        }

        String city = mainController.getCurrentLevel().getCity();
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
            timer.setText(Integer.toString(mainController.getCurrentLevel().getTimeInSec()));
            if (mainController.getCurrentLevel().getTimeInSec() <= 0) {
                mainController.switchWindowWithCSS("TimeOver.fxml", "../css/startwindow.css");
                time.stop();
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }
}
