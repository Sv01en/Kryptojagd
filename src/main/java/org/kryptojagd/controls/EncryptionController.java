package org.kryptojagd.controls;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.kryptojagd.encryptionmethods.Backwards;
import org.kryptojagd.level.Level;

/**
 * Encryption Controller for the corresponding fxml-file
 *
 * @author Amelie, Bartok, Sven
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


    @FXML
    void checkEncryption(ActionEvent event) {
        level.proveEncryptionMethod(level.getEncryptionTask().getEncryption());
        if (textField1.getText().equals(level.getEncryptionTask().getEncryptionMethod().
                encode(level.getEncryptionTask().getText(), level.getEncryptionTask().getKey()))) {
            System.out.println("Super, das hat geklappt!");

        } else {
            System.out.println("Probiere es nochmal");
        }
    }
    public void initialize() {
        label1.setText(level.getEncryptionTask().getTask());
        label2.setText(level.getEncryptionTask().getText());
    }

}
