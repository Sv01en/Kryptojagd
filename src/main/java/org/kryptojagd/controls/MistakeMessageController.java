package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kryptojagd.fileprocessing.ReadDirectory;
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
    private Label timer;

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
        String css = ReadDirectory.CSS_FILES + city + ".css";
        mainController.switchWindowWithCSS("EncryptionTask.fxml", css);
    }

    /**
     * Updates the {@link MistakeMessageController#timer} every second in the corresponding fxml-file.
     */
    @FXML
    @Override
    void updateTimer() {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        time.stop();
        KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
            System.out.println(mainController.getCurrentLevel().getTimeInSec());
            timer.setText(setCountdownFormat(mainController.getCurrentLevel().getTimeInSec()));
            if (level.getTimeInSec() <= 0) {
                mainController.switchWindowWithCSS("TimeOver.fxml", ReadDirectory.CSS_FILE_START);
                time.stop();
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

}
