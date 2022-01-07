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
    private Label frage;

    @FXML
    private Button verfahren1;

    @FXML
    private Button verfahren2;

    @FXML
    private Button verfahren3;

    private final Level level = mainController.getCurrentLevel();

    /**
     * Initializes a DecryptionController
     *
     */
    @FXML
    public void initialize(){
        encryptedPuzzleText.setText("Hallo");
        this.countdownTimer = new CountdownTimer(20);
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

    /**
     *
     */
    private void descryptionTask() {
        DecryptionTask task = level.getDecryptionTask();

    }
}
