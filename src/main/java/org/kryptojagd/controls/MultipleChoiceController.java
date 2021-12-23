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
     * Initializes a MultipleChoiceController
     *
     * Sets the Question of the MultipleChoicetask
     * and sets their answers
     *
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
    void clickAnswer2(ActionEvent event) {
        level.proofMultipleChoice(answer2.getText());
    }

    @FXML
    void clickAnswer3(ActionEvent event) {
        level.proofMultipleChoice(answer3.getText());
    }

}
