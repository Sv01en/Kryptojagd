package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * The class controls a window of the messages if a student is doing a mistake
 * in her/his solution in the encryption task.
 *
 * @author Amelie Reichert
 * @version 1.0
 */
public class MistakeMessageController extends AbstractController {

    @FXML
    private Label message1;

    @FXML
    private Button nextButton;

    /**
     * Initializes a Controller for the mistake messages.
     */
    @FXML
    public void initialize() {
        message1.setText(mainController.getCurrentLevel().getEncryptionTask().getMistakeMsg());
    }

    /**
     * Click next.
     *
     * @param event the event
     */
    @FXML
    void clickNext(ActionEvent event) {
        String city = mainController.getCurrentLevel().getCity();
        String css = "../css/" + city + ".css";
        mainController.switchWindowWithCSS("Encryption.fxml", css);
    }

}
