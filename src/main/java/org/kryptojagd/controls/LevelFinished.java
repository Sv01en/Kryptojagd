package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class LevelFinished extends AbstractController {

    /**
     *
     * @param e
     */
    @FXML
    void menuAction(ActionEvent e) {
        mainController.getCurrentLevel().clearLevel();
        mainController.setClearedLevels();
        mainController.setNextLevel();
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
        String city = mainController.getCurrentLevel().getCity();
        String css = "../css/" + city + ".css";
        mainController.getCurrentLevel().clearLevel();
        mainController.setClearedLevels();
        mainController.setNextLevel();
        mainController.encryptionTaskSucceeded = false;
        mainController.decryptionTaskSucceeded = false;
        mainController.multipleChoiceTaskSucceeded = false;
        mainController.cityTaskFinished = false;
        mainController.startLevel();
    }


}