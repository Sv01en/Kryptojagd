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

	private boolean correctAnswerCityBool;

	private boolean isCityTaskShowing;

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
		this.correctAnswerCityBool = false;
		this.isCityTaskShowing = false;
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

	public void setCityShowing() {
		this.isCityTaskShowing = true;
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

	public boolean proofCityAnswer(int answer) {
		if (answer == correctAnswerCity) {
			this.correctAnswerCityBool = true;
			return true;
		}
		this.correctAnswerCityBool = false;
		return false;
	}

	public String getCityQuestion() {
		return "In welcher Stadt befindet sich die Floppy-Disk?";
	}

	public String[] getAnswerOptionsCity() {
		return answerOptionsCity;
	}

	public boolean getCorrectAnswerCity() {
		return correctAnswerCityBool;
	}

	public boolean isCityTaskShowing() {
		return isCityTaskShowing;
	}

	public String getCity() {
		return answerOptionsCity[correctAnswerCity];
	}
}