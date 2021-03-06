package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.kryptojagd.controls.cryptotool.CryptoToolController;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.PointSystem;

/**
 * Controller for the level finished fxml file
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class LevelFinished extends AbstractController {

    /**
     * The Text.
     */
    @FXML
    private Label text;

    @FXML
    private Label score;

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        if (!CryptoToolController.isSystemHacked()) {
            score.setText("Punktestand: " + PointSystem.getScore());
        } else {
            score.setManaged(false);
        }
        text.setText("Super, du hast alle Aufgaben von diesem Level gelöst!\nMöchtest du das "
                + "nächste Level spielen oder zum Menü zurück?");
    }

    /**
     * Button action to go back to the menu.
     * @param e not used
     */
    @FXML
    void menuAction(ActionEvent e) throws Exception {
        mainController.addPlayable(mainController.getCurrentLevel());
        mainController.setCurrentLevel(mainController.getLevelHandler().getLevelPos() + 1);
        mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
    }

    /**
     * Button action to play the next level in the game.
     * @param e not used
     */
    @FXML
    void nextLevelAction(ActionEvent e) throws Exception {
        mainController.getCurrentLevel().clearLevel();
        mainController.addPlayable(mainController.getCurrentLevel());
        mainController.setCurrentLevel(mainController.getLevelHandler().getLevelPos() + 1);
        mainController.startLevel();
    }

}
