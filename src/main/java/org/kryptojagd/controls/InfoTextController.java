package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InfoTextController extends AbstractController{
	
	
	 @FXML
	 void schliesseInfotext(ActionEvent event) {
		 System.out.println("Es wurde auf Schließen geklickt!");
		 mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
	 }


}
