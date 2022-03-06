package org.kryptojagd.controls;

import org.kryptojagd.controls.levels.LevelHandler;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.pointSystem.PointSystem;
import org.kryptojagd.presentation.PresentationManager;
import java.util.ArrayList;

/**
 * The class controls every kind of controller and puts them together
 *
 * @author Michail Petermann, Sonja Kuklok, Sven Strasser, Leah Schlimm, Bartosz Treyde
 */
public class MainController {

	private PresentationManager fw;

	private Level currentLevel;

	private ArrayList<Level> allLevels;

	private ArrayList<Level> playedLevels = new ArrayList<>();

	private int clearedLevels;

	protected ArrayList<Integer> clearedLevelIndexes = new ArrayList();

	protected boolean cityTaskFinished;

	protected static boolean isBeaufortDecryption = false;

	private int currentLevelPosition;

	/**
	 * The constant TASK_FINISHED_FXML.
	 */
	public static final String TASK_FINISHED_FXML = "TaskFinished.fxml";

	LevelHandler levelHandler;


	/**
	 * Constructor of a MainController
	 *
	 * @param fw            the fw
	 * @param currentLevel  the current Level, which is played
	 * @param allLevels     the levels
	 * @param clearedLevels the cleared levels
	 */
	public MainController(PresentationManager fw, Level currentLevel, ArrayList<Level> allLevels, int clearedLevels)
			throws Exception {
		this.fw = fw;
		//this.currentLevel = currentLevel;
		this.currentLevelPosition = 0;
		this.allLevels = allLevels;
		this.clearedLevels = clearedLevels;
		this.playedLevels.add(currentLevel);
		AbstractController.setMainController(this);

		this.levelHandler = new LevelHandler(allLevels);
		this.currentLevel = this.levelHandler.getLevel(0);
	}

	public MainController(PresentationManager fw, Level currentLevel, ArrayList<Level> allLevels,
						  LevelHandler levelHandler) throws Exception {
		this.fw = fw;
		this.allLevels = allLevels;
		AbstractController.setMainController(this);
		this.levelHandler = levelHandler;
		this.currentLevel = currentLevel;
	}

	public LevelHandler getLevelHandler() {
		return this.levelHandler;
	}

	/**
	 * Starts the current level
	 */
	public void startLevel() {
		addPlayableLevel(this.currentLevel.getId());
		PointSystem.setEncryptionTaskedFinished(false);
		PointSystem.setMultipleChoiceTaskFinished(0);
		PointSystem.setDecryptionTaskFinished(0);
		switchWindowWithCSS(currentLevel.getCurrentTask().toString() + ".fxml", ReadDirectory.CSS_FILE_START);
	}

	/**
	 * Getter for current level
	 * @return the current level
	 */
	public Level getCurrentLevel() {
		return currentLevel;
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
		if (!clearedLevelIndexes.contains(getCurrentLevelPosition())) {
			clearedLevelIndexes.add(getCurrentLevelPosition());
		}
	}
	/**
	 * Sets beaufort decryption.
	 *
	 * @param beaufortDecryption the beaufort decryption
	 */
	public static void setBeaufortDecryption(boolean beaufortDecryption) {
		isBeaufortDecryption = beaufortDecryption;
	}
	/**
	 * Toggles dark mode on if its off and vice versa.
	 */
	public void toggleDarkmode() {
		fw.toggleDarkmode();
	}

	/**
	 * Toggles blind mode on and off
	 */
	public void toggleBlindMode() {
		fw.toggleBlindMode();
	}

	/**
	 * Starts a level by the given position in the list.
	 * @param id position in the list
	 */
	public void startLevelByPosition(int id) throws Exception {
		PointSystem.setPlayedLevels(id + 1);
		this.currentLevel = this.levelHandler.getLevel(id);
		switchWindowWithCSS(this.currentLevel.getCurrentTask().toString()
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

	/**
	 * Sets a new current level by the given position in the list.
	 * @param position the position in the list of the new level
	 */
	public void setCurrentLevel(int position) throws Exception {
		System.out.println("Level erfolreich geändert");
		this.currentLevel = this.levelHandler.getLevel(position);
	}

	/**
	 * Sets a new given current level
	 * @param level given as a new initialize level
	 */
	public void setRestartLevel(Level level) {
		this.currentLevel = level;
	}

	/**
	 * Add a new level to the already playable levels.
	 * @param id given level id
	 */
	public void addPlayableLevel(int id) {
		for (int i = 0; i < this.playedLevels.size(); i++) {
			if (this.playedLevels.get(i).getId() != id) {
				this.playedLevels.add(this.currentLevel);
				System.out.println("Erfolgreich");
			}
		}
	}

	/**
	 * Checks if the given level id already playable.
	 * @param id given id of an level
	 * @return if level is playable.
	 */
	public boolean checkPlayable(int id) {
		for (int i = 0; i < this.playedLevels.size(); i++) {
			if (this.playedLevels.get(i).getId() == id) {
				System.out.println("Erfolgreich");
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets all levels.
	 * @param alllevels resets level list.
	 */
	public void setAllLevels(ArrayList<Level> alllevels) {
		this.allLevels = alllevels;
	}
}
