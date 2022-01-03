package org.kryptojagd.level;

import org.kryptojagd.fileprocessing.ReadJSON;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Starts the game, initialize the required levels and restart the levels according to the rules if necessary.
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class LevelHandler {

    private Level level;
    private Level restartLevel;

    private List<Level> levels = new ArrayList<>();

    /**
     *
     */
    private void initializeGame() throws IOException {
       //TODO: read all files in the folder, maybe one folder for all levels is better.....
    }

    /**
     *
     * @return
     */
    public Level initializeLevel() {
        return new Level();
    }

    /**
     *
     * @return
     */
    private DecryptionTask initializeDecryptionTask() {
        String path = "Hello";//TODO: declare concrete path
        DecryptionTask decryptionTask = ReadJSON.createDecryptionTask(path);
        return decryptionTask;
    }

    /**
     *
     * @return
     */
    private EncryptionTask initializeEncryptionTask() {
        String path = "Hello";//TODO: declare concrete path
        EncryptionTask encryptionTask = ReadJSON.createEncryptionTask(path);
        return encryptionTask;
    }

    /**
     *
     * @return
     */
    private LinkedList initializeMultiplechoiceTask() {
        String path = "Hello";//TODO: declare concrete path
        LinkedList<String> output = new LinkedList<>();
        MultipleChoiceTask multipleChoiceTask = ReadJSON.createMultiChoiceQuestion(path);
        return output;
    }

    /**
     *
     */
    private void restartLevel() {
        this.level = this.restartLevel;
    }

    /**
     *
     */
    private void nextLevel() {
        this.level = initializeLevel();
    }

    /**
     *
     */
    public Level getLevel() {
        return this.level;
    }

}
