package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class LevelFinishedSelector extends AbstractController {

    /**
     *
     * @param e
     */
    @FXML
    void menuAction(ActionEvent e) {
        mainController.getCurrentLevel().clearLevel();
        mainController.setClearedLevels();
        mainController.encryptionTaskSucceeded = false;
        mainController.decryptionTaskSucceeded = false;
        mainController.multipleChoiceTaskSucceeded = false;
        mainController.cityTaskFinished = false;
        mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
    }

    /**
     *
     * @param e
     */
    @FXML
    void nextLevelAction(ActionEvent e) {
        mainController.getCurrentLevel().clearLevel();
        mainController.setClearedLevels();
        mainController.encryptionTaskSucceeded = false;
        mainController.decryptionTaskSucceeded = false;
        mainController.multipleChoiceTaskSucceeded = false;
        mainController.cityTaskFinished = false;
        mainController.switchWindowWithCSS("Decryption.fxml", "../css/Paris.css");
    }
}
