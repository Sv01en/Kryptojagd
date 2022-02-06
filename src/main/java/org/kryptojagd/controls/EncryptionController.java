package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.tasks.EncryptionTask;

/**
 * Encryption Controller for the corresponding fxml-file
 *
 * @author Amelie Reichert, Bartosz Treyde, Sven Strasser
 * @version 1.0
 */
public class EncryptionController extends AbstractController {

    private Level level = mainController.getCurrentLevel();
    private EncryptionTask task = (EncryptionTask) level.getCurrentTask();

    @FXML
    private Label timer = new Label();

    @FXML
    private Button button1;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private TextField textField1;

    /**
     * Initializes an EncryptionController.
     */
    @FXML
    public void initialize() {
        if (level.getEncryptionInput() != null) {
            textField1.setText(level.getEncryptionInput());
        }
        label1.setText(task.getTaskText());
        label2.setText(task.getText());
        updateTimer();
    }

    /**
     * Check if the EcryptionTask is solved succesfully.
     * If its true it switches to the EncryptionTaskFinished Window.
     * If there is a wrong solution it switches to the MistakeMessage Window.
     *
     *
     * @param event the event
     */
    @FXML
    void checkEncryption(ActionEvent event) {
        level.proveTask(textField1.getText());
        mainController.switchWindowWithCSS(MainController.TASK_FINISHED_FXML, "../css/startwindow.css");
        String city = level.getCity();
        String css = "../css/" + city + ".css";

        if (task.getTaskCompleted()) {
            mainController.switchWindowWithCSS("TaskFinished.fxml", css);
        } else {
            mainController.switchWindowWithCSS("MistakeMessage.fxml", css);
        }
    }

    /**
     * Updates the {@link EncryptionController#timer} every second in the corresponding fxml-file.
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
