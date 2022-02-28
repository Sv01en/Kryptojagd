package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.kryptojagd.controls.resources.Messages;

/**
 * @author Leah Schlimm, Bartosz Treyde
 */
public class InfoTextController extends AbstractController {

	/**
	 * The Label.
	 */
	@FXML
	private Label label;

	/**
	 * Initializes a InfoTextController with the info text
	 */
	@FXML
	public void initialize() {
		String text = Messages.START_TEXT;
		label.setText(text);
	}

	/**
	 * Handles button press event
	 * @param event that is received
	 */
	 @FXML
	 void closeInfotext(ActionEvent event) {
		 mainController.startLevel();
	 }
}
