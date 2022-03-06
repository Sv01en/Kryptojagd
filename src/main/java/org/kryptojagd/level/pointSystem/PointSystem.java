package org.kryptojagd.level.pointSystem;


/**
 * The type Point system.
 *
 * @author Amelie Reichert, Bartosz Treyde
 * @version 1.0
 */
public class PointSystem {

    private static int score;
    private static int currentLevel = 1;
    private static int playedLevels = 0;
    private static int decryptionTaskFinished;
    private static int multipleChoiceTaskFinished;
    private static boolean encryptionTaskedFinished;

    /**
     * Instantiates a new Point system.
     *
     * @param score the score
     */
    public PointSystem(int score) {
        PointSystem.score = score;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public static int getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        if (currentLevel > playedLevels) {
            PointSystem.score = score;
        }
    }

    /**
     * Gets decryption task finished.
     *
     * @return the decryption task finished
     */
    public static int getDecryptionTaskFinished() {
        return decryptionTaskFinished;
    }

    /**
     * Gets multiple choice task finished.
     *
     * @return the multiple choice task finished
     */
    public static int getMultipleChoiceTaskFinished() {
        return multipleChoiceTaskFinished;
    }

    /**
     * Gets encryption task finished.
     *
     * @return the encryption task finished
     */
    public static boolean getEncryptionTaskFinished() {return encryptionTaskedFinished; }

    /**
     * Set decryption task finished.
     *
     * @param i the
     */
    public static void setDecryptionTaskFinished(int i) {
        if (i != 0) {
            PointSystem.decryptionTaskFinished += i;
        } else {
            PointSystem.decryptionTaskFinished = 0;
        }
    }

    /**
     * Set encryption tasked finished.
     *
     * @param b the b
     */
    public static void setEncryptionTaskedFinished(boolean b) {
        PointSystem.encryptionTaskedFinished = b;
    }

    /**
     * Set multiple choice task finished.
     *
     * @param i the
     */
    public static void setMultipleChoiceTaskFinished(int i) {
        if (i != 0) {
            PointSystem.multipleChoiceTaskFinished += i;
        } else {
            PointSystem.multipleChoiceTaskFinished = 0;
        }
    }

    /**
     * Sets played levels.
     *
     * @param playedLevels the played levels
     */
    public static void setPlayedLevels(int playedLevels) {
        PointSystem.playedLevels = playedLevels;
    }
}
