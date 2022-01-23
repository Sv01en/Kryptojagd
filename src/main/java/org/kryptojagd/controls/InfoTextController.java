package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * @author Leah Schlimm
 */
public class InfoTextController extends AbstractController {

	@FXML
	public Label label;

	@FXML
	public void initialize() {
		String text = RESOURCE_BUNDLE.getString("start_text");
		label.setText(text);
	}

	/**
	 * Handles button press event
	 * @param event that is received
	 */
	 @FXML
	 void closeInfotext(ActionEvent event) {
		 System.out.println("Es wurde auf Schließen geklickt!");
		 mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
	 }
}
