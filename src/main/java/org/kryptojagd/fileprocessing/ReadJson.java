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
  public static MultipleChoiceTask createMultipleChoiceTask(String path) {

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
      System.out.println(e.getUnexpectedObject());
      System.out.println(e.getPosition());
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Creates an EncryptionTask.
   *
   * @param path to the file
   * @return a generated task of type EncryptionTask
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
   * Creates a DecryptionTask.
   *
   * @param path to the file
   * @return a generated task of type DecryptionTask
   */
  public static DecryptionTask createDecryptionTask(String path) {
    DecryptionTask decryptionTask = null;
    try {
      Object obj2 = parser.parse(new FileReader(path));
      decryptionTask = gson.fromJson(obj2.toString(), DecryptionTask.class);
      decryptionTask.createMultipleChoiceTasks();
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

  /**
   * Reads the time for the level.
   *
   * @param path to the file
   * @return time in second for the level
   */
  public static int readTime(String path) {
    try {
      Object obj2 = parser.parse(new FileReader(path));
      JsonObject jsonObject = gson.fromJson(obj2.toString(), JsonObject.class);
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
