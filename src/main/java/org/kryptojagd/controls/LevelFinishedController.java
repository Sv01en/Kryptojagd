package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LevelFinishedController extends AbstractController{
	
	
  @FXML
  private Button weiterButton;

  @FXML
  void klickWeiter(ActionEvent event) {
    System.out.println("Es wurde weiter geklickt!");
    mainController.switchWindow("Startfenster.fxml");
  }
    

}
