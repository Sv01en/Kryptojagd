package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LevelSelectorController extends AbstractController {



    @FXML
    void clickBack(ActionEvent event) {
        System.out.println("Gehe zurück zum Startmenü!");
        mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
    }

    @FXML
    void clickLevel1(ActionEvent event) {
    }
}
