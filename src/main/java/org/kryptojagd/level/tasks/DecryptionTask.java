package org.kryptojagd.level.tasks;


import org.kryptojagd.encryptionmethods.Encryption;

/**
 * The class describes a task, where you have to decrypt text
 *
 * @author Sonja, Sven
 */
public class DecryptionTask implements Task {

	/**
	<prev>JUST FOR WORKING</prev>
	 */
	private String testKey = "HalloWelt";

	private String key;

	private Encryption encryption;

	private String textForDecryption;

	private String decryptedText;

	private String procedure;

	/**
	 * Creates a {@link DecryptionTask}
	 *
	 * @param encryption the encryption, in which you have to decrypt the text
	 */
	public DecryptionTask(Encryption encryption, String givenText, String key) {
		this.key = key;
		this.encryption = encryption;
		this.textForDecryption = givenText;
		this.decryptedText = this.encryption.decode(givenText, testKey);
	}

	@Override
	public boolean proofAnswer(String answer) {
		return false;
	}

	//################# NEW ######################################



}