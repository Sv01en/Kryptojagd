package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.kryptojagd.fileprocessing.ReadDirectory;

/**
 * The type Settings controller.
 */
public class SettingsController extends AbstractController {

    /**
     * Click dark light toggle.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void clickDarkLightToggle(ActionEvent actionEvent) {
        mainController.toggleDarkmode();
        mainController.switchWindowWithCSS("Settings.fxml", ReadDirectory.CSS_FILE_START);
    }

    /**
     * Click back.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void clickBack(ActionEvent actionEvent) {
        mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
    }

    /**
     * Click accessibility toggle.
     *
     * @param event the action event
     */
    @FXML
    public void clickBlindToggle(ActionEvent event) {
        mainController.toggleBlindMode();
        mainController.switchWindowWithCSS("Settings.fxml", ReadDirectory.CSS_FILE_START);
    }
}
