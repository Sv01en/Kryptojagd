package org.kryptojagd.controls;

import java.io.IOException;

import org.kryptojagd.cryptotools.CryptoTool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.kryptojagd.fileprocessing.ReadDirectory;

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
        mainController.switchWindowWithCSS("Settings.fxml", ReadDirectory.CSS_FILE_START);
    }

    /**
     * Handles press on info button
     * @param event that is received
     */
    @FXML
    void clickInfo(ActionEvent event) {
    	System.out.println("Es wurde auf Info geklickt!");
        mainController.switchWindowWithCSS("Infotext.fxml", ReadDirectory.CSS_FILE_START);
    	//mainController.switchWindow("Infotext.fxml");
    }

    /**
     * Handles press on start button
     * @param event that is received
     */
    @FXML
    void clickStart(ActionEvent event) {
    	System.out.println("Es wurde auf Start geklickt!");
        mainController.switchWindowWithCSS("Infotext.fxml", ReadDirectory.CSS_FILE_START);
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {

    }

    /**
     * Handles press on level selector button
     * @param event that is received
     */
    @FXML
    public void clickLevelSelector(ActionEvent event) {
        mainController.switchWindowWithCSS("LevelSelector.fxml", ReadDirectory.CSS_FILE_START);
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
    
    @FXML
    void clickCryptoTool(ActionEvent event) {
    	mainController.switchWindowWithCSS("CryptoTool.fxml", ReadDirectory.CSS_FILE_START);
    }
}
