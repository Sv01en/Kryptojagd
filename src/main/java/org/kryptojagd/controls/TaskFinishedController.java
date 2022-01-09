package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


/**
 * Gives the right feedback to a task and switches the window.
 *
 * @author Sonja
 */
public class TaskFinishedController extends AbstractController{

	/**
	 * Gives the right feedback to a multipleChoice and switches the window.
	 *
	 * If the answer was right, it prints out "Richtig, weiter so!"
	 * If the answer was wrong, it prints out "Leider falsch, versuche es noch einmal. Du musst dich beeilen!"
	 * in both cases it switchs to a new multiple choice window
	 * if every question of a level is answered, it prints out "Glückwunsch, du hast alle Fragen richtig beantwortet!"
	 * and switches the window to the end of the level
	 *
	 * @param event
	 */
	 @FXML
	 void handlesMultipleChoiceAnswer(ActionEvent event) {
	 	if (!mainController.getCurrentLevel().multipleChoiceIsFinished()) {
			if (mainController.taskSucceeded) {
				System.out.println("Richtig, weiter so!");
			} else {
				System.out.println("Leider falsch, versuche es noch einmal. Du musst dich beeilen!");
			}
			mainController.switchWindow("MultipleChoice.fxml");
		} else {
			System.out.println("Glückwunsch, du hast alle Fragen richtig beantwortet!");
			mainController.switchWindow("Levelabschluss.fxml");
		}
	 }


}
