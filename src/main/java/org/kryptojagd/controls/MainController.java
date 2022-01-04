package org.kryptojagd.controls;

import java.util.HashMap;
import java.util.List;

import org.kryptojagd.level.Level;
import org.kryptojagd.presentation.PresentationManager;

public class MainController {
	
	private PresentationManager fw;
	
	private HashMap levelListe;
	private Level currentLevel;
	
	public MainController(PresentationManager fw, HashMap levels) {
		this.fw = fw;
		this.levelListe = levels;
		AbstractController.setMainController(this);
	}
	
	public void switchWindow(String str) {
		fw.switchWindow(str);
	}

	public Level getCurrentLevel() {
		return currentLevel;
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
