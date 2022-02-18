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

    @FXML
    public Label answers;

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
        String[] possibilities = task.getPossibilities();
        a1 = possibilities[0];
        a2 = possibilities[1];
        a3 = possibilities[2];
        answers.setText("A: " + a1 + "\n" + "B: " + a2 + "\n" + "C: " + a3);
        questionField.setText(task.getQuestion());
        answer1.setText("A");
        answer2.setText("B");
        answer3.setText("C");
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
        clickAnswer(a1);
    }

    /**
     * when you click on the button answer2,
     * it proves the answer and switches the window
     *
     * @param event
     */
    @FXML
    void clickAnswer2(ActionEvent event) {
        clickAnswer(a2);
    }

    /**
     * when you click on the button answer3,
     * it proves the answer and switches the window
     *
     * @param event
     */
    @FXML
    void clickAnswer3(ActionEvent event) {
       clickAnswer(a3);
    }

    /**
     * Checks if the answer is correct.
     * @param answer
     */
    private void clickAnswer(String answer){
        mainController.multipleChoiceTaskSucceeded =  level.proveTask(answer);
        String city = level.getCity();
        String css = "../css/" + city + ".css";

        mainController.switchWindowWithCSS(MainController.TASK_FINISHED_FXML, css);
    }

    /**
     * Updates the {@link MultipleChoiceController#timer} every second in the corresponding fxml-file.
     */
    /**
     * Updates the {@link DecryptionController#timer} every second in the corresponding fxml-file.
     */
    @FXML
    @Override
    void updateTimer() {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        time.stop();
        KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
            System.out.println(mainController.getCurrentLevel().getTimeInSec());
            timer.setText(setCountdownFormat(mainController.getCurrentLevel().getTimeInSec()));
            if (level.getTimeInSec() <= 0) {
                mainController.switchWindowWithCSS("TimeOver.fxml", "../css/startwindow.css");
                time.stop();
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    @FXML
    public void clickMenu(ActionEvent actionEvent) {
        mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
    }
}
