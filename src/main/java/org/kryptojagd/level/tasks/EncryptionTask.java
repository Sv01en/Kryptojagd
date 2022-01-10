package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;

/**
 * The class describes a task, where you have to encrypt text
 *
 * @author Sonja, Bartosz
 */
public class EncryptionTask implements Task {
    private String task;
    private String text;
    private String key;
    //private Encryption encryption;
    private Encryption encryptionType;

    /**
     * Creates a {@link EncryptionTask}
     *
     * @param encryption the encryption, in which you have to encrypt the text
     *
     */
    public EncryptionTask(String task, String text,Encryption encryptionType, String key){//} Encryption encryption) {

        this.encryptionType = encryptionType;
        this.task = task;
        this.text = text;
        this.key = key;

    }

    public String getTask() {
        return task;
    }

    public String getText() {
        return text;
    }

    public Encryption getEncryption(){
       return encryptionType;
    }
    public String getKey(){
        return key;
    }

    @Override
    public boolean proofAnswer(String answer) {
        return false;
    }

}
