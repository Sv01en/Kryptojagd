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

    /**
     * The constant getPlayedLevels.
     */
    public static int getPlayedLevels;
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
     * Sets current level.
     *
     * @param currentLevel the current level
     */
    public static void setCurrentLevel(int currentLevel) {
        PointSystem.currentLevel = currentLevel;
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
