package org.kryptojagd.fileprocessing;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.kryptojagd.level.Level;
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
	
	
	public static HashMap<Integer, Level> initialize() {
				
		HashMap<Integer, Level> map = new HashMap<Integer, Level>();
		
		File levelsFolder = new File(path);
		File[] listOfFolders = levelsFolder.listFiles();
			
		for (File folder : listOfFolders) {
			
			if (folder.isDirectory() && folder.getName().substring(0, 5).equals("level")) {
				
				Level level = readLevelDirectory(folder.getAbsolutePath());
				
				System.out.println(folder.getName());
				int levelNum = Integer.parseInt(folder.getName().substring(5));
				System.out.println(levelNum);
				
				map.put(levelNum, level);
				
			}
		}

		return map;
		
	}
	
	private static Level readLevelDirectory(String path) {
		
		Level level = new Level();
		
		File levelFolder = new File(path);
		File[] listOfFiles = levelFolder.listFiles();
		
		for (File file : listOfFiles) {
			if (file.isFile()) {
				String fileName = file.getName();
				String pathToFile = file.getAbsolutePath();
				
				if (fileName.contains("decryption")) {
					DecryptionTask d = ReadJSON.createDecryptionTask(pathToFile);
					level.addToTaskList(d);
					
				} else if (fileName.contains("encryption")) {
					EncryptionTask e = ReadJSON.createEncryptionTask(pathToFile);
					level.addToTaskList(e);
					
				} else if (fileName.contains("question")) {
					MultipleChoiceTask m = ReadJSON.createMultiChoiceQuestion(pathToFile);
					level.addToTaskList(m);
					
				} else {
					System.out.println("Unbekannte Datei");
				}
			}
		}
		
		return level;
		
	}

}
