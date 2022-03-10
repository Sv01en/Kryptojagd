package org.kryptojagd.controls.cryptotool;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.kryptojagd.controls.MainController;
import org.kryptojagd.controls.resources.Messages;

/**
 * Controller of the explanation of the Vigenere crypto tool.
 *
 * @author Amelie Reichert
 * @version 1.0
 */
public class CryptotoolExplanationController  {

	/**
	 * The Label.
	 */
	@FXML
	private Label label;


	/**
	 * Initialize.
	 */
	@FXML
	public void initialize() {
		String text;
		if (MainController.isBeaufortDecryption) {
			 text = Messages.BEAUFORT_EXPLANATION;
		} else {
			 text = Messages.VIGENERE_EXPLANATION;
		}
		label.setText(text);
	}
}
