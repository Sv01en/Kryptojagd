package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.tasks.Task;

import java.util.ArrayList;

/**
 *
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class LevelFinished extends AbstractController {

    @FXML
    public Label text;

    @FXML
    private Label timer;

    @FXML
    private Label score;

    @FXML
    public void initialize() {
        score.setText("Punktestand: " + Task.POINT_SYSTEM.getScore());
        text.setText("Super, du hast alle Aufgaben von diesem Level gelöst!\nMöchtest du das nächste Level spielen oder zum Menü zurück?");
    }

    /**
     *
     * @param e
     */
    @FXML
    void menuAction(ActionEvent e) throws Exception {
        mainController.getCurrentLevel().clearLevel();
        if (mainController.getClearedLevels() == mainController.getCurrentLevelPosition()) {
            mainController.setClearedLevels();
        }
        mainController.encryptionTaskSucceeded = false;
        mainController.decryptionTaskSucceeded = false;
        mainController.multipleChoiceTaskSucceeded = false;
        mainController.cityTaskFinished = false;
        int levelPos = mainController.getCurrentLevelPosition();
        ArrayList<Level> levels = ReadDirectory.initialize();
        mainController.setCurrentLevel(levels.get(levelPos + 1));
        mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
    }

    /**
     *
     * @param e
     */
    @FXML
    void nextLevelAction(ActionEvent e) throws Exception {
        mainController.getCurrentLevel().clearLevel();
        if (mainController.getClearedLevels() == mainController.getCurrentLevelPosition()) {
            mainController.setClearedLevels();
        }
        mainController.encryptionTaskSucceeded = false;
        mainController.decryptionTaskSucceeded = false;
        mainController.multipleChoiceTaskSucceeded = false;
        mainController.cityTaskFinished = false;
        int levelPos = mainController.getCurrentLevelPosition();
        ArrayList<Level> levels = ReadDirectory.initialize();
        mainController.setCurrentLevel(levels.get(levelPos + 1));
        mainController.startLevel();
    }

}
