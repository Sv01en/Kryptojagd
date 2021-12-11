package org.kryptojagd.level;

import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;
import org.kryptojagd.level.tasks.Task;

import java.util.LinkedList;

public class Level {

	private LinkedList<Task> level = new LinkedList<>();
	private Task currentTask = level.getFirst();
	
	public Level(DecryptionTask decryptionTask, EncryptionTask encryptionTask, MultipleChoiceTask multipleChoiceTask) {
		level.add(decryptionTask);
		level.add(encryptionTask);
		level.add(multipleChoiceTask);
	}



}
