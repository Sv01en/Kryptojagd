package org.kryptojagd.level;

import org.kryptojagd.encryptionmethods.*;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;
import org.kryptojagd.level.tasks.Task;

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

	/**
	 * Gets decryption task.
	 *
	 * @return the decryption task
	 */
	public DecryptionTask getDecryptionTask() {
		return this.decryptionTask;
	}

	/**
	 * Gets encryption task.
	 *
	 * @return the encryption task
	 */
	public EncryptionTask getEncryptionTask() {
		return encryptionTask;
	}

	/**
	 * TODO: Now the removes the used task. Otherwise every time it provides the same question
	 * @author Sonja Kuklok, modified by Sven Strasser
	 *
	 * @return
	 */
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
		if (!this.multipleChoiceTasks.getFirst().proofAnswer(answer)) {
			return false;
		}
		this.multipleChoiceTasks.pop();
		return true;
	}

	/**
	 * Proof decryption task boolean.
	 *
	 * @param answer the answer
	 * @return the boolean
	 */
	public boolean proofDecryptionTask(String answer) {
		System.out.println(this.decryptionTask.proofAnswer(answer));
		return this.decryptionTask.proofAnswer(answer);
	}

	/**
	 * Prove which encryption method is given in JSON and initializes encryption method.
	 *
	 * @param encryptionMethod the encryption method
	 */
	public void proveEncryptionMethod(String encryptionMethod) {
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
		return this.timeInSec;
	}

	public void setTimeInSec(int given) {
		this.timeInSec = given;
	}

}



