package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SettingsController extends AbstractController {

    @FXML
    public Button darkLightToggle;

    @FXML
    public void clickDarkLightToggle(ActionEvent actionEvent) {
        mainController.toggleDarkmode();
        mainController.switchWindowWithCSS("Settings.fxml", "../css/startwindow.css");
    }

    @FXML
    public void clickBack(ActionEvent actionEvent) {
        mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
    }
}
