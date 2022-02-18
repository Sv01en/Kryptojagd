package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 *
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class LevelFinished extends AbstractController {

    @FXML
    private Label timer;

    /**
     *
     * @param e
     */
    @FXML
    void menuAction(ActionEvent e) {
        mainController.getCurrentLevel().clearLevel();
        mainController.setClearedLevels();
        mainController.setNextLevel();
        mainController.encryptionTaskSucceeded = false;
        mainController.decryptionTaskSucceeded = false;
        mainController.multipleChoiceTaskSucceeded = false;
        mainController.cityTaskFinished = false;
        mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
    }

    /**
     *
     * @param e
     */
    @FXML
    void nextLevelAction(ActionEvent e) {
        String city = mainController.getCurrentLevel().getCity();
        String css = "../css/" + city + ".css";
        mainController.getCurrentLevel().clearLevel();
        mainController.setClearedLevels();
        mainController.setNextLevel();
        mainController.encryptionTaskSucceeded = false;
        mainController.decryptionTaskSucceeded = false;
        mainController.multipleChoiceTaskSucceeded = false;
        mainController.cityTaskFinished = false;
        mainController.startLevel();
    }

    /**
     * Updates the {@link LevelFinished#timer} every second in the corresponding fxml-file.
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
            if (mainController.getCurrentLevel().getTimeInSec() <= 0) {
                mainController.switchWindowWithCSS("TimeOver.fxml", "../css/startwindow.css");
                time.stop();
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

}
