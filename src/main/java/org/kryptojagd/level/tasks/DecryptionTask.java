package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;

/**
 * The class describes a task, where you have to decrypt text
 *
 * @author Sonja Kuklok, Sven Strasser, Leah Schlimm, Bartosz Treyde
 */
public class DecryptionTask implements Task {

	private static final String FIRST_WORD = "Hallo, ";

	private MultipleChoiceTask cityTask;
	private boolean choseEncryption = false;
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
	private boolean taskCompleted = false;
	private String name = "DecryptionTask";
	private String key;

	/**
	 * Contructor of a decryption task
	 *
	 * @param plainText               text that will be displayed encrypted
	 * @param answerOptionsEncryption answer options for decryption task
	 * @param correctAnswerEncryption correct answer of decryption task
	 * @param answerOptionsCity       answer options for city selector
	 * @param correctAnswerCity       correct city answer
	 * @param textAfterStart          text to display after city selector was done right
	 * @param timeInSec               time in sec for the task
	 * @param timePenalty             the time penalty
	 */
	public DecryptionTask(String plainText, String encryptionType, String[] answerOptionsEncryption,
						  int correctAnswerEncryption, String[] answerOptionsCity, int correctAnswerCity,
						  String textAfterStart, int timeInSec, int timePenalty) {
		this.plainText = plainText;
		this.encryptionType = encryptionType;
		this.cityTask = new MultipleChoiceTask(plainText, answerOptionsCity, answerOptionsCity[correctAnswerCity]);
		cityTask.setName("cityTask");
		this.answerOptionsEncryption = answerOptionsEncryption;
		this.correctAnswerEncryption = correctAnswerEncryption;
		this.answerOptionsCity = answerOptionsCity;
		this.correctAnswerCity = correctAnswerCity;
		this.textAfterStart = textAfterStart;
		this.timeInSec = timeInSec;
		this.timePenalty = timePenalty;
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

	/**
	 * Creates the cityTask as a multiple choice task with the given parameter in the class
	 *
	 * this method is for using, if the constructor is not used
	 */
	public void createCityTask() {
		this.cityTask = new MultipleChoiceTask(plainText, answerOptionsCity, answerOptionsCity[correctAnswerCity]);
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
		this.choseEncryption = answer.equals(this.encryptionType);
		return choseEncryption;
	}

	/**
	 * Proves if the given text is the last 3 words of the plaintext
	 *
	 * It also sets the boolean, if the task is completed (taskCompleted)
	 * @param answer given answer, which is proved
	 * @return true, if the answer is right
	 */
	private boolean proveDecryptedText(String answer) {
		String[] tokens = plainText.split(" ");
		StringBuilder decrypted = new StringBuilder();
		for (int i = tokens.length - 3; i < tokens.length; i++) {
			if (i < tokens.length - 1) {
				decrypted.append(tokens[i]).append(" ");
			} else {
				decrypted.append(tokens[i]).deleteCharAt(decrypted.length() - 1);
			}
		}
		this.taskCompleted = answer.equals(decrypted.toString());
		return taskCompleted;
	}

	/**
	 * Proves if the given String is the key, which was used to encrypt the plaintext
	 *
	 * It also sets the boolean, if the task is completed (taskCompleted)
	 * @param answer given answer, which is proved
	 * @return true, if the answer is right
	 */
	private boolean proveKey(String answer) {
		this.taskCompleted = key.equals(answer);
		return taskCompleted;
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
		if(encryptionType.startsWith("Cäsar") || encryptionType.startsWith("Rückwärts")) {
			return proveDecryptedText(answer);
		} else if (encryptionType.startsWith("Vigenère") || encryptionType.startsWith("Beaufort")) {
			return proveKey(answer);
		}
		return false;
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

}
