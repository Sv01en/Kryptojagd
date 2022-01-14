package org.kryptojagd.controls;

import javafx.stage.Stage;
import org.kryptojagd.level.Level;
import org.kryptojagd.presentation.PresentationManager;

import java.util.ArrayList;

/**
 * The class controls every kind of controller and puts them together
 *
 * @author Micha, Sonja, Sven
 */
public class MainController {
	
	private PresentationManager fw;

	private Level currentLevel;

	private ArrayList<Level> allLevels;

	private int clearedLevels;

	protected boolean MultipleChoiceTaskSucceeded;

	protected boolean DecryptionTaskSucceeded;

	protected boolean EncryptionTaskSucceeded;

	/**
	 * Constractor of a MainController
	 *
	 * @param fw
	 * @param currentLevel the current Level, which is played
	 */
	public MainController(PresentationManager fw, Level currentLevel, ArrayList<Level> allLevels, int clearedLevels) {
		this.fw = fw;
		this.currentLevel = currentLevel;
		this.allLevels = allLevels;
		this.clearedLevels = clearedLevels;
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

	public int getClearedLevels() {
		return clearedLevels;
	}

	public int getAllLevelCount() {
		return allLevels.size();
	}

	public ArrayList<Level> getAllLevels() {
		return allLevels;
	}

	public PresentationManager getPresentationManager() {
		return fw;
	}

	public Stage getStage() {
		return fw.getStage();
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
			switchWindowWithCSS("Decryption.fxml", "../css/startwindow.css");
		} else {
			switchWindowWithCSS("MultipleChoice.fxml", "../css/startwindow.css");
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
