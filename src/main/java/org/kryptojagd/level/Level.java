package org.kryptojagd.level;

import org.kryptojagd.controls.MainController;
import org.kryptojagd.controls.resources.Messages;
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

	private String feedback;

	private DecryptionTask decryptionTask;

	private EncryptionTask encryptionTask;

	private CountdownTimer countdownTimer;

	private LinkedList<MultipleChoiceTask> multipleChoiceTasks;

	private int timeInSec;

	private int currentTime;

	private int id;

	private int indexTask = 1;

	private int timePenalty;

	private ArrayList<Task> tasks = new ArrayList<>();

	private Task currentTask;

	private boolean firstTryTimer = true;

	private boolean encryptionTypeSucceeded = false;

	/**
	 * Sets first try timer.
	 *
	 * @param firstTryTimer the first try timer
	 */
	public void setFirstTryTimer(boolean firstTryTimer) {
		this.firstTryTimer = firstTryTimer;
	}

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
		this.tasks.add(decryptionTask);
		this.tasks.add(decryptionTask.getCityTask());
		this.tasks.addAll(multipleChoiceTasks);
		this.tasks.add(encryptionTask);
		this.timeInSec = timeInSec;
		this.timePenalty = this.decryptionTask.getTimePenalty();
		this.currentTime = this.timeInSec;
		initializeEncryptionMethod(this.encryptionTask.getEncryptionType());
	}

	/**
	 * Gets feedback.
	 *
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * Getter for EncryptionTypeTaskSucceeded
	 *
	 * @return true, if the right EncryptionType was answered
	 */
	public boolean isEncryptionTypeSucceeded() {
		return encryptionTypeSucceeded;
	}

	/**
	 * Getter for levelCompleted
	 *
	 * @return true, if every task in the level is completed
	 */
	public boolean isLevelCompleted() {
		for (Task task: tasks) {
			if (!task.getTaskCompleted()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Set next task.
	 */
	public void setNextTask() {
		if (this.currentTask.getTaskCompleted()) {
			this.currentTask = tasks.get(indexTask);
			indexTask++;
		}
	}

	/**
	 * Getter for current task
	 *
	 * @param name the task
	 * @return current task witch is in process
	 */
	public Task getTask(String name) {
		for (Task task : tasks) {
			if (task.getName() != null) {
				if (task.getName().equals(name)) {
					return task;
				}
			}
		}
		return null;
	}

	/**
	 * Getter for current task
	 *
	 * @return current task witch is in process
	 */
	public Task getCurrentTask() {
		return currentTask;
	}

	/**
	 * Gets city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return decryptionTask.getCityTask().getCorrectAnswer();
	}

	/**
	 * Proves the current task
	 * <p>
	 * reduces the time, if the answer is wrong
	 * Also sets the right feedback
	 * good feedback, if the answer is right
	 * bad feedback, if the answer is wrong
	 * an alternative feedback, if a special task is over or the whole level
	 *
	 * @param answer string given by the GUI
	 */
	public void proveTask(String answer) {
		if (!this.currentTask.proveAnswer(answer)) {
			this.countdownTimer.reduceTimer(this.timePenalty);
			feedback = Messages.STANDARD_FEEDBACK_BAD;
			this.currentTask.proveAnswer(answer);
			return;
		}
		if (this.currentTask.getTaskCompleted()) {
			feedback = Messages.STANDARD_FEEDBACK_GOOD;
			alternativeFeedback();
		}
		if (isLevelCompleted()) {
			feedback = Messages.LEVEL_FINISHED;
			if (this.firstTryTimer) {
				currentTask.setScore(currentTask.getScore() + currentTime);
			}
		}
		this.currentTask.proveAnswer(answer);
	}

	/**
	 * Sets an alternative feedback
	 *
	 * if the city task is completed, it sets the text after the city task
	 * if the whole multiple choice is finished, it sets the right {@link Messages}
	 */
	private void alternativeFeedback() {
		if (this.currentTask.getName() != null) {
			if (this.currentTask.getName().equals("cityTask")) {
				feedback = decryptionTask.getTextAfterCityTask();
				return;
			}
		}
		if (isMultipleChoiceFinished()) {
			feedback = Messages.FINISHED_MULTIPLE_CHOICE;
		}
	}

	/**
	 * Proves if every multiple choice is completed
	 *
	 * @return true, if every multiple choice is completed
	 */
	private boolean isMultipleChoiceFinished() {
		for (Task task : multipleChoiceTasks) {
			if (!task.getTaskCompleted()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the given answer is the correct city
	 * <p>
	 * Reduces the timer, if the answer is wrong
	 * Also sets the right feedback
	 * good feedback, if the answer is right
	 * bad feedback, if the answer is wrong
	 *
	 * @param answer answer to check
	 */
	public void proveEncryptionType(String answer) {
		if (!decryptionTask.proveEncryptionType(answer)) {
			countdownTimer.reduceTimer(timePenalty);
			feedback = Messages.STANDARD_FEEDBACK_BAD;
		} else {
			feedback = Messages.STANDARD_FEEDBACK_GOOD;
		}
		encryptionTypeSucceeded = decryptionTask.proveEncryptionType(answer);
	}

	/**
	 * Set up the correct encryption method
	 * @param encryptionMethod name given as a string
	 */
	private void initializeEncryptionMethod(String encryptionMethod) {
		Encryption encryption = null;
		switch (encryptionMethod) {
			case "Backwards":
				encryption = new Backwards();
				break;
			case ("Caesar"):
				encryption = new Caesar();
				break;
			case("Vigenere"):
				MainController.setBeaufortDecryption(false);
				encryption = new Vigenere();
				break;
			case("Beaufort"):
				MainController.setBeaufortDecryption(true);
				encryption = new Beaufort();
				break;
			default:
				System.out.println("Error Encryption does not exist.");
				break;
		}
		this.decryptionTask.setEncryptionMethod(encryption);
		this.encryptionTask.setEncryptionMethod(encryption);
	}

	/**
	 * Sets the id of the level.
	 *
	 * @param id given as an integer
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the id of the level.
	 *
	 * @return id of the level as an integer
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the remaining time from the {@link CountdownTimer} as an integer.
	 *
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
		this.encryptionTypeSucceeded = false;
		this.currentTask = decryptionTask;
		for (Task task: tasks) {
			if (task.getTaskCompleted()) {
				task.setTaskCompletedEnd();
			}
		}
	}

}