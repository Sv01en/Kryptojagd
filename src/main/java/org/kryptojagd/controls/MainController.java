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
	 *
	 * If every multiple choice task is answered, it switches the window to the leveEnd
	 * if not, it switches the window to a new multiple choice task
	 *
	 */
	public void runMultipleChoice(){
		if(!currentLevel.multipleChoiceIsFinished()) {
			switchWindow("MultipleChoice.fxml");
		} else {
			switchWindow("Levelabschluss.fxml");
		}
	}
}
