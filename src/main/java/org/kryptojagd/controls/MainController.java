package org.kryptojagd.controls;

import java.util.List;

import org.kryptojagd.level.Level;
import org.kryptojagd.presentation.PresentationManager;

public class MainController {
	
	private PresentationManager fw;
	
	private List<Level> levelListe;
	private Level currentLevel;
	
	public MainController(PresentationManager fw) {
		this.fw = fw;
		AbstractController.setMainController(this);
	}
	
	public void switchWindow(String str) {
		fw.switchWindow(str);
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	private void initiliazeLevel() {
		
	}

	/**
	 * Runs the multiple choice tasks
	 * First it sets the first multiple choice question.
	 * Than it proofs the given answer and proofs the next multiple choice question
	 * until every question is answered
	 *
	 */
	public void questionFinished(){
		//####### Isn't finished! ############
		if(!currentLevel.multipleChoiceIsFinished()) {
			switchWindow("MultipleChoice.fxml");
		}
	}

}
