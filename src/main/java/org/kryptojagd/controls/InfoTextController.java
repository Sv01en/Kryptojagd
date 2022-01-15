package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * @author Leah
 */
public class InfoTextController extends AbstractController{

	/**
	 * Handles button press event
	 * @param event that is received
	 */
	 @FXML
	 void schliesseInfotext(ActionEvent event) {
		 System.out.println("Es wurde auf Schließen geklickt!");
		 mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
	 }


}
