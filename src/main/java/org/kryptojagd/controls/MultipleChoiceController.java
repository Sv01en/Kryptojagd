package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.kryptojagd.level.Level;

public class MultipleChoiceController extends AbstractController{

    @FXML
    private Label QuestionField;

    @FXML
    private Button antwort1;

    @FXML
    private Button antwort2;

    @FXML
    private Button antwort3;

    private final Level level = mainController.getCurrentLevel();

    private void init(){
        QuestionField.setText(level.getCurrentMultipleChoiceTask().getQuestion());

    }



    @FXML
    void clickAnswer1(ActionEvent event) {
        level.proofMultipleChoice(antwort1.getText());
    }


    @FXML
    void klickAntwort1(ActionEvent event) {
    	System.out.println("Antwort 1 wurde ausgewäht!");
    	mainController.switchWindow("Levelabschluss.fxml");
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
