package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.tasks.Task;
import org.kryptojagd.level.tasks.Task;

/**
 *
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class LevelFinished extends AbstractController {

    @FXML
    private Label timer;

    @FXML
    private Label score;

    @FXML
    public void initialize() {
        score.setText("Punktestand: " + Task.pointSystem.getScore());
    }

    /**
     *
     * @param e
     */
    @FXML
    void menuAction(ActionEvent e) {
        mainController.getCurrentLevel().clearLevel();
        if (mainController.getClearedLevels() == mainController.getCurrentLevelPosition()) {
            mainController.setClearedLevels();
        }
        mainController.setNextLevel();
        mainController.encryptionTaskSucceeded = false;
        mainController.decryptionTaskSucceeded = false;
        mainController.multipleChoiceTaskSucceeded = false;
        mainController.cityTaskFinished = false;
        mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
    }

    /**
     *
     * @param e
     */
    @FXML
    void nextLevelAction(ActionEvent e) {
        mainController.getCurrentLevel().clearLevel();
        if (mainController.getClearedLevels() == mainController.getCurrentLevelPosition()) {
            mainController.setClearedLevels();
        }
        mainController.setNextLevel();
        mainController.encryptionTaskSucceeded = false;
        mainController.decryptionTaskSucceeded = false;
        mainController.multipleChoiceTaskSucceeded = false;
        mainController.cityTaskFinished = false;
        mainController.startLevel();
    }

}
