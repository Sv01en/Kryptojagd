package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.kryptojagd.fileprocessing.ReadDirectory;

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
        mainController.switchWindowWithCSS("DecryptionTask.fxml", ReadDirectory.CSS_FILE_START);
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
        mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
    }

    /**
     *  Initializes a TimeOverController.
     */
    @FXML
    public void initialize() {

    }
}
