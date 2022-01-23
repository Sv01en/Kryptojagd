package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Gives the right feedback to a task and switches the window.
 *
 * @author Sonja Kuklok, Leah Schlimm
 */
public class TaskFinishedController extends AbstractController {

	@FXML
	private Label timer = new Label();

	@FXML
	private Label feedbackText;

	/**
	 * Initializes a TaskFinishedController
	 *
	 * If the answer was right, it prints out the good standard feedback
	 * If the answer was wrong, it prints out  the bad standard feedback
	 * if every multiple choice question of a level is answered, it prints out the finished multiple choice text
	 * if the city question of a level is right answered, it prints out the textAfterStartDecryption
	 *
	 */
	@FXML
	public void initialize() {
		updateTimer();
		if (rightAnswered()) {
			feedbackText.setText(RESOURCE_BUNDLE.getString("standard_feedback_good"));
		} else if (mainController.cityTaskFinished) {
			feedbackText.setText(mainController.getCurrentLevel().getTextAfterStartDecryption());
		} else if (mainController.getCurrentLevel().multipleChoiceIsFinished()) {
			feedbackText.setText(RESOURCE_BUNDLE.getString("finished_multiple_choice"));
		} else {
			feedbackText.setText(RESOURCE_BUNDLE.getString("standard_feedback_bad"));
		}
	}

	private boolean rightAnswered() {
		if (!mainController.getCurrentLevel().isCityTaskShowing() && mainController.decryptionTaskSucceeded) {
			return true;
		} else if (!mainController.getCurrentLevel().multipleChoiceIsFinished() && mainController.multipleChoiceTaskSucceeded) {
			return true;
		} else return mainController.getCurrentLevel().decryptionIsFinished() && mainController.decryptionTaskSucceeded;
	}


	/**
	 * Handles the switch to eather the next multiple choice question or the next task
	 * @param event that is received
	 */
	 @FXML
	 void switchMultipleChoice(ActionEvent event) {
	 	if (!mainController.getCurrentLevel().multipleChoiceIsFinished()) {
			String city = mainController.getCurrentLevel().getCity();
			String css = "../css/" + city + ".css";

			mainController.switchWindowWithCSS("MultipleChoice.fxml", css);
		} else {
			//mainController.switchWindow("Startfenster.fxml");
			mainController.runLevel();
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
			timer.setText(Integer.toString(mainController.getCurrentLevel().getTimeInSec()));
			if (mainController.getCurrentLevel().getTimeInSec() <= 0) {
				mainController.switchWindowWithCSS("TimeOver.fxml", "../css/startwindow.css");
				time.stop();
			}
		});
		time.getKeyFrames().add(frame);
		time.playFromStart();
	}
}
