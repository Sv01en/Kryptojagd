package org.kryptojagd.steuerung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LevelabschlussController extends AbstractController{
	
	
    @FXML
    private Button weiterButton;

    @FXML
    void klickWeiter(ActionEvent event) {
    	System.out.println("Es wurde weiter geklickt!");
    	hs.wechsleFenster("Startfenster.fxml");
    }
    

}
