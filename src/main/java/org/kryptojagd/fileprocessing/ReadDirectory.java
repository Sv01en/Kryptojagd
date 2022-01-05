package org.kryptojagd.fileprocessing;

import java.io.File;
import java.util.*;

import org.kryptojagd.level.Level;
import org.kryptojagd.level.LevelComparator;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;

/**
 * This class can be used to read directories.
 *
 * @author Michail
 */
public class ReadDirectory {
	
	private final static String path = "src/main/resources/org/kryptojagd/levels";
	
	
	public static ArrayList<Level> initialize() throws Exception {
				
		ArrayList<Level> allLevels = new ArrayList<Level>();
		
		File levelsFolder = new File(path);
		File[] listOfFolders = levelsFolder.listFiles();
			
		for (File folder : listOfFolders) {
			
			if (folder.isDirectory() && folder.getName().substring(0, 5).equals("level")) {
				
				Level level = readLevelDirectory(folder.getAbsolutePath());
				
				System.out.println(folder.getName());
				int levelNum = Integer.parseInt(folder.getName().substring(5));
				System.out.println(levelNum);
				
				// 
				level.setId(levelNum);
				allLevels.add(level);
				//
				
			}
		}

		Collections.sort(allLevels, new LevelComparator());
		return allLevels;
		
	}

//ToDo proof, if decryptionTask and encryptionTask exists as a file
	/**
	 * Reads the files with the information for a level and creates it.
	 *
	 * @param path filepath to the files with the information for a level
	 * @return level, with the given information of the files
	 * @throws Exception if there is an unknown file
	 */
	private static Level readLevelDirectory(String path) throws Exception {

		LinkedList<MultipleChoiceTask> multipleChoiceTasks = new LinkedList<>();
		int timeInSec = 300;
		String pathToDecryptionTask = "";
		String pathToEncryptionTask = "";

		File levelFolder = new File(path);
		File[] listOfFiles = levelFolder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				String fileName = file.getName();
				String pathToFile = file.getAbsolutePath();
				
				if (fileName.contains("decryption")) {
					pathToDecryptionTask = pathToFile;

				} else if (fileName.contains("encryption")) {
					pathToEncryptionTask = pathToFile;
					
				} else if (fileName.contains("question")) {
					MultipleChoiceTask multipleChoiceTask = ReadJSON.createMultiChoiceQuestion(pathToFile);
					multipleChoiceTasks.add(multipleChoiceTask);
					
				} else if (fileName.contains("time")) {
					timeInSec = ReadJSON.readTime(pathToFile);
				} else {
					throw new Exception("Unbekannte Datei");
				}
			}
		}
		DecryptionTask decryptionTask = ReadJSON.createDecryptionTask(pathToDecryptionTask);
		EncryptionTask encryptionTask = ReadJSON.createEncryptionTask(pathToEncryptionTask);
		
		return new Level(decryptionTask, encryptionTask, multipleChoiceTasks, timeInSec);
		
	}

}
