package org.kryptojagd.fileprocessing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;

/**
 * This class can be used to read JSON files.
 *
 * @author Michail
 */
public class ReadJSON {

	private static Gson gson = new Gson();
	private static JSONParser parser = new JSONParser();
	
	/**
	 * Reads the files with the information for a MultipleChoiceTask and creates it.
	 *
	 * @param path filepath to the files with the information for a MultipleChoiceTask
	 * @return MultipleChoiceTask, with the given information of the files
	 */
	public static MultipleChoiceTask createMultiChoiceQuestion(String path) {
		
		MultipleChoiceTask multipleChoiceTask;
		try {
			Object obj2 = parser.parse(new FileReader(path));
			multipleChoiceTask = gson.fromJson(obj2.toString(), MultipleChoiceTask.class);
			return multipleChoiceTask;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Datei wurde nicht gefunden!");
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static EncryptionTask createEncryptionTask(String path) {
		EncryptionTask encryptionTask;
		try {
			Object obj2 = parser.parse(new FileReader(path));
			encryptionTask = gson.fromJson(obj2.toString(), EncryptionTask.class);
			return encryptionTask;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Datei wurde nicht gefunden!");
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public static DecryptionTask createDecryptionTask(String path) {
		DecryptionTask decryptionTask = null;
			try {
				Object obj2 = parser.parse(new FileReader(path));
				decryptionTask = gson.fromJson(obj2.toString(), DecryptionTask.class);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Decryption Datei wurde nicht gefunden!");
				// e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return decryptionTask;
	}
	
	public static int readTime(String path) {
		int time = 0;
		try {
			Object obj2 = parser.parse(new FileReader(path));
			JsonObject jsonObject = gson.fromJson( obj2.toString(), JsonObject.class);
			time = Integer.parseInt(jsonObject.get("time").getAsString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}

}
