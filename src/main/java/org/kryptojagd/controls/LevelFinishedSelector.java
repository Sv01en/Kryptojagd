package org.kryptojagd.controls;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LevelFinishedSelector extends AbstractController {

    @FXML
    private Button NextLevel;

    @FXML
    private Button Menu;

    @FXML
    void menuAction(Event e) {
        mainController.setClearedLevels();
        mainController.EncryptionTaskSucceeded = false;
        mainController.DecryptionTaskSucceeded = false;
        mainController.MultipleChoiceTaskSucceeded = false;
        mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
    }

    @FXML
    void nextLevelAction(Event e) {
        mainController.setClearedLevels();
        mainController.EncryptionTaskSucceeded = false;
        mainController.DecryptionTaskSucceeded = false;
        mainController.MultipleChoiceTaskSucceeded = false;
        mainController.switchWindowWithCSS("Decryption.fxml", "../css/Paris.css");
    }
}
