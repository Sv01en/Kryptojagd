package org.kryptojagd.level.tasks;


import org.kryptojagd.encryptionmethods.Encryption;

import java.util.ArrayList;

/**
 * The class describes a task, where you have to decrypt text
 *
 * @author Sonja, Sven
 */
public class DecryptionTask implements Task {

	private Encryption encryption;

	private String textForDecryption;

	private String decryptedText;

	private ArrayList<String> possibleSolutions;

	private String correctAnswer;

	public DecryptionTask(String method, String givenText, ArrayList<String> possibleSolutions, String correctAnswer) {
		this.textForDecryption = givenText;
		this.decryptedText = this.encryption.decode(givenText, "Test");
		this.possibleSolutions = possibleSolutions;
		this.correctAnswer = correctAnswer;
	}

	@Override
	public boolean proofAnswer(String answer) {
		if (answer.equals(correctAnswer)) {
			return true;
		}
		return false;
	}

	public ArrayList<String> getPossibleSolutions() {
		return this.possibleSolutions;
	}

	public String getDecryptedText() {
		return this.decryptedText;
	}

	public String getTextForDecryption() {
		return this.textForDecryption;
	}

	public String getCorrectAnswer() {
		return this.correctAnswer;
	}
}