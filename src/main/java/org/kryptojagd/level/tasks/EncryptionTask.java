package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;
import org.kryptojagd.level.hamming.HammingDistance;

/**
 * The class describes a task, where you have to encrypt the given text
 *
 * @author Sonja Kuklok, Sven Strasser, Amelie Reichert, Bartosz Treyde
 */
public class EncryptionTask implements Task {

    private String task;

    private String text;

    private String key;

    private Encryption encryptionMethod;

    private String encryptionType;

    private boolean taskCompleted;

    private HammingDistance hammingDistance = new HammingDistance();

    private int hammingDistanceValue;
    private String taskName = "EncryptionTask";

    /**
     * Creates a {@link EncryptionTask}
     *
     * @param task             the task
     * @param text             the text to be encrypted
     * @param encryptionType   the encryption method as a String
     * @param key              the key
     * @param encryptionMethod the encryption method, in which you have to encrypt the text
     */
    public EncryptionTask(String task, String text, String encryptionType, String key, Encryption encryptionMethod) {
        this.encryptionType = encryptionType;
        this.task = task;
        this.text = text;
        this.key = key;
        this.encryptionMethod = encryptionMethod;
        this.taskCompleted = false;
    }

    /**
     * Gets task.
     *
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Get encryption as a String.
     *
     * @return the string
     */
    public String getEncryption() {
        return encryptionType;
    }

    /**
     * Get key as a String.
     *
     * @return the string
     */
    public String getKey() {
        return key;
    }


    /**
     * Sets encryption method.
     *
     * @param encryptionMethod the encryption method
     */
    public void setEncryptionMethod(Encryption encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
    }

    /**
     * Gets encryption method.
     *
     * @return the encryption method
     */
    public Encryption getEncryptionMethod() {
        return encryptionMethod;
    }

    @Override
    public boolean proveAnswer(String answer) {
        String checkAnswer = answer.toUpperCase();
        this.taskCompleted = checkAnswer.equals(this.encryptionMethod.encode(this.text, this.key));
        this.hammingDistanceValue = hammingDistance.calculateHammingDistance(
                this.encryptionMethod.encode(this.text, this.key), checkAnswer);
        return checkAnswer.equals(this.encryptionMethod.encode(this.text, this.key));
    }

    //ToDo default
    @Override
    public String[] getPossibilities() {
        return new String[0];
    }

    @Override
    public String toString() {
        return taskName;
    }

    /**
     * Returns if the task is complete
     *
     * @return true or false as boolean
     */
    public boolean getTaskCompleted() {
        return this.taskCompleted;
    }

    /**
     * Returns the calculated hamming distance.
     * @return hamming distance as an integer
     */
    public int getHammingDistanceValue() {
        return this.hammingDistanceValue;
    }
}
