package org.kryptojagd.fileprocessing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import org.kryptojagd.level.DecryptionTask;
import org.kryptojagd.level.MultipleChoiceQuestion;

public class ReadJSON {
	
	// gibt Mul
	public static MultipleChoiceQuestion createMultiChoiceQuestion() {
		
		MultipleChoiceQuestion frage = null;
		
		try {
			JSONParser parser = new JSONParser();
			Object obj2 = parser.parse(new FileReader("/home/mp/eclipse-workspace/TestProjekt/src/test.json"));
			JSONObject jsonObject =  (JSONObject) obj2;
			HashMap<String, Object> yourHashMap = new Gson().fromJson(jsonObject.toString(), HashMap.class);
			frage = new MultipleChoiceQuestion(yourHashMap);
			
			
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
		return frage;
		
	}
	
	public static DecryptionTask createEncryptionTask() {
		
		DecryptionTask aufgabe = null;
		
		try {
			JSONParser parser = new JSONParser();
			Object obj2 = parser.parse(new FileReader("/home/mp/eclipse-workspace/TestProjekt/src/test.json"));
			JSONObject jsonObject =  (JSONObject) obj2;
			HashMap<String, Object> yourHashMap = new Gson().fromJson(jsonObject.toString(), HashMap.class);
			aufgabe = new DecryptionTask(yourHashMap);
			
			
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
		return aufgabe;
	}

}
