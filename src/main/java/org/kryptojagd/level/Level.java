package org.kryptojagd.level;

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

	private int currentMultipleChoiceTask = 0;

	private String encryptionInput;

	private Encryption encryptionMethod;

	private ArrayList<Task> tasks = new ArrayList<>();

	private Task currentTask;

	private int key;

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
		proveEncryptionMethod(this.encryptionTask.getEncryption());
	}

	public String getFeedback() {
		return feedback;
	}

	/**
	 * Getter for levelCompleted
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

	public void setNextTask(){
		if (this.currentTask.getTaskCompleted()) {
			this.currentTask = tasks.get(indexTask);
			indexTask++;
		}
	}

	/**
	 * Getter for current task
	 * @return current task witch is in process
	 */
	public Task getTask(String name) {
		for (Task task : tasks) {
			if(task.getName() != null) {
				if (task.getName().equals(name)) {
					return task;
				}
			}
		}
		return null;
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
	 * Proves the current task
	 *
	 * reduces the time, if the answer is wrong
	 * Also sets the right feedback
	 * good feedback, if the answer is right
	 * bad feedback, if the answer is wrong
	 * an alternative feedback, if a special task is over or the whole level
	 * @param answer string given by the GUI
	 * @return true or false
	 */
	public boolean proveTask(String answer) {
		if (!this.currentTask.proveAnswer(answer)) {
			this.countdownTimer.reduceTimer(this.timePenalty);
			feedback = Messages.STANDARD_FEEDBACK_BAD;
			return this.currentTask.proveAnswer(answer);
		}
		if (this.currentTask.getTaskCompleted() ) {
			feedback = Messages.STANDARD_FEEDBACK_GOOD;
			alternativeFeedback();
		}
		if (isLevelCompleted()) {
			feedback = Messages.LEVEL_FINISHED;
		}
		return this.currentTask.proveAnswer(answer);
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
	 *
	 * Reduces the timer, if the answer is wrong
	 * Also sets the right feedback
	 * good feedback, if the answer is right
	 * bad feedback, if the answer is wrong
	 * @param answer answer to check
	 * @return true if the answer is correct else false
	 */
	public boolean proveEncryptionType(String answer) {
		if (!decryptionTask.proveEncryptionType(answer)) {
			countdownTimer.reduceTimer(timePenalty);
			feedback = Messages.STANDARD_FEEDBACK_BAD;
		} else {
			feedback = Messages.STANDARD_FEEDBACK_GOOD;
		}
		return decryptionTask.proveEncryptionType(answer);
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
				if (this.id == 2) {
					this.encryptionMethod.setKey(3);
					this.key = 3;
				} else {
					this.key = (int) (Math.random() * (26 - 1) + 1);
					this.encryptionMethod.setKey(this.key);
				}
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

	public int getKey() {
		return this.key;
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
		this.currentMultipleChoiceTask = 0;
		this.currentTime = this.timeInSec;
		this.countdownTimer.cancelTimerTask();
		this.currentTask = decryptionTask;
		for (Task task: tasks) {
			if (task.getTaskCompleted()) {
				task.setTaskCompletedEnd();
			}
		}
	}

	/**
	 * Returns the stored {@link Level#encryptionInput}.
	 * @return {@link Level#encryptionInput} as a string.
	 */
	public String getEncryptionInput() {
		return this.encryptionInput;
	}
}