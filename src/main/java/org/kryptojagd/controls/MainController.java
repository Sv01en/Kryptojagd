package org.kryptojagd.controls;

import org.kryptojagd.level.Level;
import org.kryptojagd.presentation.PresentationManager;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The class controls every kind of controller and puts them together
 *
 * @author Michail Petermann, Sonja Kuklok, Sven Strasser, Leah Schlimm
 */
public class MainController {

	private PresentationManager fw;

	private Level currentLevel;

	private ArrayList<Level> allLevels;

	private int clearedLevels;

	protected boolean multipleChoiceTaskSucceeded;

	protected boolean decryptionTaskSucceeded;

	protected boolean encryptionTaskSucceeded;

	protected boolean cityTaskFinished;

	/**
	 * Constractor of a MainController
	 *
	 * @param fw            the fw
	 * @param currentLevel  the current Level, which is played
	 * @param allLevels     the all levels
	 * @param clearedLevels the cleared levels
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
	 */
	public void runLevel() {
		runDecryptionTask();
	}

	/**
	 * Getter for current level
	 * @return the current level
	 */
	public Level getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * Switches windows
	 * @param str Window to switch to
	 */
	public void switchWindow(String str) {
		fw.switchWindow(str);
	}

	/**
	 * Switches windows and sets a style to the window
	 * @param path Window to switch to
	 * @param css Style to apply
	 */
	public void switchWindowWithCSS(String path, String css) {
		fw.switchWindowWithCSS(path, css);
	}

	/**
	 * Getter for the count of cleared Levels
	 * @return cleared level count
	 */
	public int getClearedLevels() {
		return clearedLevels;
	}

	/**
	 * Getter for the count of total levels in the game
	 * @return level count
	 */
	public int getAllLevelCount() {
		return allLevels.size();
	}

	/**
	 * Getter for the list of levels
	 * @return list of levels
	 */
	public ArrayList<Level> getAllLevels() {
		return allLevels;
	}

	/**
	 * Getter for the presentationManager
	 * @return current presentationmanager
	 */
	public PresentationManager getPresentationManager() {
		return fw;
	}

	/**
	 * Sets cleared levels plus one.
	 */
	public void setClearedLevels() {
		this.clearedLevels++;
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
		if (!currentLevel.decryptionIsFinished() && !currentLevel.cityIsFinished()) {
			System.out.println("Run decryption task");
			switchWindowWithCSS("Decryption.fxml", "../css/startwindow.css");
		} else if (!currentLevel.cityIsFinished() && currentLevel.decryptionIsFinished()) {
			System.out.println("Run city task");
			switchWindowWithCSS("Decryption.fxml", "../css/startwindow.css");
		} else if (currentLevel.decryptionIsFinished() && currentLevel.cityIsFinished()
			&& !currentLevel.multipleChoiceIsFinished()) {
			String city = currentLevel.getCity();
			String css = "../css/" + city + ".css";
			switchWindowWithCSS("MultipleChoice.fxml", css);
		} else {
			String city = currentLevel.getCity();
			String css = "../css/" + city + ".css";
			switchWindowWithCSS("Encryption.fxml", css);
		}
	}

	/**
	 * Runs the multiple choice tasks
	 *
	 * If every multiple choice task is answered, it switches the window to the levelEnd
	 * if not, it switches the window to a new multiple choice task
	 *
	 */
	private void runMultipleChoice() {
		if (!currentLevel.multipleChoiceIsFinished()) {
			switchWindow("MultipleChoice.fxml");
		} else {
			switchWindow("Levelabschluss.fxml");
		}
	}
}
