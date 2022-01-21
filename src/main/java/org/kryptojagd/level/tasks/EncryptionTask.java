package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;

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
        this.taskCompleted = answer.equals(this.encryptionMethod.encode(this.text, this.key));
        System.out.println(this.taskCompleted);
        System.out.println(this.encryptionMethod.encode(this.text, this.key));
        return answer.equals(this.encryptionMethod.encode(this.text, this.key));
    }

    /**
     * Returns if the task is complete
     *
     * @return true or false as boolean
     */
    public boolean getTaskCompleted() {
        return this.taskCompleted;
    }
}
