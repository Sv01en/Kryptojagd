package org.kryptojagd.level;

import org.kryptojagd.encryptionmethods.Backwards;
import org.kryptojagd.encryptionmethods.Caesar;
import org.kryptojagd.encryptionmethods.Beaufort;
import org.kryptojagd.encryptionmethods.Vigenere;
import org.kryptojagd.level.countdown.CountdownTimer;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;

import java.util.LinkedList;

/**
 * The class describes a level in the game
 *
 * @author Sonja Kuklok, Sven Strasser, Amelie Reichert, Bartosz Treyde, Leah Schlimm
 */
public class Level {

	private DecryptionTask decryptionTask;

	private EncryptionTask encryptionTask;

	private CountdownTimer countdownTimer;

	private LinkedList<MultipleChoiceTask> multipleChoiceTasks;

	private int timeInSec;

	private int currentTime;

	private int id;

	private int timePenalty;

	private int currentMultipleChoiceTask;

	private boolean multipleChoiceFinished;

	/**
	 * Creates a {@link Level}
	 *
	 * @param decryptionTask      first task in level is a decryptionTask
	 * @param encryptionTask      second task in level is a encryptionTask
	 * @param multipleChoiceTasks third and last task is a LinkedList of multiple choice questions
	 * @param timeInSec           the time in seconds
	 */
	public Level(DecryptionTask decryptionTask, EncryptionTask encryptionTask,
				 LinkedList<MultipleChoiceTask> multipleChoiceTasks, int timeInSec) {
		this.decryptionTask = decryptionTask;
		this.encryptionTask = encryptionTask;
		this.multipleChoiceTasks = multipleChoiceTasks;
		this.timeInSec = timeInSec;
		this.timePenalty = this.decryptionTask.getTimePenalty();
		this.currentTime = this.timeInSec;
		this.currentMultipleChoiceTask = 0;
		this.multipleChoiceFinished = false;
		proveEncryptionMethod(this.encryptionTask.getEncryption());
	}

	/**
	 * Getter for the decryption task
	 * @return decryption task of the level
	 */
	public DecryptionTask getDecryptionTask() {
		return this.decryptionTask;
	}

	/**
	 * Getter for encryption task
	 * @return encryption task of the level
	 */
	public EncryptionTask getEncryptionTask() {
		return encryptionTask;
	}

	/**
	 * Getter for multiple choice task
	 * @return multiple choice task of the level
	 */
	public MultipleChoiceTask getCurrentMultipleChoiceTask() {
		return multipleChoiceTasks.get(this.currentMultipleChoiceTask);
	}

	/**
	 * Proofs the answer of the current multiple choice task,
	 * if the answer is true, it removes the right answered question
	 *
	 * @param answer answer of the player to the multipleChoiceTask
	 * @return true, if the answer is false
	 * 			false, if the answer is true
	 */
	public boolean proveMultipleChoice(String answer) {
		if (!this.multipleChoiceTasks.get(this.currentMultipleChoiceTask).proofAnswer(answer)) {
			this.countdownTimer.reduceTimer(this.timePenalty);
			return false;
		}
		this.currentMultipleChoiceTask++;
		if (this.currentMultipleChoiceTask == this.multipleChoiceTasks.size()) {
			this.multipleChoiceFinished = true;
		}
		return true;

	}

	/**
	 * proves the decryption task.
	 * @param answer string given by the GUI
	 * @return true or false
	 */
	public boolean proveDecryptionTask(String answer) {
		if (!this.decryptionTask.proofAnswer(answer)) {
			this.countdownTimer.reduceTimer(this.timePenalty);
		}
		return this.decryptionTask.proofAnswer(answer);
	}

	/**
	 * Checks if the given answer is the correct city
	 * @param answer answer to check
	 * @return true if the answer is correct else false
	 */
	public boolean proveCityTask(int answer) {
		if (!decryptionTask.proofCityAnswer(answer)) {
			countdownTimer.reduceTimer(timePenalty);
		}
		return decryptionTask.proofCityAnswer(answer);
	}

	/**
	 * Proves the input of the encryption task.
	 * @param answer given as a string from the gui
	 * @return true or false
	 */
	public boolean proveEncryptionTask(String answer) {
		if (this.encryptionTask.proofAnswer(answer)) {
			this.countdownTimer.reduceTimer(this.timePenalty);
		}
		return this.encryptionTask.proofAnswer(answer);
	}

	/**
	 * Set up the correct encryption method
	 * @param encryptionMethod name given as a string
	 */
	private void proveEncryptionMethod(String encryptionMethod) {
		switch (encryptionMethod) {
			case "Backwards":
				encryptionTask.setEncryptionMethod(new Backwards());
				break;
			case ("Caesar"):
				encryptionTask.setEncryptionMethod(new Caesar());
				break;
			case("Vigenere"):
				encryptionTask.setEncryptionMethod(new Vigenere());
				break;
			case("Beaufort"):
				encryptionTask.setEncryptionMethod(new Beaufort());
				break;
			default:
				System.out.println("Error while trying to get Encryption.");
				break;
		}
	}

	/**
	 * Proofs, if every multiple choice task is answered.
	 * If true, the counter for the current multiple choice question is set to 0
	 *
	 * @return true, if there is no more multiple choice task
	 */
	public boolean multipleChoiceIsFinished() {
		return this.multipleChoiceFinished;
	}

	/**
	 * Returns if the decryption task is finished
	 * @return true if the task is finished, otherwise false
	 */
	public boolean decryptionIsFinished() {
		return this.decryptionTask.getCorrectAnswer();
	}

	/**
	 * Getter to detect if the city task is finished
	 * @return true if it is, else false
	 */
	public boolean cityIsFinished() {
		return this.decryptionTask.getCorrectAnswerCity();
	}

	/**
	 * Getter for the correct city name
	 * @return correct city name
	 */
	public String getCity() {
		return decryptionTask.getCity();
	}

	/**
	 * Getter, if the city task is currently showing
	 * @return true if it is showing, else false
	 */
	public boolean isCityTaskShowing() {
		return decryptionTask.isCityTaskShowing();
	}

	/**
	 * Switches from the decryption to the city task
	 */
	public void setCityShowing() {
		decryptionTask.setCityShowing();
	}

	/**
	 * Returns if the encryption task is finished
	 * @return true if the task is finished, otherwise false
	 */
	public boolean encryptionTaskFinished() {
		return this.encryptionTask.getTaskCompleted();
	}

	/**
	 * Sets the id of the level.
	 * @param id given as an integer
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the id of the level.
	 * @return id of the level as an integer
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the remaining time from the {@link CountdownTimer} as an integer.
	 * @return the current time of the {@link CountdownTimer}
	 */
	public int getTimeInSec() {
		if (this.countdownTimer != null) {
			this.currentTime = Integer.parseInt(this.countdownTimer.getCurrentValue());
		}
		return this.currentTime;
	}

	/**
	 * Starts the countdown.
	 */
	public void startCountdown() {
		this.countdownTimer = new CountdownTimer(this.currentTime);
	}

	/**
	 * Clears the level attributes at the end of the level.
	 */
	public void clearLevel() {
		this.multipleChoiceFinished = false;
		this.currentMultipleChoiceTask = 0;
		this.currentTime = this.timeInSec;
		this.countdownTimer.cancelTimerTask();
		this.decryptionTask.clearDecryptionTask();
	}
}