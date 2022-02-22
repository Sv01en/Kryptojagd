package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.kryptojagd.fileprocessing.ReadDirectory;

public class SettingsController extends AbstractController {

    @FXML
    public Button darkLightToggle;

    @FXML
    public void clickDarkLightToggle(ActionEvent actionEvent) {
        mainController.toggleDarkmode();
        mainController.switchWindowWithCSS("Settings.fxml", ReadDirectory.CSS_FILE_START);
    }

    @FXML
    public void clickBack(ActionEvent actionEvent) {
        mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
    }
}
