package org.kryptojagd.fileprocessing;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.parser.JSONParser;
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
   * @param jsonFile filepath to the files with the information for a MultipleChoiceTask
   * @return MultipleChoiceTask, with the given information of the files
   */
  public static MultipleChoiceTask createMultipleChoiceTask(String jsonFile) {

	  MultipleChoiceTask multipleChoiceTask;
      try {

          JsonObject convertedObject = new Gson().fromJson(jsonFile, JsonObject.class);
          multipleChoiceTask = gson.fromJson(convertedObject, MultipleChoiceTask.class);
          return multipleChoiceTask;
      } catch (Exception e) {
      }
      return null;
  }

  /**
   * Creates an EncryptionTask.
   *
   * @param jsonFile  filepath to the file  with the information for a EncryptionTask
   * @return a generated task of type EncryptionTask
   */
  public static EncryptionTask createEncryptionTask(String jsonFile) {

	  EncryptionTask encryptionTask;
try {
    JsonObject convertedObject = new Gson().fromJson(jsonFile, JsonObject.class);
    encryptionTask = gson.fromJson(convertedObject, EncryptionTask.class);
    return encryptionTask;
} catch (Exception e) {
    return null;
}
  }

  /**
   * Creates a DecryptionTask.
   *
   * @param jsonFile  filepath to the file  with the information for a DecryptionTask
   * @return a generated task of type DecryptionTask
   */
  public static DecryptionTask createDecryptionTask(String jsonFile) {

	  DecryptionTask decryptionTask;
try {

    JsonObject convertedObject = new Gson().fromJson(jsonFile, JsonObject.class);
    decryptionTask = gson.fromJson(convertedObject, DecryptionTask.class);
    decryptionTask.createCityTask();
    return decryptionTask;
} catch (Exception e) {
    return null;
}

  }

  /**
   *
   * @param jsonFile
   * @return time
   */
  public static int readTime(String jsonFile) {
try {
    JsonObject convertedObject = new Gson().fromJson(jsonFile, JsonObject.class);
    time = Integer.parseInt(convertedObject.get("time").getAsString());
    return time;
} catch (Exception e) {
    return 0;
}
  }
}
