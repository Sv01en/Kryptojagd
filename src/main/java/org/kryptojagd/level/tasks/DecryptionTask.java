package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;

import java.util.Arrays;

/**
 * The class describes a task, where you have to decrypt text
 *
 * @author Sonja, Sven, Leah
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

	private int timePenalty;

	private boolean correctAnswer;

	private boolean correctAnswerCityBool;

	private boolean isCityTaskShowing;

	/**
	 * Contructor of a decryption task
	 * @param plainText text that will be displayed encrypted
	 * @param encryptionMethod method to entcrypt plaintext with
	 * @param answerOptionsEncryption answer options for decryption task
	 * @param correctAnswerEncryption correct answer of decryption task
	 * @param answerOptionsCity answer options for city selector
	 * @param correctAnswerCity correct city answer
	 * @param textAfterStart text to display after city celector was done right
	 * @param timeInSec time in sec for the task
	 */
	public DecryptionTask(String plainText, String encryptionMethod, String[] answerOptionsEncryption,
						  int correctAnswerEncryption, String[] answerOptionsCity, int correctAnswerCity,
						  String textAfterStart, int timeInSec, int timePenalty) {
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
		this.timePenalty = timePenalty;
	}

	/**
	 * Returns the time penalty as an integer
	 * @return time as an integer
	 */
	public int getTimePenalty() {
		return this.timePenalty;
	}

	/**
	 * Proofs the answer of decryption task
	 * @param answer to proof
	 * @return true if the answer is correct, else false
	 */
	@Override
	public boolean proofAnswer(String answer) {
		if (answer.equals(this.encryptionMethod)) {
			this.correctAnswer = true;
			return true;
		}
		this.correctAnswer = false;
		return false;
	}

	/**
	 * If the decryption task is finished, this has to be set to display the city task correctly
	 */
	public void setCityShowing() {
		this.isCityTaskShowing = true;
	}

	/**
	 * Getter for the plain text
	 * @return plaintext
	 */
	public String getPlainText() {
		return this.plainText;
	}

	/**
	 * Getter for answer options of decryption task
	 * @return answer options
	 */
	public String[] getAnswerOptionsEncryption() {
		return this.answerOptionsEncryption;
	}

	/**
	 * Getter for time in sec
	 * @return left time in sec
	 */
	public int getTimeInSec() {
		return this.timeInSec;
	}

	/**
	 * Getter, if the given answer was correct
	 * @return true if it was, else false
	 */
	public boolean getCorrectAnswer() {
		return this.correctAnswer;
	}

	/**
	 * Setter for the encryption method
	 * @param encryptionMethod method to set
	 */
	public void setEncryptionMethod(String encryptionMethod) {
		this.encryptionMethod = encryptionMethod;
	}

	/**
	 * Proof if the selected city is correct
	 * @param answer answer to proof
	 * @return true if the answer was correct, else false
	 */
	public boolean proofCityAnswer(int answer) {
		if (answer == correctAnswerCity) {
			this.correctAnswerCityBool = true;
			return true;
		}
		this.correctAnswerCityBool = false;
		return false;
	}

	/**
	 * Getter for the city question
	 * @return question
	 */
	public String getCityQuestion() {
		return "In welcher Stadt befindet sich die Floppy-Disk?";
	}

	/**
	 * Getter for city answer options
	 * @return answer options
	 */
	public String[] getAnswerOptionsCity() {
		return answerOptionsCity;
	}

	/**
	 * Getter for the given answer was correct
	 * @return true if it was, else false
	 */
	public boolean getCorrectAnswerCity() {
		return correctAnswerCityBool;
	}

	/**
	 * Getter, if the city task or the decryption task is currently showing
	 * @return true, if the city task will be shown, else false
	 */
	public boolean isCityTaskShowing() {
		return isCityTaskShowing;
	}

	/**
	 * Getter for the correct city name
	 * @return name of correct city
	 */
	public String getCity() {
		return answerOptionsCity[correctAnswerCity];
	}

	public void clearDecryptionTask() {
		this.correctAnswer = false;
		this.correctAnswerCityBool = false;
		this.isCityTaskShowing = false;
	}
}