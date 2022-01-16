package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controls the TimeOver file
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class TimeOverController extends AbstractController {

    /**
     * Restarts the current level.
     *
     * @param e the e
     */
    @FXML
    void restartAction(ActionEvent e) {
        mainController.encryptionTaskSucceeded = false;
        mainController.decryptionTaskSucceeded = false;
        mainController.multipleChoiceTaskSucceeded = false;
        mainController.getCurrentLevel().clearLevel();
        mainController.switchWindowWithCSS("Decryption.fxml", "../css/startwindow.css");
    }

    /**
     * Sets all attributes to the initial value and changes the screen to the menu.
     *
     * @param e the e
     */
    @FXML
    void menuAction(ActionEvent e) {
        mainController.encryptionTaskSucceeded = false;
        mainController.decryptionTaskSucceeded = false;
        mainController.multipleChoiceTaskSucceeded = false;
        mainController.getCurrentLevel().clearLevel();
        mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
    }

    /**
     *  Initializes a TimeOverController.
     */
    @FXML
    public void initialize() {

    }
}
