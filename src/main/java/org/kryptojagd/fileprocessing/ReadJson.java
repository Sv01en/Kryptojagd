package org.kryptojagd.fileprocessing;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;

/**
 * This class can be used to read JSON files.
 *
 * @author Michail Petermann, Bartosz Treyde
 */
public class ReadJson {

  private static Gson gson = new Gson();
  private static JSONParser parser = new JSONParser();
  private static int time = 0;

  /**
   * Reads the files with the information for a MultipleChoiceTask and creates it.
   *
   * @param path filepath to the files with the information for a MultipleChoiceTask
   * @return MultipleChoiceTask, with the given information of the files
   */
  public static MultipleChoiceTask createMultipleChoiceTask(String jsonFile) {

	  MultipleChoiceTask multipleChoiceTask;

		  JsonObject convertedObject = new Gson().fromJson(jsonFile, JsonObject.class);
	      multipleChoiceTask = gson.fromJson(convertedObject, MultipleChoiceTask.class);
	      return multipleChoiceTask;

  }

  /**
   * Creates an EncryptionTask.
   *
   * @param path to the file
   * @return a generated task of type EncryptionTask
   */
  public static EncryptionTask createEncryptionTask(String jsonFile) {

	  EncryptionTask encryptionTask;

	  JsonObject convertedObject = new Gson().fromJson(jsonFile, JsonObject.class);
	  encryptionTask = gson.fromJson(convertedObject, EncryptionTask.class);
      return encryptionTask;

  }

  /**
   * Creates a DecryptionTask.
   *
   * @param path to the file
   * @return a generated task of type DecryptionTask
   */
  public static DecryptionTask createDecryptionTask(String jsonFile) {

	  DecryptionTask decryptionTask;

	  JsonObject convertedObject = new Gson().fromJson(jsonFile, JsonObject.class);
	  decryptionTask = gson.fromJson(convertedObject, DecryptionTask.class);
      decryptionTask.createCityTask();
	  return decryptionTask;

  }

  /**
   *
   * @param jsonFile
   * @return
   */
  public static int readTime(String jsonFile) {

	  JsonObject convertedObject = new Gson().fromJson(jsonFile, JsonObject.class);
	  time = Integer.parseInt(convertedObject.get("time").getAsString());
      return time;

  }
}
