package org.kryptojagd.controls;

import java.util.HashMap;
import java.util.List;

import org.kryptojagd.level.Level;
import org.kryptojagd.presentation.PresentationManager;

/**
 * The class controls every kind of controller and puts them together
 */
public class MainController {
	
	private PresentationManager fw;

	private HashMap<Integer, Level> allLevels;
	private Level currentLevel;

	public MainController(PresentationManager fw, HashMap<Integer, Level> levels) {
		this.fw = fw;
		this.allLevels = levels;
		this.currentLevel = levels.get(1);
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
