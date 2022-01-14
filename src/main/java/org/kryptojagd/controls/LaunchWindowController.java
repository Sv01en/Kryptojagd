package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the Launch Window
 *
 * @author Leah
 */
public class LaunchWindowController extends AbstractController {
	
	

    @FXML
    private Button startButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button einstellungenButton;

    /**
     * Handles press on settings button
     * @param event that is received
     */
    @FXML
    void klickEinstellungen(ActionEvent event) {
        System.out.println("Das ist ein Test");
        //mainController.switchWindowWithCSS("Encryption.fxml", "../css/startwindow.css");
    }

    /**
     * Handles press on info button
     * @param event that is received
     */
    @FXML
    void klickInfo(ActionEvent event) {
    	System.out.println("Es wurde auf Info geklickt!");
        mainController.switchWindowWithCSS("Infotext.fxml", "../css/startwindow.css");
    	//mainController.switchWindow("Infotext.fxml");
    }

    /**
     * Handles press on start button
     * @param event that is received
     */
    @FXML
    void klickStart(ActionEvent event) {
    	System.out.println("Es wurde auf Start geklickt!");
    	mainController.runLevel();
    }
    
   
    @FXML
    public void initialize() {
    	
    }

    /**
     * Handles press on level selector button
     * @param event that is received
     */
    @FXML
    public void klickLevelSelector(ActionEvent event) {
        mainController.switchWindowWithCSS("LevelSelector.fxml", "../css/startwindow.css");
    }
}

