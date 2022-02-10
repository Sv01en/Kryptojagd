package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;

/**
 * The class describes a task, where you have to decrypt text
 *
 * @author Sonja Kuklok, Sven Strasser, Leah Schlimm, Bartosz Treyde
 */
public class DecryptionTask implements Task {

	private static final String FIRST_WORD = "Hallo, ";

	private String plainText;

	private String encryptionType;

	private Encryption encryptionMethod;

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
	private boolean taskCompleted = false;

	/**
	 * Contructor of a decryption task
	 *
	 * @param plainText               text that will be displayed encrypted
	 * @param answerOptionsEncryption answer options for decryption task
	 * @param correctAnswerEncryption correct answer of decryption task
	 * @param answerOptionsCity       answer options for city selector
	 * @param correctAnswerCity       correct city answer
	 * @param textAfterStart          text to display after city celector was done right
	 * @param timeInSec               time in sec for the task
	 * @param timePenalty             the time penalty
	 */
	public DecryptionTask(String plainText, String encryptionType, String[] answerOptionsEncryption,
						  int correctAnswerEncryption, String[] answerOptionsCity, int correctAnswerCity,
						  String textAfterStart, int timeInSec, int timePenalty) {
		this.plainText = plainText;
		this.encryptionType = encryptionType;
		this.answerOptionsEncryption = answerOptionsEncryption;
		this.correctAnswerEncryption = correctAnswerEncryption;
		this.answerOptionsCity = answerOptionsCity;
		this.correctAnswerCity = correctAnswerCity;
		this.textAfterStart = textAfterStart;
		this.timeInSec = timeInSec;
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
	 * Sets encryption method.
	 *
	 * @param encryptionMethod the encryption method
	 */
	public void setEncryptionMethod(Encryption encryptionMethod) {
		this.encryptionMethod = encryptionMethod;
	}

	@Override
	public boolean proveAnswer(String answer) {
		this.taskCompleted = answer.equals(this.encryptionType);
		return taskCompleted;
	}

	@Override
	public String[] getPossibilities() {
		return this.answerOptionsEncryption;
	}

	@Override
	public boolean getTaskCompleted() {
		return taskCompleted;
	}

	@Override
	public String toString(){
		return "DecryptionTask";
	}

	/**
	 * If the decryption task is finished, this has to be set to display the city task correctly
	 */
	public void setCityShowing(boolean isShowing) {
		this.isCityTaskShowing = isShowing;
	}

	/**
	 * Getter for the plain text
	 * @return plaintext
	 */
	public String getPlainText() {
		return FIRST_WORD + this.plainText;
	}


	/**
	 * Getter for time in sec
	 * @return left time in sec
	 */
	public int getTimeInSec() {
		return this.timeInSec;
	}

	/**
	 * Proof if the selected city is correct
	 * @param answer answer to proof
	 * @return true if the answer was correct, else false
	 */
	public boolean proofCityAnswer(String answer) {
		if (answer.equals(answerOptionsCity[correctAnswerCity])) {
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
	 * Getter for the number of the correct city in the Array in the Json file
	 * @return the number of the correct city in the Array in the Json file
	 */
	public int getCorrectAnswerCityInt() {
		return correctAnswerCity;
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

	/**
	 * Getter for text to display after the city question is answered correctly
	 * @return text to display
	 */
	public String getTextAfterStart() {
		return textAfterStart;
	}

	/**
	 * Clears the decryption task.
	 */
	public void clearDecryptionTask() {
		this.correctAnswer = false;
		this.correctAnswerCityBool = false;
		this.isCityTaskShowing = false;
	}

	/**
	 * Getter for the encryption method
	 * @return encryption method
	 */
	public String getEncryptionMethod() {
		return encryptionMethod.toString();
	}

	/**
	 * Getter for the number of the encryption method in the Array in the Json file
	 * @return the number of the encryption method in the Array in the Json file
	 */
	public int getCorrectAnswerEncryption() {
		return correctAnswerEncryption;
	}
}
