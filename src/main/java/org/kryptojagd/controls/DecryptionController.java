package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * The class controls a window of a decryption task.
 *
 * @author Michail, Sven
 */
public class DecryptionController extends AbstractController{

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
     * Initializes a DecryptionController
     *
     */
    @FXML
    public void initialize(){
        if (!mainController.getCurrentLevel().decryptionIsFinished()) {
            mainController.getCurrentLevel().startCountdown();
            String[] possibleProcedures = mainController.getCurrentLevel().getDecryptionTask().getAnswerOptionsEncryption();
            String plaintext = mainController.getCurrentLevel().getDecryptionTask().getPlainText();
            encryptedPuzzleText.setText(mainController.getCurrentLevel().getEncryptionTask().getEncryptionMethod().
                    encode(plaintext, mainController.getCurrentLevel().getEncryptionTask().getKey()));
            procedure1.setText(possibleProcedures[0]);
            procedure2.setText(possibleProcedures[1]);
            procedure3.setText(possibleProcedures[2]);
        } else {
            mainController.getCurrentLevel().setCityShowing();
            String[] cities = mainController.getCurrentLevel().getDecryptionTask().getAnswerOptionsCity();
            String question = mainController.getCurrentLevel().getDecryptionTask().getCityQuestion();
            encryptedPuzzleText.setText(question);
            procedure1.setText(cities[0]);
            procedure2.setText(cities[1]);
            procedure3.setText(cities[2]);
        }

        updateTimer();
    }

    @FXML
    void clickProcedure1(ActionEvent event) {
        if (!mainController.getCurrentLevel().decryptionIsFinished()) {
            mainController.DecryptionTaskSucceeded = mainController.getCurrentLevel().proveDecryptionTask(
                    procedure1.getText());
        } else {
            mainController.CityTaskFinished = mainController.getCurrentLevel().proofCityTask(0);
        }

        mainController.switchWindow("DecryptionTaskFinished.fxml");
    }

    @FXML
    void clickProcedure2(ActionEvent event) {
        if (!mainController.getCurrentLevel().decryptionIsFinished()) {
            mainController.DecryptionTaskSucceeded = mainController.getCurrentLevel().proveDecryptionTask(
                    procedure2.getText());
        } else {
            mainController.CityTaskFinished = mainController.getCurrentLevel().proofCityTask(1);
        }

        mainController.switchWindow("DecryptionTaskFinished.fxml");
    }

    @FXML
    void clickProcedure3(ActionEvent event) {
        if (!mainController.getCurrentLevel().decryptionIsFinished()) {
            mainController.DecryptionTaskSucceeded = mainController.getCurrentLevel().proveDecryptionTask(
                    procedure3.getText());
        } else {
            mainController.CityTaskFinished = mainController.getCurrentLevel().proofCityTask(2);
        }

        mainController.switchWindow("DecryptionTaskFinished.fxml");
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
            if (mainController.getCurrentLevel().getTimeInSec() == 0) {
                mainController.switchWindow("Decryption.fxml");
                time.stop();
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }
}
