package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.pointSystem.PointSystem;
import org.kryptojagd.level.tasks.Task;

import java.util.ArrayList;

/**
 * Controls the TimeOver window.
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class TimeOverController extends AbstractController {

    @FXML
    private Label score;

    /**
     * Restarts the current level.
     *
     * @param e the e
     */
    @FXML
    void restartAction(ActionEvent e) throws Exception {
        int levelPos = mainController.getCurrentLevelPosition();
        mainController.getCurrentLevel().clearLevel();
        ArrayList<Level> levels = ReadDirectory.initialize();
        mainController.setRestartLevel(levels.get(levelPos));
        mainController.getCurrentLevel().setFirstTryTimer(false);
        mainController.switchWindowWithCSS("DecryptionTask.fxml", ReadDirectory.CSS_FILE_START);
    }

    /**
     * Sets all attributes to the initial value and changes the screen to the menu.
     *
     * @param e the e
     */
    @FXML
    void menuAction(ActionEvent e) throws Exception {
        int levelPos = mainController.getCurrentLevelPosition();
        mainController.getCurrentLevel().clearLevel();
        ArrayList<Level> levels = ReadDirectory.initialize();
        mainController.setRestartLevel(levels.get(levelPos));
        mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
    }

    /**
     *  Initializes a TimeOverController.
     */
    @FXML
    public void initialize() {
        if (!CryptoToolController.isSystemHacked()) {
            score.setText("Punktestand: " + PointSystem.getScore());
        } else {
            score.setManaged(false);
        }
    }
}
