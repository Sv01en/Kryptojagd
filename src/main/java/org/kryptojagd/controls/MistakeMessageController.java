package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;

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
    private Button nextButton;

    /**
     * Initializes a Controller for the mistake messages.
     */
    @FXML
    public void initialize() {
        message1.setText(task.getMistakeMsg());
    }

    /**
     * Click next.
     *
     * @param event the event
     */
    @FXML
    void clickNext(ActionEvent event) {
        String city = level.getCity();
        String css = "../css/" + city + ".css";
        mainController.switchWindowWithCSS("EncryptionTask.fxml", css);
    }

}
