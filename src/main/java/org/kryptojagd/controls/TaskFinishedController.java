package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kryptojagd.controls.resources.Messages;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.tasks.EncryptionTask;

/**
 * Gives the right feedback to a task and switches the window.
 *
 * @author Sonja Kuklok, Leah Schlimm
 */
public class TaskFinishedController extends AbstractController {

	private Level level = mainController.getCurrentLevel();
	private EncryptionTask task = (EncryptionTask) level.getCurrentTask();
	private static final String BACK_TO_MENU = "LevelFinishedSelector.fxml";

	@FXML
	private Label timer = new Label();

	@FXML
	private Label feedbackText;

	/**
	 * Initializes a TaskFinishedController
	 *
	 * if every multiple choice question of a level is answered, it prints out the finished multiple choice text
	 * if the city question of a level is right answered, it prints out the textAfterStartDecryption
	 * If the task is completed, it prints out the good standard feedback
	 * else it prints out  the bad standard feedback
	 *
	 */
	@FXML
	public void initialize() {
		updateTimer();
		if (level.isMultipleChoiceFinished()) {
			feedbackText.setText(Messages.FINISHED_MULTIPLE_CHOICE);
		} else if (mainController.cityTaskFinished && !task.getTaskCompleted()) {
			feedbackText.setText(level.getTextAfterStartDecryption());
		} else if (task.getTaskCompleted() | level.getCurrentMultipleChoiceTask().getTaskCompleted()) {
			feedbackText.setText(Messages.STANDARD_FEEDBACK_GOOD);
		} else {
			feedbackText.setText(Messages.STANDARD_FEEDBACK_BAD);
		}
	}

	/**
	 * Handles the switch to the next task
	 * If the the decryption task as last task is answered,
	 * it switches the window to the menu
	 *
	 * @param event that is received
	 */
	 @FXML
	 void nextWindow(ActionEvent event) {
	 	if (!level.isLevelCompleted()) {
			String city = level.getCity();
			String css = "../css/" + city + ".css";
			level.setNextTask(level.getCurrentTask());
			mainController.switchWindowWithCSS(level.getCurrentTask() + ".fxml", css);
		} else {
			mainController.switchWindow(BACK_TO_MENU);
		}
	 }

	/**
	 * Updates the {@link TaskFinishedController#timer} every second in the corresponding fxml-file.
	 */
	@FXML
	void updateTimer() {
		Timeline time = new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		time.stop();
		KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
			timer.setText(Integer.toString(level.getTimeInSec()));
			if (level.getTimeInSec() <= 0) {
				mainController.switchWindowWithCSS("TimeOver.fxml", "../css/startwindow.css");
				time.stop();
			}
		});
		time.getKeyFrames().add(frame);
		time.playFromStart();
	}
}
