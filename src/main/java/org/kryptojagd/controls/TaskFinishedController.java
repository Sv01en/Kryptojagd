package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kryptojagd.controls.resources.Messages;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;
import org.kryptojagd.level.tasks.Task;

/**
 * Gives the right feedback to a task and switches the window.
 *
 * @author Sonja Kuklok, Leah Schlimm
 */
public class TaskFinishedController extends AbstractController {

	private final Level level = mainController.getCurrentLevel();
	private static final String LEVEL_FINISHED = "LevelFinished.fxml";

	@FXML
	private Label timer = new Label();

	@FXML
	private Label feedbackText;

	/**
	 * Initializes a TaskFinishedController
	 *
	 * Updates the timer and sets the feedback text
	 */
	@FXML
	public void initialize() {
		updateTimer();
		feedbackText.setText(level.getFeedback());
	}

	/**
	 * Handles the switch to the next task
	 *
	 * If the cityTask is completed it changes the background picture
	 * If the level is completed,
	 * it switches the window to the level finished window
	 *
	 * @param event that is received
	 */
	 @FXML
	 void nextWindow(ActionEvent event) {
	 	if (!level.isLevelCompleted()) {
			String css;
	 		if (level.getTask("cityTask").getTaskCompleted()) {
				String city = level.getCity();
				css = "../css/" + city + ".css";
			} else {
	 			css = "../css/startwindow.css";
			}
			level.setNextTask();
			mainController.switchWindowWithCSS(level.getCurrentTask().toString() + ".fxml", css);
		} else {
		 	String css;
			String city = level.getCity();
			css = "../css/" + city + ".css";
			mainController.switchWindowWithCSS(LEVEL_FINISHED, css);
		}
	 }

	/**
	 * Updates the {@link TaskFinishedController#timer} every second in the corresponding fxml-file.
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
				mainController.switchWindowWithCSS("TimeOver.fxml", "../css/startwindow.css");
				time.stop();
			}
		});
		time.getKeyFrames().add(frame);
		time.playFromStart();
	}
}
