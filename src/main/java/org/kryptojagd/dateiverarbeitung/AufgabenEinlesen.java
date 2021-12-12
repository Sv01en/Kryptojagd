package org.kryptojagd.dateiverarbeitung;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class AufgabenEinlesen {
	
	// gibt Mul
	public static MultipleChoiceFrage erstelleMultiChoiceFrage() {
		
		MultipleChoiceFrage frage = null;
		
		try {
			JSONParser parser = new JSONParser();
			Object obj2 = parser.parse(new FileReader("/home/mp/eclipse-workspace/TestProjekt/src/test.json"));
			JSONObject jsonObject =  (JSONObject) obj2;
			HashMap<String, Object> yourHashMap = new Gson().fromJson(jsonObject.toString(), HashMap.class);
			frage = new MultipleChoiceFrage(yourHashMap);
			
			
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
	
	public static AufgabeVerschluesselung erstelleAufgabeVerschluesselung() {
		
		AufgabeVerschluesselung aufgabe = null;
		
		try {
			JSONParser parser = new JSONParser();
			Object obj2 = parser.parse(new FileReader("/home/mp/eclipse-workspace/TestProjekt/src/test.json"));
			JSONObject jsonObject =  (JSONObject) obj2;
			HashMap<String, Object> yourHashMap = new Gson().fromJson(jsonObject.toString(), HashMap.class);
			aufgabe = new AufgabeVerschluesselung(yourHashMap);
			
			
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
