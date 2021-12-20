package org.kryptojagd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.kryptojagd.fileprocessing.ReadJSON;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;

import com.google.gson.Gson;

/**
 * The class runs the program.
 *
 * @author Leah Schlimm, Amelie Reichert, Bartosz Treyde, Sven Strasser, Michail Petermann, Sonja Kuklok
 */
public class Main {

    public static void main(String[] args) {
    	System.out.println("Starte Programm!");
    	MainApp.main(args);
    	
    	
    	
    	// ######################## TO DELETE (START) ########################
    	
//    	// Pfad zur Datei
//    	String path1 = "";
//    	String path2 = "";
//    	String path3 = "";
//    	
//    	MultipleChoiceTask m = ReadJSON.createMultiChoiceQuestion(path1);
//
//    	
//    	EncryptionTask e = ReadJSON.createEncryptionTask(path2);
//    	
//    	
//    	DecryptionTask d = ReadJSON.createDecryptionTask(path3);
    	
    	// ######################## TO DELETE (END) ########################
    }
}
