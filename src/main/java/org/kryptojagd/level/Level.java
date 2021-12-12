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

	private LinkedList<Task> level = new LinkedList<>();
	private Task currentTask = level.getFirst();

	/**
	 * Creates a {@link Level}
	 *
	 * @param decryptionTask first task in level is a decryptionTask
	 * @param encryptionTask second task in level is a encryptionTask
	 * @param multipleChoiceTask third and last task is a Collection of multiple choice questions
	 */
	public Level(DecryptionTask decryptionTask, EncryptionTask encryptionTask, MultipleChoiceTask multipleChoiceTask) {
		level.add(decryptionTask);
		level.add(encryptionTask);
		level.add(multipleChoiceTask);
	}

	/**
	 * Gives the next Task in the chronology of the level
	 *
	 * @return the current task in level
	 */
	public Task giveNextTask() {
		currentTask = level.iterator().next();
		return currentTask;
	}

	/**
	 * Checks the answer to the current task
	 *
	 * @param answer the answer which is given
	 * @return trur, if the answer is correct
	 */
	public boolean proofCurrentTask(String answer) {
		return currentTask.proofAnswer(answer);
	}

}
