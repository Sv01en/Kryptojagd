package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.tasks.DecryptionTask;

/**
 * The class controls a window of a decryption task.
 *
 * @author Michail Petermann, Sven Strasser, Leah Schlimm
 */
public class DecryptionController extends AbstractController {

    private Level level = mainController.getCurrentLevel();
    private DecryptionTask task = (DecryptionTask) level.getCurrentTask();

    @FXML
    public Label question;

    @FXML
    private Label timer = new Label();

    @FXML
    private Label encryptedPuzzleText = new Label();

    @FXML
    private Button procedure1;

    @FXML
    private Button procedure2;

    @FXML
    private Button procedure3;

    /**
     * Initializes a DecryptionController either with the city question or the decryption task
     */
    @FXML
    public void initialize() {
        if (!level.cityIsFinished()) {
            level.startCountdown();
            String[] possibleChoice = task.getPossibilities();
            String plaintext = task.getPlainText();
            encryptedPuzzleText.setText(level.getEncryptionMethod().
                    encode(plaintext, level.getEncryptionMethod().getKey()));
            procedure1.setText(possibleChoice[0]);
            procedure2.setText(possibleChoice[1]);
            procedure3.setText(possibleChoice[2]);
        } else {
            level.setCityShowing();
            String[] cities = task.getAnswerOptionsCity();
            String questionStr = task.getCityQuestion();
            question.setText(questionStr);
            encryptedPuzzleText.setText(task.getPlainText());
            procedure1.setText(cities[0]);
            procedure2.setText(cities[1]);
            procedure3.setText(cities[2]);
        }

        updateTimer();
    }

    /**
     * Handles press on first button
     *
     * @param event that is received
     */
    @FXML
    void clickProcedure1(ActionEvent event) {
        if (!level.getCurrentTask().getTaskCompleted()) {
            mainController.decryptionTaskSucceeded = level.proveTask(
                    procedure1.getText());
        } else {
            mainController.cityTaskFinished = level.proveCityTask(0);
        }

        mainController.switchWindowWithCSS(MainController.TASK_FINISHED_FXML, "../css/startwindow.css");
    }

    /**
     * Handles press on second button
     *
     * @param event that is received
     */
    @FXML
    void clickProcedure2(ActionEvent event) {
        if (!level.getCurrentTask().getTaskCompleted()) {
            mainController.decryptionTaskSucceeded = level.proveTask(
                    procedure2.getText());
        } else {
            mainController.cityTaskFinished = level.proveCityTask(1);
        }

        mainController.switchWindowWithCSS(MainController.TASK_FINISHED_FXML, "../css/startwindow.css");
    }

    /**
     * Handles press on third button
     *
     * @param event that is received
     */
    @FXML
    void clickProcedure3(ActionEvent event) {
        if (!level.getCurrentTask().getTaskCompleted()) {
            mainController.decryptionTaskSucceeded = level.proveTask(
                    procedure3.getText());
        } else {
            mainController.cityTaskFinished = level.proveCityTask(2);
        }

        mainController.switchWindowWithCSS(MainController.TASK_FINISHED_FXML, "../css/startwindow.css");
    }

    /**
     * Updates the {@link DecryptionController#timer} every second in the corresponding fxml-file.
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
