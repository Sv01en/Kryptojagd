package org.kryptojagd.steuerung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class QuizController extends AbstractController{

    @FXML
    private Button antwort1;

    @FXML
    private Button antwort2;

    @FXML
    private Button antwort3;

    @FXML
    void klickAntwort1(ActionEvent event) {
    	System.out.println("Antwort 1 wurde ausgewäht!");
    	hs.wechsleFenster("Levelabschluss.fxml");
    }

    @FXML
    void klickAntwort2(ActionEvent event) {
    	System.out.println("Antwort 2 wurde ausgewäht!");
    }

    @FXML
    void klickAntwort3(ActionEvent event) {
    	System.out.println("Antwort 3 wurde ausgewäht!");
    }
    


}
