package org.kryptojagd.fileprocessing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.LevelComparator;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;

/**
 * This class is used to read directories.
 *
 * @author Michail Petermann, Bartosz Treyde
 */
public class ReadDirectory {

  private static final String PREFIX = "/org/kryptojagd/levels/";
  private static final String LEVEL = "level";
  private static final String[] TASKS = {"encryption", "decryption", "question"};
	/**
	 * The constant CSS_FILES.
	 */
	public static final String CSS_FILES = "/org/kryptojagd/css/";
	/**
	 * The constant CSS_FILE_START.
	 */
	public static final String CSS_FILE_START = CSS_FILES + "startwindow.css";
  
  /**
   * This method initializes all levels.
   *
   * @return array list of initialized levels
   * @throws Exception if unknown file
   */
  public static ArrayList<Level> initialize() throws Exception {

    ArrayList<Level> allLevels = new ArrayList<Level>();
    
    boolean[] check = {true, true, true};
    
    //TODO Exception, wenn Decryption, Encryption oder MultipleChoice gleich null
    
    for (int i = 1; ; i++) {
    	
    	DecryptionTask decryptionTask = null;
    	EncryptionTask encryptionTask = null;
    	LinkedList<MultipleChoiceTask> multipleChoiceTasks = new LinkedList<MultipleChoiceTask>(); 
    	
    	for (String t : TASKS) {
    		
    		if (t.equals("question")) {
    			
    			for (int k = 1; k < 6; k++) {
        			String p = PREFIX + LEVEL + i + "/" + t + k + ".json";
        			try {
        				InputStream configStream = ReadDirectory.class.getResourceAsStream(p);
        			    BufferedReader configReader = new BufferedReader(new InputStreamReader(configStream, "UTF-8"));
        			    
        			    String content = "";
        			    String line = configReader.readLine();
        			  	while (line != null) {
        			  		content += line + "\n";
        			  		line = configReader.readLine();
        			  	}
	    			  	if (t.contains("question")) {
	    			  		multipleChoiceTasks.add(ReadJson.createMultipleChoiceTask(content));
	    				} 
        			    
        			} catch (Exception e) {
        				if (k == 1) {
        					check[2] = false;
        				}
        				break;
        			}
        		}
    			
    		} else {
    			String p = PREFIX + LEVEL + i + "/" + t + ".json";
    			try {
    				InputStream configStream = ReadDirectory.class.getResourceAsStream(p);
    			    BufferedReader configReader = new BufferedReader(new InputStreamReader(configStream, "UTF-8"));
    			    
    			    String content = "";
    			    String line = configReader.readLine();
    			  	while (line != null) {
    			  		content += line + "\n";
    			  		line = configReader.readLine();
    			  	}
    			  	if (t.contains("decryption")) {
    					decryptionTask = ReadJson.createDecryptionTask(content);
    				} else if (t.contains("encryption")) {
    					encryptionTask = ReadJson.createEncryptionTask(content);
    				}
    			    
    			} catch (Exception e) {
    				if (t.equals("encryption")) {
    					 check[0] = false;
    				} else {
    					check[1] = false;
    				}
    			}
    		}
    	}
    	
    	// Wenn die Pfade für Decryption, Encryption und MultipleChoiceQuestion für ein komplettes Level 
    	// ungültig sind, wird das Einlesen abgebrochen.
    	
    	if (!check[0] && !check[1] && !check[2]) {
    		System.out.println("Ordner für Level " + i + " existiert nicht. Einlesen der Level nach Level " + (i - 1)
					+ " abgebrochen.");
    		break;
    	} else {
    		Level l = new Level(decryptionTask, encryptionTask, multipleChoiceTasks, 600);
			l.setId(i - 1);
    		allLevels.add(l);
    		check[0] = true;
    		check[1] = true;
    		check[2] = true;
    	}
    }

    Collections.sort(allLevels, new LevelComparator());
    return allLevels;

  }
}
