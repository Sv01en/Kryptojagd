package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;

import java.util.Arrays;

/**
 * The class describes a task, where you have to decrypt text
 *
 * @author Sonja, Sven
 */
public class DecryptionTask implements Task {

	private String plainText;

	private String encryptionMethod;

	private Encryption encryptionMethod1;

	private String[] answerOptionsEncryption;

	private int correctAnswerEncryption;

	private String[] answerOptionsCity;

	private int correctAnswerCity;

	private String textAfterStart;

	private int timeInSec;

	private boolean correctAnswer;

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
		this.correctAnswer = false;
	}

	@Override
	public boolean proofAnswer(String answer) {
		if (answer.equals(this.encryptionMethod)) {
			this.correctAnswer = true;
			return true;
		}
		this.correctAnswer = false;
		return false;
	}

	public String getPlainText() {
		return this.plainText;
	}

	public String[] getAnswerOptionsEncryption() {
		return this.answerOptionsEncryption;
	}

	public int getTimeInSec() {
		return this.timeInSec;
	}

	public boolean getCorrectAnswer() {
		return this.correctAnswer;
	}

	public void setEncryptionMethod(String encryptionMethod) {
		this.encryptionMethod = encryptionMethod;
	}
}