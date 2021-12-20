package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;

/**
 * The class describes a task, where you have to encrypt text
 *
 * @author Sonja
 */
public class EncryptionTask implements Task {

    // private Encryption encryption;
    
    private String plainText;
    private String encryptionMethod;
    private String[] answerOptionsEncryption;
    private int correctAnswerEncryption;
    private String[] answerOptionsCity;
    private int correctAnswerCity;
    private String textAfterStart;
    private int timeInSec;

//    /**
//     * Creates a {@link EncryptionTask}
//     *
//     * @param encryption the encryption, in which you have to encrypt the text
//     */
//    public EncryptionTask(Encryption encryption) {
//        this.encryption = encryption;
//    }
    
    public EncryptionTask(String plainText, String encryptionMethod, String[] answerOptionsEncryption,
    		int correctAnswerEncryption, String[] answerOptionsCity, int correctAnswerCity, 
    		String textAfterStart, int timeInSec) {
    	
    	this.plainText = plainText;
    	this.encryptionMethod = encryptionMethod;
    	this.answerOptionsEncryption = answerOptionsEncryption;
    	this.correctAnswerEncryption = correctAnswerEncryption;
    	this.answerOptionsCity = answerOptionsCity;
    	this.correctAnswerCity = correctAnswerCity;
    	this.textAfterStart = textAfterStart;
    	this.timeInSec = timeInSec;
    }

    @Override
    public boolean proofAnswer(String answer) {
        return false;
    }

}
