package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.pointSystem.PointSystem;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.Task;

/**
 * Encryption Controller for the corresponding fxml-file
 *
 * @author Amelie Reichert, Bartosz Treyde, Sven Strasser
 * @version 1.0
 */
public class EncryptionController extends AbstractController {

    private Level level = mainController.getCurrentLevel();
    private EncryptionTask task = (EncryptionTask) level.getCurrentTask();

    @FXML
    private Label timer = new Label();

    @FXML
    private Button button1;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private TextField textField1;

    @FXML
    private Label score;

    /**
     * Initializes an EncryptionController.
     */
    @FXML
    public void initialize() {
        if (!CryptoToolController.isSystemHacked()) {
            score.setText("Punktestand: " + PointSystem.getScore());
        } else {
            score.setManaged(false);
        }
        label1.setText(task.getTaskText());
        label2.setText(task.getText());
        label3.setVisible(false);
        updateTimer(timer);
    }

    /**
     * Check if the EcryptionTask is solved succesfully.
     * If its true it switches to the EncryptionTaskFinished Window.
     * If there is a wrong solution it switches to the MistakeMessage Window.
     *
     *
     * @param event the event
     */
    @FXML
    void checkEncryption(ActionEvent event) {
        level.proveTask(textField1.getText());
        //mainController.switchWindowWithCSS(MainController.TASK_FINISHED_FXML, ReadDirectory.CSS_FILE_START);
        String city = level.getCity();
        String css = ReadDirectory.CSS_FILES + city + ".css";

        if (task.getTaskCompleted()) {
            mainController.switchWindowWithCSS("TaskFinished.fxml", css);
        } else {
            EncryptionTask task = (EncryptionTask) level.getCurrentTask();
            String hint = task.getMistakeMsg();
            label3.setText(hint);
            label3.setVisible(true);
            //mainController.switchWindowWithCSS("MistakeMessage.fxml", css);
        }
    }


    /**
     * Click menu.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void clickMenu(ActionEvent actionEvent) {
        mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
    }

    /**
     * Click help opens the text based help.
     *
     * @param event the event
     */
    @FXML
    void clickHelp(ActionEvent event) {
        mainController.switchWindowWithCSS("HelpText.fxml", ReadDirectory.CSS_FILE_START);
    }
}
