package org.kryptojagd.steuerung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InfotextController extends AbstractController{
	
	
	 @FXML
	 void schliesseInfotext(ActionEvent event) {
		 System.out.println("Es wurde auf Schließen geklickt!");
		 hs.wechsleFenster("Startfenster.fxml");
	 }


}
