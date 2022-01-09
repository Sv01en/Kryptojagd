package org.kryptojagd.level.tasks;

import java.util.Arrays;

/**
 * The class describes a task, where you have to decrypt text
 *
 * @author Sonja, Sven
 */
public class DecryptionTask implements Task {

	private String plainText;

	private String encryptionMethod;

	private String[] answerOptionsEncryption;

	private int correctAnswerEncryption;

	private String[] answerOptionsCity;

	private int correctAnswerCity;

	private String textAfterStart;

	private int timeInSec;

	public DecryptionTask(String plainText, String encryptionMethod, String[] answerOptionsEncryption,
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
		if (answer.equals(this.encryptionMethod)) {
			return true;
		}
		return false;
	}

	public String getPlainText() {
		return this.plainText;
	}

	public String getEncryptionMethod() {
		return this.encryptionMethod;
	}

	public String[] getAnswerOptionsEncryption() {
		return this.answerOptionsEncryption;
	}

	public int getCorrectAnswerEncryption() {
		return this.correctAnswerEncryption;
	}

	public String[] getAnswerOptionsCity() {
		return this.answerOptionsCity;
	}

	public int getCorrectAnswerCity() {
		return this.correctAnswerCity;
	}

	public String getTextAfterStart() {
		return this.textAfterStart;
	}

	public int getTimeInSec() {
		return this.timeInSec;
	}
}