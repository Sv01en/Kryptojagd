package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kryptojagd.level.countdown.CountdownTimer;

/**
 * The class controls a window of a multiple choice task.
 *
 * @author Sonja Kuklok, Michail Petermann, Sven Strasser, Leah Schlimm
 */
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

    private String a1;
    private String a2;
    private String a3;

    /**
     * Initializes a MultipleChoiceController
     *
     * Sets the Question of the MultipleChoiceTask
     * and sets their answers
     *
     */
    @FXML
    public void initialize() {
        String[] possibilities = mainController.getCurrentLevel().getCurrentMultipleChoiceTask().getPossibilities();
        a1 = possibilities[0];
        a2 = possibilities[1];
        a3 = possibilities[2];
        String question = mainController.getCurrentLevel().getCurrentMultipleChoiceTask().getQuestion() + "\n\n" + "A: " + a1 + "\n" + "B: " + a2 + "\n" + "C: " + a3;
        questionField.setText(question);
        answer1.setText("A");
        answer2.setText("B");
        answer3.setText("C");
        this.countdownTimer = new CountdownTimer(mainController.getCurrentLevel().getTimeInSec());
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
        mainController.multipleChoiceTaskSucceeded = mainController.getCurrentLevel()
                .proveMultipleChoice(a1);

        String city = mainController.getCurrentLevel().getCity();
        String css = "../css/" + city + ".css";

        mainController.switchWindowWithCSS("TaskFinished.fxml", css);
    }

    /**
     * when you click on the button answer2,
     * it proves the answer and switches the window
     *
     * @param event
     */
    @FXML
    void clickAnswer2(ActionEvent event) {
        mainController.multipleChoiceTaskSucceeded =  mainController.getCurrentLevel()
                .proveMultipleChoice(a2);

        String city = mainController.getCurrentLevel().getCity();
        String css = "../css/" + city + ".css";

        mainController.switchWindowWithCSS("TaskFinished.fxml", css);
    }

    /**
     * when you click on the button answer3,
     * it proves the answer and switches the window
     *
     * @param event
     */
    @FXML
    void clickAnswer3(ActionEvent event) {
        mainController.multipleChoiceTaskSucceeded =  mainController.getCurrentLevel()
                .proveMultipleChoice(a3);

        String city = mainController.getCurrentLevel().getCity();
        String css = "../css/" + city + ".css";

        mainController.switchWindowWithCSS("TaskFinished.fxml", css);
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
