package org.kryptojagd.controls.levels;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.kryptojagd.controls.AbstractController;
import org.kryptojagd.controls.cryptotool.CryptoToolController;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.PointSystem;
import org.kryptojagd.level.tasks.EncryptionTask;

/**
 * The class controls a window of the messages if a student is doing a mistake
 * in her/his solution in the encryption task.
 *
 * @author Amelie Reichert
 * @version 1.0
 */
public class MistakeMessageController extends AbstractController {

    private Level level = mainController.getCurrentLevel();
    private EncryptionTask task = (EncryptionTask) level.getCurrentTask();

    @FXML
    private Label message1;

    @FXML
    private Label score;


    /**
     * Initializes a Controller for the mistake messages.
     */
    @FXML
    public void initialize() {
        message1.setText(task.getMistakeMsg());
        if (!CryptoToolController.isSystemHacked()) {
            score.setText("Punktestand: " + PointSystem.getScore());
        } else {
            score.setManaged(false);
        }
    }

    /**
     * Click next.
     *
     * @param event the event
     */
    @FXML
    void clickNext(ActionEvent event) {
        String city = level.getCity();
        String css = ReadDirectory.CSS_FILES + city + ".css";
        mainController.switchWindowWithCSS("EncryptionTask.fxml", css);
    }

}
