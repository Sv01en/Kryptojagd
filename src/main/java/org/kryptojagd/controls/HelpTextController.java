package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.kryptojagd.level.tasks.Task;

/**
 * Controller for the fxml-file for text-based Help
 *
 * @author Amelie Reichert
 * @version 1.0
 */
public class HelpTextController extends AbstractController {

	/**
	 * The Label.
	 */
	@FXML
	private Label label;

	@FXML
	private Label score;

	/**
	 * Initializes a HelpTextController with the text-based help
	 */
	@FXML
	public void initialize() {
		String text = mainController.getCurrentLevel().getCurrentTask().getHelpText();
		label.setText(text);
		score.setText("Punktestand: " + Task.POINT_SYSTEM.getScore());
	}

	/**
	 * Handles button press event
	 * @param event that is received
	 */
	 @FXML
	 void closeHelpText(ActionEvent event) {
		 mainController.startLevel();
	 }
}