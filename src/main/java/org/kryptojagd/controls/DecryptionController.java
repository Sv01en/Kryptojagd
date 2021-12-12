package org.kryptojagd.steuerung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EntschluesselungController extends AbstractController{

	
    @FXML
    private Label verschluesselterRaetseltext;

    @FXML
    private Label frage;

    @FXML
    private Button verfahren1;

    @FXML
    private Button verfahren2;

    @FXML
    private Button verfahren3;

    @FXML
    void klickVerfahren1(ActionEvent event) {
    	System.out.println("Verfahren 1 wurde ausgewählt.");
    	hs.wechsleFenster("Quiz.fxml");
    }

    @FXML
    void klickVerfahren2(ActionEvent event) {
    	System.out.println("Verfahren 2 wurde ausgewählt.");
        hs.wechsleFenster("WrongChoice.fxml");
    }

    @FXML
    void klickVerfahren3(ActionEvent event) {
    	System.out.println("Verfahren 3 wurde ausgewählt.");
        hs.wechsleFenster("WrongChoice.fxml");
    }

  
}
