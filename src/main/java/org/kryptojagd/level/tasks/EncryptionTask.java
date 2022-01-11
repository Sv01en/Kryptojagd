package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;

/**
 * The class describes a task, where you have to encrypt text
 *
 * @author Sonja, Amelie, Bartosz
 */
public class EncryptionTask implements Task {
    private String task;
    private String text;
    private String key;
    private Encryption encryptionMethod;
    private String encryptionType;

    /**
     * Creates a {@link EncryptionTask}
     *
     * @param encryptionMethod the encryption, in which you have to encrypt the text
     *
     */
    public EncryptionTask(String task, String text,String encryptionType, String key, Encryption encryptionMethod){//} Encryption encryption) {

        this.encryptionType = encryptionType;
        this.task = task;
        this.text = text;
        this.key = key;
        this.encryptionMethod = encryptionMethod;

    }

    public String getTask() {
        return task;
    }

    public String getText() {
        return text;
    }

    public String getEncryption(){
        return encryptionType;
    }
    public String getKey(){
        return key;
    }


    public void setEncryptionMethod(Encryption encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
    }

    public Encryption getEncryptionMethod() {
        return encryptionMethod;
    }

    @Override
    public boolean proofAnswer(String answer) {
        return false;
    }

}
