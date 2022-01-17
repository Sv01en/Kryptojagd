package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Gives the right feedback to a task and switches the window.
 *
 * @author Sonja Kuklok, Sven Strasser, Leah Schlimm
 */
public class EncryptionTaskFinished extends AbstractController {

    @FXML
    private Label timer = new Label();

    @FXML
    private Label feedbackText;

    /**
     * Initializes a TaskFinishedController
     *
     * Gives the right feedback to a multipleChoice and switches the window.
     *
     * If the answer was right, it prints out "Richtig, weiter so!"
     * If the answer was wrong, it prints out "Leider falsch, versuche es noch einmal. Du musst dich beeilen!"
     * in both cases it switchs to a new multiple choice window
     * if every question of a level is answered, it prints out "Glückwunsch, du hast alle Fragen richtig beantwortet!"
     *
     */
    @FXML
    public void initialize() {
        updateTimer();
        if (mainController.getCurrentLevel().decryptionIsFinished()) {
            if (mainController.decryptionTaskSucceeded) {
                feedbackText.setText("Richtig, weiter so!");
            } else {
                feedbackText.setText("Leider falsch, versuche es noch einmal. Du musst dich beeilen!");
            }
        }
    }

    /**
     * Switches the window to the end of the level, if every question of a level is answered
     * otherwise switches to the next multiple choice question
     *
     * @param event
     */
    @FXML
    void switchMultipleChoice(ActionEvent event) {
        if (mainController.getCurrentLevel().encryptionTaskFinished()) {
            //mainController.switchWindow("MultipleChoice.fxml");
            mainController.switchWindow("LevelFinishedSelector.fxml");
        } else {
            mainController.switchWindow("Encryption.fxml");
        }
    }

    /**
     * Updates the {@link EncryptionTaskFinished#timer} every second in the corresponding fxml-file.
     */
    @FXML
    void updateTimer() {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        time.stop();
        KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
            timer.setText(Integer.toString(mainController.getCurrentLevel().getTimeInSec()));
            if (mainController.getCurrentLevel().getTimeInSec() == 0) {
                mainController.switchWindow("MultipleChoice.fxml");
                time.stop();
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }
}