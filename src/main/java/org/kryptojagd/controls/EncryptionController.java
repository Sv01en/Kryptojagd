package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.kryptojagd.level.Level;


public class EncryptionController extends AbstractController {

    @FXML
    private Button button1;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private TextField textField1;

    //private final Level level = mainController.getCurrentLevel();


    @FXML
    void checkEncryption(ActionEvent event) {

    }

    public void initialize() {
        // label1.setText(level.getEncryptionTask());
    }
}

