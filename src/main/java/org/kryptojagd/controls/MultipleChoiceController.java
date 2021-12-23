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
    private Button answer1;

    @FXML
    private Button answer2;

    @FXML
    private Button answer3;

    private final Level level = mainController.getCurrentLevel();

    /**
     * Initialize a MultipleChoiceController
     */
    private void init(){
        QuestionField.setText(level.getCurrentMultipleChoiceTask().getQuestion());
        String[] possibilities = level.getCurrentMultipleChoiceTask().getPossibilities();
        for (String answer : possibilities) {
            answer1.setText(answer);
        }
    }



    @FXML
    void clickAnswer1(ActionEvent event) {
        level.proofMultipleChoice(answer1.getText());
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
