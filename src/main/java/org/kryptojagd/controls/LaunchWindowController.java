package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LaunchWindowController extends AbstractController {
	
	

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
    	mainController.switchWindow("Infotext.fxml");
    }

    @FXML
    void klickStart(ActionEvent event) {
    	System.out.println("Es wurde auf Start geklickt!");
    	mainController.runLevel();
    }
    
   
    @FXML
    public void initialize() {
    	
    }
    
 

}

