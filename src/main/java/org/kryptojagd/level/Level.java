package org.kryptojagd.level;

import org.kryptojagd.encryptionmethods.*;
import org.kryptojagd.level.countdown.CountdownTimer;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;

import java.util.LinkedList;

/**
 * The class describes a level in the game
 *
 * @author Sonja, Sven, Amelie, Bartosz
 */
public class Level {

	private DecryptionTask decryptionTask;
	private EncryptionTask encryptionTask;
	private CountdownTimer countdownTimer;

	private LinkedList<MultipleChoiceTask> multipleChoiceTasks;

	private boolean isRunning;
	private int timeInSec;
	private int currentTime;
	private int id;
	private int timePenalty;
	private int currentMultipleChoiceTask;
	private boolean multipleChoiceFinished;

	/**
	 * Creates a {@link Level}
	 *
	 * @param decryptionTask first task in level is a decryptionTask
	 * @param encryptionTask second task in level is a encryptionTask
	 * @param multipleChoiceTasks third and last task is a LinkedList of multiple choice questions
	 */
	public Level(DecryptionTask decryptionTask, EncryptionTask encryptionTask,
				 LinkedList<MultipleChoiceTask> multipleChoiceTasks, int timeInSec) {
		this.decryptionTask = decryptionTask;
		this.encryptionTask = encryptionTask;
		this.multipleChoiceTasks = multipleChoiceTasks;
		this.timeInSec = timeInSec;
		this.isRunning = true;
		//Just for working....
		//TODO: Add penalty time to the json file.
		this.timePenalty = 20;
		this.currentTime = this.timeInSec;
		this.currentMultipleChoiceTask = 0;
		this.multipleChoiceFinished = false;
		proveEncryptionMethod(this.encryptionTask.getEncryption());
	}

	public DecryptionTask getDecryptionTask() {
		return this.decryptionTask;
	}

	public EncryptionTask getEncryptionTask() {
		return encryptionTask;
	}

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
		if(!this.multipleChoiceTasks.get(this.currentMultipleChoiceTask).proofAnswer(answer)) {
			this.countdownTimer.reduceTimer(this.timePenalty);
			return false;
		}
		this.currentMultipleChoiceTask++;
		if (this.currentMultipleChoiceTask == this.multipleChoiceTasks.size()) {
			this.multipleChoiceFinished = true;
		}
		return true;

	}

	public boolean proveDecryptionTask(String answer) {
		if (!this.decryptionTask.proofAnswer(answer)) {
			this.countdownTimer.reduceTimer(this.timePenalty);
		}
		return this.decryptionTask.proofAnswer(answer);
	}

	public boolean proveEncryptionTask(String answer) {
		if (this.encryptionTask.proofAnswer(answer)) {
			this.countdownTimer.reduceTimer(this.timePenalty);
		}
		return this.encryptionTask.proofAnswer(answer);
	}

	private void proveEncryptionMethod(String encryptionMethod){
		switch (encryptionMethod){
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
	 * Proofs, if every multiple choice task is answered
	 *
	 * @return true, if there is no more multiple choice task
	 */
	public boolean multipleChoiceIsFinished() {
		return this.multipleChoiceFinished;
	}

	public boolean decryptionIsFinished() {
		return this.decryptionTask.getCorrectAnswer();
	}

	public boolean encryptionTaskFinished() {
		return this.encryptionTask.getTaskCompleted();
	}

	public void isFinished() {
		this.isRunning = false;
	}

	public boolean getIsRunnig() {
		return this.isRunning;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public int getTimeInSec() {
		if (this.countdownTimer != null) {
			this.currentTime = Integer.parseInt(this.countdownTimer.getCurrentValue());
		}
		return this.currentTime;
	}

	public void startCountdown() {
		this.countdownTimer = new CountdownTimer(this.getTimeInSec());
	}

}