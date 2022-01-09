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
import org.kryptojagd.level.tasks.DecryptionTask;

/**
 * The class controls a window of a decryption task.
 *
 * @author Michail, Sven
 */
public class DecryptionController extends AbstractController{

    private CountdownTimer countdownTimer;

    @FXML
    private Label timer = new Label();


    @FXML
    private Label encryptedPuzzleText = new Label();

    @FXML
    private Label frage = new Label();

    @FXML
    private Button procedure1;

    @FXML
    private Button procedure2;

    @FXML
    private Button procedure3;

    private final Level level = mainController.getCurrentLevel();

    /**
     * Initializes a DecryptionController
     *
     */
    @FXML
    public void initialize(){
        String[] possibleProcedures = level.getDecryptionTask().getAnswerOptionsEncryption();
        encryptedPuzzleText.setText(level.getDecryptionTask().getPlainText());
        procedure1.setText(possibleProcedures[0]);
        procedure2.setText(possibleProcedures[1]);
        procedure3.setText(possibleProcedures[2]);
        this.countdownTimer = new CountdownTimer(level.getDecryptionTask().getTimeInSec());
        updateTimer();
    }

    @FXML
    void clickProcedure1(ActionEvent event) {
        System.out.println("Verfahren 1 wurde ausgewählt.");
        mainController.switchWindow("MultipleChoice.fxml");
    }

    @FXML
    void clickProcedure2(ActionEvent event) {
        System.out.println("Verfahren 2 wurde ausgewählt.");
        mainController.switchWindow("WrongChoice.fxml");
    }

    @FXML
    void clickProcedure3(ActionEvent event) {
        System.out.println("Verfahren 3 wurde ausgewählt.");
        mainController.switchWindow("WrongChoice.fxml");
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
            timer.setText(countdownTimer.getCurrentValue());
            if (Integer.parseInt(countdownTimer.getCurrentValue()) == 0) {
                mainController.switchWindow("Decryption.fxml");
                time.stop();
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    @FXML
    private void handleDescryptionTask() {

    }
}
