package org.kryptojagd.level;

import org.kryptojagd.encryptionmethods.*;
import org.kryptojagd.level.countdown.CountdownTimer;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;
import org.kryptojagd.level.tasks.Task;

import java.util.ArrayList;
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

	private int currentMultipleChoiceTask = 0;

	private boolean multipleChoiceFinished;

	private String encryptionInput;

	private Encryption encryptionMethod;

	private Task currentTask;

	private boolean levelCompleted = false;

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
		this.currentTask = decryptionTask;
		this.encryptionTask = encryptionTask;
		this.multipleChoiceTasks = multipleChoiceTasks;
		this.timeInSec = timeInSec;
		this.timePenalty = this.decryptionTask.getTimePenalty();
		this.currentTime = this.timeInSec;
		this.multipleChoiceFinished = false;
		proveEncryptionMethod(this.encryptionTask.getEncryption());
	}

	/**
	 * Getter for levelCompleted
	 * @return true, if the level is completed
	 */
	public boolean isLevelCompleted() {
		return levelCompleted;
	}

	/**
	 * Sets the nextTask, if the current task is completed
	 * otherwise nothing is changing
	 *
	 * @param currentTask the current Task
	 */
	public void setNextTask(Task currentTask){
		if (currentTask.getTaskCompleted() && !isMultipleChoiceFinished()) {
			switch (currentTask.toString()) {
				case "DecryptionTask":
					if (!decryptionTask.isEncryptionTaskCompleted()) {
						return;
					}
					if (!cityIsFinished()) {
						this.currentTask = decryptionTask.getCityTask();
						return;
					}
					this.currentTask = multipleChoiceTasks.get(currentMultipleChoiceTask);
					this.currentMultipleChoiceTask++;
					return;
				case "MultipleChoiceTask": this.currentTask = getCurrentMultipleChoiceTask();
					this.currentMultipleChoiceTask++;
					return;
			}
		}
		if (isMultipleChoiceFinished()) {
			this.currentTask = encryptionTask;
		}
	}

	/**
	 * Getter for current task
	 * @return current task witch is in process
	 */
	public Task getCurrentTask() {
		return currentTask;
	}

	public String getCity() {
		return decryptionTask.getCityTask().getCorrectAnswer();
	}

	/**
	 * Getter for encryption method
	 * @return multiple encryption method of the level
	 */
	public Encryption getEncryptionMethod() {
		return encryptionMethod;
	}

	/**
	 * Getter for multiple choice task
	 * @return multiple choice task of the level
	 */
	public MultipleChoiceTask getCurrentMultipleChoiceTask() {
		return multipleChoiceTasks.get(this.currentMultipleChoiceTask);
	}

	/**
	 * Proves if every multipleChoiceTask is completed
	 *
	 * @return true, if every task is finished
	 */
	public boolean isMultipleChoiceFinished() {
		boolean finished = true;
		for (Task multipleChoice: multipleChoiceTasks) {
			if (!multipleChoice.getTaskCompleted()) {
				finished = false;
			}
		}
		return finished;
	}

	public boolean cityIsFinished() {
		return decryptionTask.getCityTask().getTaskCompleted();
	}


	/**
	 * proves the current task
	 * reduces the time, if the answer was wrong
	 *
	 * @param answer string given by the GUI
	 * @return true or false
	 */
	public boolean proveTask(String answer) {
		if (!this.currentTask.proveAnswer(answer)) {
			this.countdownTimer.reduceTimer(this.timePenalty);
		}
		if (this.encryptionTask.getTaskCompleted() ) {
			levelCompleted = true;
		}
		return this.currentTask.proveAnswer(answer);
	}

	/**
	 * Checks if the given answer is the correct city
	 * @param answer answer to check
	 * @return true if the answer is correct else false
	 */
	public boolean proveCityTask(String answer) {
		if (!decryptionTask.proofCityAnswer(answer)) {
			countdownTimer.reduceTimer(timePenalty);
		}
		return decryptionTask.proofCityAnswer(answer);
	}

	/**
	 * Set up the correct encryption method
	 * @param encryptionMethod name given as a string
	 */
	private void proveEncryptionMethod(String encryptionMethod) {
		switch (encryptionMethod) {
			case "Backwards":
				this.encryptionMethod = new Backwards();
				break;
			case ("Caesar"):
				this.encryptionMethod = new Caesar();
				break;
			case("Vigenere"):
				this.encryptionMethod = new Vigenere();
				break;
			case("Beaufort"):
				this.encryptionMethod = new Beaufort();
				break;
			default:
				System.out.println("Error while trying to get Encryption.");
				break;
		}
		this.decryptionTask.setEncryptionMethod(this.encryptionMethod);
		this.encryptionTask.setEncryptionMethod(this.encryptionMethod);
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
	public void setCityShowing(boolean isShowing) {
		decryptionTask.setCityShowing(isShowing);
	}

	/**
	 * Getter for text to display after the city question is answered correctly
	 * @return Text to display
	 */
	public String getTextAfterStartDecryption() {
		return decryptionTask.getTextAfterStart();
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

	/**
	 * Returns the stored {@link Level#encryptionInput}.
	 * @return {@link Level#encryptionInput} as a string.
	 */
	public String getEncryptionInput() {
		return this.encryptionInput;
	}
}