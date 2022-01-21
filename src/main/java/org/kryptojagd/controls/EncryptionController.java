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

/**
 * Encryption Controller for the corresponding fxml-file
 *
 * @author Amelie Reichert, Bartosz Treyde, Sven Strasser
 * @version 1.0
 */
public class EncryptionController extends AbstractController {

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

    @FXML
    public void initialize() {
        if (mainController.getCurrentLevel().getEncryptionInput() != null) {
            textField1.setText(mainController.getCurrentLevel().getEncryptionInput());
        }
        label1.setText(mainController.getCurrentLevel().getEncryptionTask().getTask());
        label2.setText(mainController.getCurrentLevel().getEncryptionTask().getText());
        updateTimer();
    }

    @FXML
    void checkEncryption(ActionEvent event) {
        mainController.encryptionTaskSucceeded = mainController.getCurrentLevel().proveEncryptionTask(
                textField1.getText());
        mainController.switchWindow("EncryptionTaskFinished.fxml");
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
            timer.setText(Integer.toString(mainController.getCurrentLevel().getTimeInSec()));
            if (mainController.getCurrentLevel().getTimeInSec() <= 0) {
                mainController.switchWindow("TimeOver.fxml");
                time.stop();
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }
}
