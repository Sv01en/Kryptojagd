package org.kryptojagd.controls;

import java.io.IOException;

import org.kryptojagd.cryptotools.CryptoTool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the Launch Window
 *
 * @author Leah Schlimm
 */
public class StartWindowController extends AbstractController {

    /**
     * Handles press on settings button
     * @param event that is received
     */
    @FXML
    void clickSettings(ActionEvent event) {
        System.out.println("Das ist ein Test");
        //mainController.switchWindowWithCSS("EncryptionTask.fxml", "../css/startwindow.css");
    }

    /**
     * Handles press on info button
     * @param event that is received
     */
    @FXML
    void clickInfo(ActionEvent event) {
    	System.out.println("Es wurde auf Info geklickt!");
        mainController.switchWindowWithCSS("Infotext.fxml", "../css/startwindow.css");
    	//mainController.switchWindow("Infotext.fxml");
    }

    /**
     * Handles press on start button
     * @param event that is received
     */
    @FXML
    void clickStart(ActionEvent event) {
    	System.out.println("Es wurde auf Start geklickt!");
    	mainController.startLevel();
    }

    @FXML
    public void initialize() {

    }

    /**
     * Handles press on level selector button
     * @param event that is received
     */
    @FXML
    public void clickLevelSelector(ActionEvent event) {
        mainController.switchWindowWithCSS("LevelSelector.fxml", "../css/startwindow.css");
    }

    /**
     * Handles press on start button
     * @param event that is received
     */
    @FXML
    void clickCryptoCaesar(ActionEvent event) {
        System.out.println("Es wurde auf Kryptotool Cäsar Button geklickt!");
        mainController.startLevel();
    }

    /**
     * Handles press on start button
     * @param event that is received
     */
    @FXML
    void clickCryptoVigeniere(ActionEvent event) {
        System.out.println("Es wurde auf Kryptotool Vigeniére Button geklickt!");
        mainController.startLevel();
    }
    
    @FXML
    void caesar(ActionEvent event) {
    	try {
			CryptoTool.caesar(new Stage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void vigenere(ActionEvent event) {
    	try {
			CryptoTool.vigenere(new Stage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
