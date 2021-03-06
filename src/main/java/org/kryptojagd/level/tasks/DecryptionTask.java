package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;
import org.kryptojagd.level.PointSystem;

/**
 * The class describes a task, where you have to decrypt text
 *
 * @author Sonja Kuklok, Sven Strasser, Leah Schlimm, Bartosz Treyde, Amelie Reichert
 */
public class DecryptionTask implements Task {

	private static final String FIRST_WORD = "Hallo, ";
	private static final int LAST_WORDS_NUMBER = 3;

	private MultipleChoiceTask cityTask;
	private final String plainText;
	private String helpText;
	private final String encryptionType;
	private Encryption encryptionMethod;
	private final String[] answerOptionsEncryption;
	private final int correctAnswerEncryption;
	private final String[] answerOptionsCity;
	private final int correctAnswerCity;
	private final String textAfterStart;
	private final int timeInSec;
	private final int timePenalty;
	private boolean taskCompleted = false;
	private String name = "DecryptionTask";
	private final int pointsDecryptionTask = 50;
	private final int pointsEncryptionType = 10;

	/**
	 * Contructor of a decryption task
	 *
	 * @param plainText               text that will be displayed encrypted
	 * @param encryptionType          encryption type for the decryption
	 * @param answerOptionsEncryption answer options for decryption task
	 * @param correctAnswerEncryption correct answer of decryption task
	 * @param answerOptionsCity       answer options for city selector
	 * @param correctAnswerCity       correct city answer
	 * @param textAfterStart          text to display after city selector was done right
	 * @param timeInSec               time in sec for the task
	 * @param timePenalty             the time penalty
	 * @param helpText                the help text
	 */
	public DecryptionTask(String plainText, String encryptionType, String[] answerOptionsEncryption,
						  int correctAnswerEncryption, String[] answerOptionsCity, int correctAnswerCity,
						  String textAfterStart, int timeInSec, int timePenalty, String helpText) {
		this.plainText = plainText;
		this.encryptionType = encryptionType;
		this.cityTask = new MultipleChoiceTask(plainText, answerOptionsCity,
			answerOptionsCity[correctAnswerCity], helpText);
		cityTask.setName("cityTask");
		this.answerOptionsEncryption = answerOptionsEncryption;
		this.correctAnswerEncryption = correctAnswerEncryption;
		this.answerOptionsCity = answerOptionsCity;
		this.correctAnswerCity = correctAnswerCity;
		this.textAfterStart = textAfterStart;
		this.timeInSec = timeInSec;
		this.timePenalty = timePenalty;
		this.helpText = helpText;
	}


	/**
	 * Getter for the plain text
	 * @return plaintext with the first word appending
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
	 * Getter for text to display after the city question is answered correctly
	 * @return text to display
	 */
	public String getTextAfterCityTask() {
		return textAfterStart;
	}

	/**
	 * Getter for the encryption method
	 * @return encryption method
	 */
	public Encryption getEncryptionMethod() {
		return encryptionMethod;
	}

	/**
	 * Getter for the number of the encryption method in the Array in the Json file
	 * @return the number of the encryption method in the Array in the Json file
	 */
	public int getCorrectAnswerEncryption() {
		return correctAnswerEncryption;
	}

	/**
	 * Creates the cityTask as a multiple choice task with the given parameter in the class
	 *
	 * this method is for using, if the constructor is not used
	 */
	public void createCityTask() {
		this.cityTask = new MultipleChoiceTask(plainText, answerOptionsCity,
			answerOptionsCity[correctAnswerCity], helpText);
		cityTask.setName("cityTask");
	}


	/**
	 * Returns the time penalty as an integer
	 * @return time as an integer
	 */
	public int getTimePenalty() {
		return this.timePenalty;
	}

	/**
	 * Getter for the cityTask
	 * @return cityTask
	 */
	public MultipleChoiceTask getCityTask() {
		return cityTask;
	}

	/**
	 * Sets encryption method.
	 *
	 * @param encryptionMethod the encryption method
	 */
	public void setEncryptionMethod(Encryption encryptionMethod) {
		this.encryptionMethod = encryptionMethod;
	}

	/**
	 * Proves if the given encryption type is the used encryption of the class
	 *
	 * It also sets the boolean, if the right encryption is chosen (choseEncryption)
	 * @param answer given answer, which is proved
	 * @return true, if the answer is right
	 */
	public boolean proveEncryptionType(String answer) {
		boolean choseEncryption = answer.equals(this.encryptionType);
		if (choseEncryption && PointSystem.getDecryptionTaskFinished() < 1) {
			POINT_SYSTEM.setScore(PointSystem.getScore() + pointsEncryptionType);
			PointSystem.setDecryptionTaskFinished(1);
		}
		return choseEncryption;
	}

	/**
	 * Gives the last words of the plaintext
	 *
	 * @param number the index of the first word
	 * @return the last 3 words of the plaintext
	 */
	private String lastWordsOfPlaintext(int number) {
		String[] tokens = plainText.split(" ");
		StringBuilder decrypted = new StringBuilder();
		for (int i = tokens.length - number; i < tokens.length; i++) {
			if (i < tokens.length - 1) {
				decrypted.append(tokens[i]).append(" ");
			} else {
				decrypted.append(tokens[i]).deleteCharAt(decrypted.length() - 1);
			}
		}
		return decrypted.toString();
	}

	/**
	 * Proves the answer to the task
	 *
	 * if the encryption type is ceasar or backwards, it proves the decrypted last 3 words
	 * if the encryption type is vigenere or beaufort, it proves the right key
	 * @param answer the answer
	 * @return true, if the task is right answered
	 */
	@Override
	public boolean proveAnswer(String answer) {
		String decrypted = lastWordsOfPlaintext(LAST_WORDS_NUMBER);
		this.taskCompleted = answer.toUpperCase().equals(decrypted.toUpperCase());
		if (taskCompleted && PointSystem.getDecryptionTaskFinished() < 2) {
			POINT_SYSTEM.setScore(PointSystem.getScore() + pointsDecryptionTask);
			PointSystem.setDecryptionTaskFinished(1);
		}
		return taskCompleted;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getHelpText() {
		return helpText;
	}

	@Override
	public void setHelpText(String newHelpText) {
		this.helpText = newHelpText;
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
	public void setTaskCompletedEnd() {

	}

	/**
	 * Set score.
	 *@param score given as an integer
	 */
	public void setScore(int score) {
		POINT_SYSTEM.setScore(score);
	}
	/**
	 * Gets score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return PointSystem.getScore();
	}

	@Override
	public String toString() {
		return "DecryptionTask";
	}

	/**
	 * Gets text after start.
	 *
	 * @return the text after start
	 */
	public String getTextAfterStart() {
		return textAfterStart;
	}

	/**
	 * Get answer options encryption string [ ].
	 *
	 * @return the string [ ]
	 */
	public String[] getAnswerOptionsEncryption() {
		return answerOptionsEncryption;
	}
}
