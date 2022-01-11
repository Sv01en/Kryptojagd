package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.kryptojagd.level.Level;

/**
 * The class controls a window of a Encryption task.
 *
 * @author Amelie, Bartosz, Sven
 * @version 1.0
 */
public class EncryptionController extends AbstractController {

    @FXML
    private Button button1;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private TextField textField1;

    private final Level level = mainController.getCurrentLevel();


    /**
     * Check if user input is correctly encrypted.
     *
     * @param event the event
     */
    @FXML
    void checkEncryption(ActionEvent event) {
        level.proveEncryptionMethod(level.getEncryptionTask().getEncryption());
        if (textField1.getText().equals(level.getEncryptionTask().getEncryptionMethod().
                encode(level.getEncryptionTask().getText(), level.getEncryptionTask().getKey()))) {
            System.out.println("Super, das hat geklappt!");
            mainController.switchWindow("MultipleChoice.fxml");
        } else {
            System.out.println("Probiere es nochmal");
        }
    }

    /**
     * Initializes a encryption task.
     */
    public void initialize() {
        label1.setText(level.getEncryptionTask().getTask());
        label2.setText(level.getEncryptionTask().getText());
    }
}