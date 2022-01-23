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
	 * <p></p>
	 * Gives the right feedback to a multipleChoice and switches the window.
	 *<p></p>
	 * If the answer was right, it prints out "Richtig, weiter so!"<p></p>
	 * If the answer was wrong, it prints out "Leider falsch, versuche es noch einmal. Du musst dich beeilen!"<p></p>
	 * in both cases it switchs to a new multiple choice window
	 * if every question of a level is answered, it prints out "Glückwunsch, du hast alle Fragen richtig beantwortet!"
	 *
	 */
	@FXML
	public void initialize() {
		updateTimer();
		if (!mainController.getCurrentLevel().multipleChoiceIsFinished()) {
			if (mainController.multipleChoiceTaskSucceeded) {
				feedbackText.setText(RESOURCE_BUNDLE.getString("standard_feedback_good"));
			} else {
				feedbackText.setText("Die Antwort war leider falsch! "
						+ "Eve ist der Floppy-Disk einen Schritt näher gekommen. Beeile dich!");
			}
		} else {
			feedbackText.setText("Glückwunsch, du hast alle Fragen richtig beantwortet und die Floppy-Disk erhalten!\nSchicke dem NIV eine verschlüsselte Bestätigung, dass ihr die Floppy-Disk erhalten habt.");
		}
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
