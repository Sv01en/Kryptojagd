package org.kryptojagd.controls;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kryptojagd.controls.resources.Messages;
import org.kryptojagd.cryptotools.CryptoTool;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.Task;

import java.io.IOException;

/**
 * The class controls a window of a decryption task.
 *
 * @author Michail Petermann, Sven Strasser, Leah Schlimm, Bartosz Treyde, Amelie Reichert
 */
public class DecryptionController extends AbstractController {

    private Level level = mainController.getCurrentLevel();
    private DecryptionTask task = (DecryptionTask) level.getCurrentTask();

    /**
     * The Question.
     */
    @FXML
    public Label question;

    /**
     * The Encrypted puzzle text.
     */
    @FXML
    public Label encryptedPuzzleText;

    /**
     * The Text field.
     */
    @FXML
    public TextField textField;

    @FXML
    private Label timer = new Label();

    @FXML
    private Button procedure1;

    @FXML
    private Button procedure2;

    @FXML
    private Button procedure3;

    @FXML
    private Button Cryptotool;

    @FXML
    private Button button1;

    @FXML
    private Label score;

    /**
     * Initializes a DecryptionController either with the city question or the decryption task
     */
    @FXML
    public void initialize() {
        updateTimer(timer);
        score.setText("Punktestand: " + Task.POINT_SYSTEM.getScore());
        if (!task.getTaskCompleted()) {
            level.startCountdown();
            question.setText(Messages.DECRYPTION_QUESTION);
            String[] possibleChoice = task.getPossibilities();
            String plaintext = task.getPlainText();
            encryptedPuzzleText.setText(task.getEncryptionMethod().encode(plaintext));
            procedure1.setText(possibleChoice[0]);
            procedure2.setText(possibleChoice[1]);
            procedure3.setText(possibleChoice[2]);

            Cryptotool.setDisable(true);

            button1.setDisable(true);

            if (mainController.decryptionTaskSucceeded) {
                procedure1.setDisable(true);
                procedure2.setDisable(true);
                procedure3.setDisable(true);
                button1.setDisable(false);

                if (level.getId() >= 1) {
                    Cryptotool.setDisable(false);
                }
            }
        }
    }

    /**
     * Handles press on first button
     *
     * @param event that is received
     */
    @FXML
    void clickProcedure1(ActionEvent event) {
        clickAnswer(procedure1);
    }

    /**
     * Handles press on second button
     *
     * @param event that is received
     */
    @FXML
    void clickProcedure2(ActionEvent event) {
        clickAnswer(procedure2);
    }

    /**
     * Handles press on third button
     *
     * @param event that is received
     */
    @FXML
    void clickProcedure3(ActionEvent event) {
        clickAnswer(procedure3);
    }

    @FXML
    private void clickSend(ActionEvent event) {
        mainController.decryptionTextTaskSucceeded = level.proveTask(textField.getText());
        mainController.switchWindowWithCSS(MainController.TASK_FINISHED_FXML, ReadDirectory.CSS_FILE_START);
    }

    /**
     * Checks if the answer is correct.
     * @param procedure Button which is clicked on
     */
    private void clickAnswer(Button procedure) {
        mainController.decryptionTaskSucceeded = level.proveEncryptionType(procedure.getText());
        mainController.switchWindowWithCSS(MainController.TASK_FINISHED_FXML, ReadDirectory.CSS_FILE_START);
    }

    /**
     * Click menu.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void clickMenu(ActionEvent actionEvent) {
        mainController.getCurrentLevel().clearLevel();
        mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
    }

    @FXML
    private void clickCrypto(ActionEvent event) {
        String plaintext = task.getPlainText();
        if (task.getPossibilities()[task.getCorrectAnswerEncryption()].startsWith("Cäsar")) {
            try {
                CryptoTool.caesar(new Stage(), task.getEncryptionMethod().encode(plaintext));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                CryptoTool.vigenere(new Stage(), task.getEncryptionMethod().encode(plaintext));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Click help opens the text based help.
     *
     * @param event the event
     */
    @FXML
    void clickHelp(ActionEvent event) {
        mainController.switchWindowWithCSS("HelpText.fxml", ReadDirectory.CSS_FILE_START);
    }

}
