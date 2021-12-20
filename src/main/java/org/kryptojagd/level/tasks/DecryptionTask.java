package org.kryptojagd.level.tasks;


import org.kryptojagd.encryptionmethods.Encryption;

/**
 * The class describes a task, where you have to decrypt text
 *
 * @author Sonja
 */
public class DecryptionTask implements Task {

    // private Encryption encryption;
	
	private String task;
	private String text;
	private int timeInSec;
    

//    /**
//     * Creates a {@link DecryptionTask}
//     *
//     * @param encryption the encryption, in which you have to decrypt the text
//     */
//    public DecryptionTask(Encryption encryption) {
//        this.encryption = encryption;
//    }
	
	public DecryptionTask(String task, String text, int timeInSec) {
		this.task = task;
		this.text = text;
		this.timeInSec = timeInSec;
	}
    
   

    @Override
    public boolean proofAnswer(String answer) {
        return false;
    }

}