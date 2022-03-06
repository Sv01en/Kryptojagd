package org.kryptojagd.controls;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
     * Click crypto tool.
     *
     * @param event the event
     */
    @FXML
    void clickCryptoTool(ActionEvent event) {
        mainController.beaufortEncryption(false);
    	mainController.switchWindowWithCSS("CryptoTool.fxml", ReadDirectory.CSS_FILE_START);
    }
}
