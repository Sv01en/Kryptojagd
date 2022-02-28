package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;
import org.kryptojagd.level.hamming.HammingDistance;
import org.kryptojagd.encryptionmethods.*;

/**
 * The class describes a task, where you have to encrypt the given text
 *
 * @author Sonja Kuklok, Sven Strasser, Amelie Reichert, Bartosz Treyde
 */
public class EncryptionTask implements Task {

    /**
     * The constant WRONGCOUNTLETTER
     * (if there are more or less letters in answer compared to correct answer).
     */
    public static final String WRONGCOUNTLETTER = "Pass auf, dass du genau so viele Buchstaben verschlüsselst "
            + "wie auch in der Nachricht vorkommen.";
    /**
     * The constant WRONGBACKWARDS
     * (if whole text is encrypted backwards instead of word by word).
     */
    public static final String WRONGBACKWARDS = "Pass auf, jedes Wort sollte einzeln verschlüsselt werden.";
    /**
     * The constant WRONGFLIP
     * (if the answer is encrypted correctly except of one or two letter-flips).
     */
    public static final String WRONGFLIP = "Pass auf, es scheint so als hätten sich ein oder zwei Buchstabendreher "
            + "bei dir eingeschlichen.";
    /**
     * The constant TYPINGERROR
     * (if there are one, two or three typing errors).
     */
    public static final String TYPINGERROR = "Pass auf, es scheint so als hätten sich ein paar Tippfehler bei dir"
            + " eingeschlichen.";
    /**
     * The constant WRONGCOUNTLETTER
     * (if the answer has too many mistakes).
     */
    public static final String ALLWRONG = "Schade, dass ist leider nicht richtig verschlüsselt, versuche es noch "
            + "einmal.";


    private String task;

    private String text;

    private String key;

    private String mistakeMsg;

    private Encryption encryptionMethod;

    private String encryptionType;

    private boolean taskCompleted;

    private HammingDistance hammingDistance = new HammingDistance();

    private int hammingDistanceValue;

    private String name = "EncryptionTask";

    /**
     * Creates a {@link EncryptionTask}
     *
     * @param task             the task
     * @param text             the text to be encrypted
     * @param encryptionType   the encryption method as a String
     * @param key              the key
     * @param encryptionMethod the encryption method, in which you have to encrypt the text
     */
    public EncryptionTask(String task, String text, String encryptionType, String key, Encryption encryptionMethod) {
        this.encryptionType = encryptionType;
        this.task = task;
        this.text = text;
        this.key = key;
        this.encryptionMethod = encryptionMethod;
        this.taskCompleted = false;
        this.mistakeMsg = "Standardfehler";
    }

    /**
     * Set up the key for caesar encryption.
     *
     * @param key given as an integer
     */
    public void setCaaesarKey(int key) {
        this.key = Integer.toString(key);
    }

    /**
     * Gets the fitting mistake message.
     *
     * @return the mistake msg
     */
    public String getMistakeMsg() {
        return mistakeMsg;
    }

    /**
     * Gets task.
     *
     * @return the task
     */
    public String getTaskText() {
        return task;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Get encryption as a String.
     *
     * @return the string
     */
    public String getEncryption() {
        return encryptionType;
    }

    /**
     * Get key as a String.
     *
     * @return the string
     */
    public String getKey() {
        return key;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
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
        char[] origMsg = new char[this.text.length()];
        char[] studentSolution = new char[answer.length()];
        String realSolutionString = this.encryptionMethod.encode(this.text);
        char[] realSolution = new char[realSolutionString.length()];

        for (int i = 0; i < this.text.length(); i++) {
            origMsg[i] = Character.toUpperCase(this.text.charAt(i));
        }
        for (int i = 0; i < answer.length(); i++) {
            studentSolution[i] = Character.toUpperCase(answer.charAt(i));
        }
        for (int i = 0; i < realSolutionString.length(); i++) {
            realSolution[i] = realSolutionString.charAt(i);
        }
        String upperAnswer = answer.toUpperCase();
        this.taskCompleted = upperAnswer.equals(realSolutionString);
        this.hammingDistanceValue = HammingDistance.calculateHammingDistance(realSolutionString, upperAnswer);
        if (upperAnswer.equals(realSolutionString)) {
            return true;
        } else if (studentSolution.length != realSolution.length) {
            this.mistakeMsg = WRONGCOUNTLETTER;
        } else if ((this.encryptionMethod instanceof Backwards) && palindrome(studentSolution, origMsg)) {
            this.mistakeMsg = WRONGBACKWARDS;
        } else if (this.hammingDistanceValue <= 4 && flipTest(studentSolution, realSolution)) {
            this.mistakeMsg = WRONGFLIP;
        } else if (this.hammingDistanceValue <= 3) {
            this.mistakeMsg = TYPINGERROR;
        } else {
            this.mistakeMsg = ALLWRONG;
        }
        return false;
    }

    /**
     * Tests if there are one or two transposed letters in the Solution from the student.
     *
     * @param a1 the written Solution from the student
     * @param a2 the original Solution
     * @return the boolean
     */
    boolean flipTest(char[] a1, char[] a2) {
        for (int i = 1; i < a1.length; i++) {
            if ((a1[i] == a2[i - 1]) && (a1[i - 1] == a2[i]) && (a1[i - 1] != a2[i - 1]) && (a1[i] != a2[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tests if the student has turned around the whole message
     * instead of turning around word by word.
     *
     * @param a1 the written Solution from the student
     * @param a2 the plain Text
     * @return the boolean
     */
    boolean palindrome(char[] a1, char[] a2) {
        int n = a1.length;
        for (int i = 0; i < n; i++) {
            if (a1[i] != a2[ n - i - 1]) {
                return false;
            }
        }
        return true;
    }

    //ToDo default
    @Override
    public String[] getPossibilities() {
        return new String[0];
    }

    @Override
    public String toString() {
        return "EncryptionTask";
    }

    /**
     * Returns if the task is complete
     *
     * @return true or false as boolean
     */
    public boolean getTaskCompleted() {
        return this.taskCompleted;
    }

    /**
     * Returns the calculated hamming distance.
     *
     * @return hamming distance as an integer
     */
    public int getHammingDistanceValue() {
        return this.hammingDistanceValue;
    }

    @Override
    public void setTaskCompletedEnd() {
        this.taskCompleted = false;
    }
}
