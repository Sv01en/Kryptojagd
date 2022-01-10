package org.kryptojagd.controls;

import org.kryptojagd.level.Level;
import org.kryptojagd.presentation.PresentationManager;

/**
 * The class controls every kind of controller and puts them together
 *
 * @author Micha, Sonja, Sven
 */
public class MainController {
	
	private PresentationManager fw;

	private Level currentLevel;

	protected boolean MultipleChoiceTaskSucceeded;

	protected boolean DecryptionTaskSucceeded;

	protected boolean EncryptionTaskSucceeded;

	/**
	 * Constractor of a MainController
	 *
	 * @param fw
	 * @param currentLevel the current Level, which is played
	 */
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
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void switchWindow(String str) {
		fw.switchWindow(str);
	}

	public void switchWindowWithCSS(String path, String css) {
		fw.switchWindowWithCSS(path, css);
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
		if (!currentLevel.decryptionIsFinished()) {
			switchWindow("Decryption.fxml");
		} else {
			switchWindow("MultipleChoice.fxml");
		}
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
