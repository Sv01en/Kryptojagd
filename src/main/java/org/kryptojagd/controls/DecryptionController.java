package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kryptojagd.level.tasks.DecryptionTask;

/**
 * The class controls a window of a decryption task.
 *
 * @author Michail Petermann, Sven Strasser, Leah Schlimm
 */
public class DecryptionController extends AbstractController {

    private DecryptionTask task = (DecryptionTask) mainController.getCurrentLevel().getCurrentTask();

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
        if (!mainController.getCurrentLevel().cityIsFinished()) {
            mainController.getCurrentLevel().startCountdown();
            String[] possibleChoice = task.getPossibilities();
            String plaintext = task.getPlainText();
            encryptedPuzzleText.setText(task.getEncryptionMethod().
                    encode(plaintext, mainController.getCurrentLevel().getEncryptionTask().getKey()));
            procedure1.setText(possibleChoice[0]);
            procedure2.setText(possibleChoice[1]);
            procedure3.setText(possibleChoice[2]);
        } else {
            mainController.getCurrentLevel().setCityShowing();
            String[] cities = mainController.getCurrentLevel().getDecryptionTask().getAnswerOptionsCity();
            String questionStr = mainController.getCurrentLevel().getDecryptionTask().getCityQuestion();
            question.setText(questionStr);
            encryptedPuzzleText.setText(mainController.getCurrentLevel().getDecryptionTask().getPlainText());
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
        if (!mainController.getCurrentLevel().getCurrentTask().getTaskCompleted()) {
            mainController.decryptionTaskSucceeded = mainController.getCurrentLevel().proveTask(
                    procedure1.getText());
        } else {
            mainController.cityTaskFinished = mainController.getCurrentLevel().proveCityTask(0);
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
        if (!mainController.getCurrentLevel().getCurrentTask().getTaskCompleted()) {
            mainController.decryptionTaskSucceeded = mainController.getCurrentLevel().proveTask(
                    procedure2.getText());
        } else {
            mainController.cityTaskFinished = mainController.getCurrentLevel().proveCityTask(1);
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
        if (!mainController.getCurrentLevel().getCurrentTask().getTaskCompleted()) {
            mainController.decryptionTaskSucceeded = mainController.getCurrentLevel().proveTask(
                    procedure3.getText());
        } else {
            mainController.cityTaskFinished = mainController.getCurrentLevel().proveCityTask(2);
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
