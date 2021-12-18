package org.kryptojagd.level;

import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;
import org.kryptojagd.level.tasks.Task;

import java.util.LinkedList;

/**
 * The class describes a level in the game
 *
 * @author Sonja
 */
public class Level {

	private DecryptionTask decryptionTask;
	private EncryptionTask encryptionTask;
	private LinkedList<MultipleChoiceTask> multipleChoiceTasks;

	/**
	 * Creates a {@link Level}
	 *
	 * @param decryptionTask first task in level is a decryptionTask
	 * @param encryptionTask second task in level is a encryptionTask
	 * @param multipleChoiceTasks third and last task is a LinkedList of multiple choice questions
	 */
	public Level(DecryptionTask decryptionTask, EncryptionTask encryptionTask, LinkedList<MultipleChoiceTask> multipleChoiceTasks) {
		this.decryptionTask = decryptionTask;
		this.encryptionTask = encryptionTask;
		this.multipleChoiceTasks = multipleChoiceTasks;
	}

	public DecryptionTask getDecryptionTask() {
		return decryptionTask;
	}

	public EncryptionTask getEncryptionTask() {
		return encryptionTask;
	}

	public MultipleChoiceTask getCurrentMultipleChoiceTask() {
		return multipleChoiceTasks.getFirst();
	}

	/**
	 * Proofs the answer of the current multiple choice task,
	 * if the answer is false, it returns the same question,
	 * if the answer is true, it returns the next question
	 * and removes the right answered question
	 *
	 * @param answer answer of the player to the multipleChoiceTask
	 * @return the same question, if the answer is false
	 * 			the next question, if the answer is true
	 */
	public String proofMultipleChoice(String answer) {
		if(!this.multipleChoiceTasks.getFirst().proofAnswer(answer)) {
			return this.multipleChoiceTasks.getFirst().getQuestion();
		}
		this.multipleChoiceTasks.pop();
		return this.multipleChoiceTasks.getFirst().getQuestion();
	}

}

