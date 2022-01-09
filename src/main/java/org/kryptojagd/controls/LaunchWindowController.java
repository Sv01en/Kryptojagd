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
        //mainController.switchWindowWithCSS("Startfenster.fxml", "../css/Berlin.css");
    }

    @FXML
    void klickInfo(ActionEvent event) {
    	System.out.println("Es wurde auf Info geklickt!");
    	mainController.switchWindowWithCSS("Infotext.fxml", "../css/startwindow.css");
    }

    @FXML
    void klickStart(ActionEvent event) {
    	System.out.println("Es wurde auf Start geklickt!");
    	//mainController.switchWindow("Entschluesselung.fxml");
        mainController.switchWindowWithCSS("Entschluesselung.fxml", "../css/Berlin.css");
    }
    
   
    @FXML
    public void initialize() {
    	
    }
    
 

}

