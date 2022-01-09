package org.kryptojagd.controls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.kryptojagd.level.Level;
import org.kryptojagd.presentation.PresentationManager;

/**
 * The class controls every kind of controller and puts them together
 */
public class MainController {
	
	private PresentationManager fw;

	private Level currentLevel;
	protected boolean taskSucceeded;

	public MainController(PresentationManager fw, Level currentLevel) {
		this.fw = fw;
		this.currentLevel = currentLevel;
		AbstractController.setMainController(this);
	}

	/**
	 * Runs a whole level which each task
	 *
	 */
	public void runLevel() {
		runDecryptionTask();
		runEncryptionTask();
		runMultipleChoice();
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void switchWindow(String str) {
		fw.switchWindow(str);
	}

	/**
	 * Runs the encryption tasks
	 *
	 *
	 */
	private void runEncryptionTask() {

	}

	/**
	 * Runs the decryption tasks
	 *
	 *
	 */
	private void runDecryptionTask() {

	}

	/**
	 * Runs the multiple choice tasks
	 *
	 * If every multiple choice task is answered, it switches the window to the levelEnd
	 * if not, it switches the window to a new multiple choice task
	 *
	 */
	private void runMultipleChoice(){
		if (!currentLevel.multipleChoiceIsFinished()) {
			switchWindow("MultipleChoice.fxml");
		} else {
			switchWindow("Levelabschluss.fxml");
		}
	}
}
