package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.tasks.Task;

import java.util.ArrayList;

/**
 * Controller for the level finished fxml file
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class LevelFinished extends AbstractController {

    @FXML
    public Label text;

    @FXML
    private Label score;

    @FXML
    public void initialize() {
        score.setText("Punktestand: " + Task.POINT_SYSTEM.getScore());
        text.setText("Super, du hast alle Aufgaben von diesem Level gelöst!\nMöchtest du das " +
                "nächste Level spielen oder zum Menü zurück?");
    }

    /**
     * Button action to go back to the menu.
     * @param e not used
     */
    @FXML
    void menuAction(ActionEvent e) {
        mainController.getCurrentLevel().clearLevel();
        /*if (mainController.getCurrentLevelPosition() < mainController.getClearedLevels()) {
            mainController.setClearedLevels();
        }*/
        mainController.encryptionTaskSucceeded = false;
        mainController.decryptionTaskSucceeded = false;
        mainController.multipleChoiceTaskSucceeded = false;
        mainController.cityTaskFinished = false;
        int levelPos = mainController.getCurrentLevelPosition();
        mainController.setCurrentLevel(levelPos + 1);
        mainController.addPlayableLevel(mainController.getCurrentLevel().getId());
        mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
    }

    /**
     * Button action to play the next level in the game.
     * @param e not used
     */
    @FXML
    void nextLevelAction(ActionEvent e) {
        mainController.getCurrentLevel().clearLevel();
        /*if (mainController.getCurrentLevelPosition() < mainController.getClearedLevels()) {
            mainController.setClearedLevels();
        }*/
        mainController.encryptionTaskSucceeded = false;
        mainController.decryptionTaskSucceeded = false;
        mainController.multipleChoiceTaskSucceeded = false;
        mainController.cityTaskFinished = false;
        int levelPos = mainController.getCurrentLevelPosition();
        mainController.setCurrentLevel(levelPos + 1);
        mainController.addPlayableLevel(mainController.getCurrentLevel().getId());
        mainController.startLevel();
    }

}
