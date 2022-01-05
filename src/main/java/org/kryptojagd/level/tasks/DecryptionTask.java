package org.kryptojagd.level.tasks;


import org.kryptojagd.encryptionmethods.Backwards;
import org.kryptojagd.encryptionmethods.Encryption;

/**
 * The class describes a task, where you have to decrypt text
 *
 * @author Sonja, Sven
 */
public class DecryptionTask implements Task {

	Backwards test = new Backwards();

	private Encryption encryption;

	private String textForDecryption;

	private String decryptedText;

	private String procedure;

	/**
	 * Creates a {@link DecryptionTask}
	 *
	 * @param encryption the encryption, in which you have to decrypt the text
	 */
	public DecryptionTask(Encryption encryption, String givenText) {
		this.encryption = encryption;
		this.textForDecryption = givenText;
		this.decryptedText = Encryption.decode()
	}

	@Override
	public boolean proofAnswer(String answer) {
		if (answer.equ)
	}

	//################# NEW ######################################



}