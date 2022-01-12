package org.kryptojagd.controls;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.kryptojagd.encryptionmethods.Backwards;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.countdown.CountdownTimer;

/**
 * Encryption Controller for the corresponding fxml-file
 *
 * @author Amelie, Bartok, Sven
 * @version 1.0
 */
public class EncryptionController extends AbstractController {

    private CountdownTimer countdownTimer;

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

    private final Level level = mainController.getCurrentLevel();

    @FXML
    public void initialize() {
        label1.setText(level.getEncryptionTask().getTask());
        label2.setText(level.getEncryptionTask().getText());
        this.countdownTimer = new CountdownTimer(level.getTimeInSec());
        updateTimer();
    }

    @FXML
    void checkEncryption(ActionEvent event) {
        if (level.proveEncryptionTask(textField1.getText())) {
            System.out.println("Super, das hat geklappt!");
            mainController.switchWindow("MultipleChoice.fxml");
        } else {
            System.out.println("Probiere es nochmal");
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
            timer.setText(countdownTimer.getCurrentValue());
            mainController.getCurrentLevel().setTimeInSec(Integer.parseInt(countdownTimer.getCurrentValue()));
            if (Integer.parseInt(countdownTimer.getCurrentValue()) == 0) {
                mainController.switchWindow("Decryption.fxml");
                time.stop();
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }
}
