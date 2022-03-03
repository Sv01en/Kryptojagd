package org.kryptojagd.controls;

import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.pointSystem.PointSystem;
import org.kryptojagd.presentation.PresentationManager;
import java.util.ArrayList;

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
	protected ArrayList<Integer> clearedLevelIndexes = new ArrayList();

	protected boolean multipleChoiceTaskSucceeded;

	protected boolean decryptionTaskSucceeded;

	protected boolean decryptionTextTaskSucceeded;

	protected boolean encryptionTaskSucceeded;

	protected boolean cityTaskFinished;

	private int currentLevelPosition;

	/**
	 * The constant TASK_FINISHED_FXML.
	 */
	public static final String TASK_FINISHED_FXML = "TaskFinished.fxml";


	/**
	 * Constractor of a MainController
	 *
	 * @param fw            the fw
	 * @param currentLevel  the current Level, which is played
	 * @param allLevels     the levels
	 * @param clearedLevels the cleared levels
	 */
	public MainController(PresentationManager fw, Level currentLevel, ArrayList<Level> allLevels, int clearedLevels) {
		this.fw = fw;
		this.currentLevel = currentLevel;
		this.currentLevelPosition = 0;
		this.allLevels = allLevels;
		this.clearedLevels = clearedLevels;
		AbstractController.setMainController(this);
	}

	/**
	 * Starts the current level
	 */
	public void startLevel() {
		switchWindowWithCSS(currentLevel.getCurrentTask().toString() + ".fxml", ReadDirectory.CSS_FILE_START);
	}

	/**
	 * Sets the next level
	 */
	public void setNextLevel() {
		this.currentLevel = allLevels.get(clearedLevels);
		this.currentLevelPosition++;
		PointSystem.setCurrentLevel(currentLevelPosition);
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
		if (!clearedLevelIndexes.contains(currentLevel.getId())) {
			clearedLevelIndexes.add(currentLevel.getId());
		}
	}

	/**
	 * Toggles dark mode on if its off and vice versa.
	 */
	public void toggleDarkmode() {
		fw.toggleDarkmode();
	}

	/**
	 * Starts a level by the given position in the list.
	 * @param id position in the list
	 */
	public void startLevelByPosition(int id) {
		PointSystem.setPlayedLevels(id + 1);
		this.currentLevelPosition = id;
		this.currentLevel = this.allLevels.get(id);
		switchWindowWithCSS(currentLevel.getCurrentTask().toString()
				+ ".fxml", ReadDirectory.CSS_FILE_START);
	}

	/**
	 * Returns the position in the list of the current level.
	 * @return position as an integer
	 */
	public int getCurrentLevelPosition() {
		return this.currentLevelPosition;
	}
	
	/**
	 * Unlocks all levels of the game.
	 */
	public void unlockAllLevels() {
		LevelSelectorController.unlockAllLevels();
	}

	public void resetLevels() throws Exception {
		this.allLevels = ReadDirectory.initialize();
	}
}
