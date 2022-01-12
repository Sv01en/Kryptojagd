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
	private LinkedList<MultipleChoiceTask> multipleChoiceTasks;
	private boolean isRunning;
	private int timeInSec;
	private int id;
	private CountdownTimer countdownTimer;

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
	}

	public DecryptionTask getDecryptionTask() {
		return this.decryptionTask;
	}

	public EncryptionTask getEncryptionTask() {
		return encryptionTask;
	}

	public MultipleChoiceTask getCurrentMultipleChoiceTask() {
		return multipleChoiceTasks.getFirst();
	}

	/**
	 * Proofs the answer of the current multiple choice task,
	 * if the answer is true, it removes the right answered question
	 *
	 * @param answer answer of the player to the multipleChoiceTask
	 * @return true, if the answer is false
	 * 			false, if the answer is true
	 */
	public boolean proofMultipleChoice(String answer) {
		if(!this.multipleChoiceTasks.getFirst().proofAnswer(answer)) {
			return false;
		}
		this.multipleChoiceTasks.pop();
		return true;
	}

	public boolean proofDecryptionTask(String answer) {
		System.out.println(this.decryptionTask.proofAnswer(answer));
		return this.decryptionTask.proofAnswer(answer);
	}

	public void proveEncryptionMethod(String encryptionMethod){
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
		return this.multipleChoiceTasks.isEmpty();
	}

	public boolean decryptionIsFinished() {
		return this.decryptionTask.getCorrectAnswer();
	}

	private void isFinished() {
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
		this.timeInSec = Integer.parseInt(this.countdownTimer.getCurrentValue());
		return this.timeInSec;
	}

	/**
	 * @deprecated
	 * @param given
	 */
	public void setTimeInSec(int given) {
		this.timeInSec = given;
	}

	public void startCountdown() {
		this.countdownTimer = new CountdownTimer(this.getTimeInSec());
	}

}