package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.kryptojagd.level.Level;

public class MultipleChoiceController extends AbstractController{

    private Level level;

    /**
     * Runs the multiple choice tasks
     * First it gives the first multiple choice question.
     * Than it proofs the given answer and proofs the next multiple choice question
     * until every question is answered
     *
     */
    private void runMultipleChoice(){
        //Isn't finished!
        String output;
        output = level.getCurrentMultipleChoiceTask().getQuestion();
        String input = "";
        level.proofMultipleChoice(input);
        while (!level.multipleChoiceIsFinished()) {
            output = level.proofMultipleChoice(input);
        }
    }

    @FXML
    private Button antwort1;

    @FXML
    private Button antwort2;

    @FXML
    private Button antwort3;

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
