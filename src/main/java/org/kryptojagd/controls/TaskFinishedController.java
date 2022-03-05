package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.pointSystem.PointSystem;
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
	private Label score;

	@FXML
	private Label translusentLabel;

	@FXML
	private Label feedbackText;

	/**
	 * Initializes a TaskFinishedController
	 *
	 * Updates the timer and sets the feedback text
	 */
	@FXML
	public void initialize() {
		updateTimer(timer);
		feedbackText.setText(level.getFeedback());
		String text = "Punktestand: " + PointSystem.getScore();
		if (!CryptoToolController.isSystemHacked()) {
			score.setText(text);
			translusentLabel.setText(text);
		} else {
			score.setManaged(false);
		}
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
				css = ReadDirectory.CSS_FILES + city + ".css";
			} else {
	 			css = ReadDirectory.CSS_FILE_START;
			}
			level.setNextTask();
			mainController.switchWindowWithCSS(level.getCurrentTask().toString() + ".fxml", css);
		} else {
		 	String css;
			String city = level.getCity();
			css = ReadDirectory.CSS_FILES + city + ".css";
			mainController.switchWindowWithCSS(LEVEL_FINISHED, css);
		}
	 }
}