package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;
import org.kryptojagd.level.hamming.HammingDistance;
import org.kryptojagd.encryptionmethods.*;
import org.kryptojagd.level.pointSystem.PointSystem;

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

    private String helpText;

    private String key;

    private String mistakeMsg;

    private Encryption encryptionMethod;

    private String encryptionType;

    private boolean taskCompleted;

    private int hammingDistanceValue;

    private String name = "EncryptionTask";

    private final int pointsEncryptionTask = 50;

    /**
     * Creates a {@link EncryptionTask}
     *
     * @param task             the task
     * @param text             the text to be encrypted
     * @param encryptionType   the encryption method as a String
     * @param key              the key
     * @param encryptionMethod the encryption method, in which you have to encrypt the text
     * @param helpText         the help text
     */
    public EncryptionTask(String task, String text, String encryptionType, String key,
                          Encryption encryptionMethod, String helpText) {
        this.encryptionType = encryptionType;
        this.task = task;
        this.text = text;
        this.key = key;
        encryptionMethod.setKey(key);
        this.encryptionMethod = encryptionMethod;
        this.taskCompleted = false;
        this.mistakeMsg = "Standardfehler";
        this.helpText = helpText;
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
    public Encryption getEncryption() {
        encryptionMethod.setKey(key);
        return encryptionMethod;
    }

    /**
     * Get encryption as a String.
     *
     * @return the string
     */
    public String getEncryptionType() {
        return encryptionType;
    }

    /**
     * Set name.
     *@param name given as a String
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Set score.
     *@param score given as an integer
     */
    public void setScore(int score) {
        POINT_SYSTEM.setScore(score);
    }

    /**
     * Get score.
     *
     * @return the score
     */
    public int getScore() {
        return PointSystem.getScore();
    }

    /**
     * Gets the fitting text-based Help.
     *
     * @return the helpText
     */
    public String getHelpText() {
        return helpText;
    }

    @Override
    public void setHelpText(String newHelpText) {
        this.helpText = newHelpText;
    }

    @Override
    public boolean proveAnswer(String answer) {
        if (encryptionType.equals("Beaufort")) {
            Beaufort.setIsEncryptionTask(true);
        }
        encryptionMethod.setKey(key);
        char[] origMsg = new char[this.text.length()];
        char[] studentSolution = new char[answer.length()];
        //String realSolutionString = this.encryptionMethod.encode(this.text);
        String realSolutionString = this.encryptionMethod.decode(this.text, key);
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
            if (!PointSystem.getEncryptionTaskFinished()) {
                POINT_SYSTEM.setScore(PointSystem.getScore() + pointsEncryptionTask);
                PointSystem.setEncryptionTaskedFinished(true);
            }
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

    @Override
    public void setTaskCompletedEnd() {
        this.taskCompleted = false;
    }

    /**
     * Sets encryption method.
     *
     * @param encryption the encryption
     */
    public void setEncryptionMethod(Encryption encryption) {
        this.encryptionMethod = encryption;
    }
}
