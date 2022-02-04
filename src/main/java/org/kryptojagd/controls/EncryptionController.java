package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

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

    /**
     * Initializes an EncryptionController.
     */
    @FXML
    public void initialize() {
        if (mainController.getCurrentLevel().getEncryptionInput() != null) {
            textField1.setText(mainController.getCurrentLevel().getEncryptionInput());
        }
        label1.setText(mainController.getCurrentLevel().getEncryptionTask().getTask());
        label2.setText(mainController.getCurrentLevel().getEncryptionTask().getText());
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
        boolean solutionStatus = mainController.getCurrentLevel().proveEncryptionTask(textField1.getText());
        mainController.encryptionTaskSucceeded = solutionStatus;
        String city = mainController.getCurrentLevel().getCity();
        String css = "../css/" + city + ".css";

        if (solutionStatus) {
            mainController.switchWindowWithCSS("EncryptionTaskFinished.fxml", css);
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
