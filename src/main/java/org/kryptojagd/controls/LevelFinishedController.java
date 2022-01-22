package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * The type Level finished controller.
 */
public class LevelFinishedController extends AbstractController {

  @FXML
  private Button nextButton;

  @FXML
  void clickNext(ActionEvent event) {
    System.out.println("Es wurde weiter geklickt!");
    mainController.switchWindow("Startfenster.fxml");
  }
}
