package org.kryptojagd.steuerung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartfensterController extends AbstractController {
	
	

    @FXML
    private Button startButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button einstellungenButton;

    @FXML
    void klickEinstellungen(ActionEvent event) {

    }

    @FXML
    void klickInfo(ActionEvent event) {
    	System.out.println("Es wurde auf Info geklickt!");
    	hs.wechsleFenster("Infotext.fxml");
    }

    @FXML
    void klickStart(ActionEvent event) {
    	System.out.println("Es wurde auf Start geklickt!");
    	hs.wechsleFenster("Entschluesselung.fxml");
    }
    
 

}

