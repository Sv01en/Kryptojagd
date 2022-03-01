package org.kryptojagd.level.pointSystem;

/**
 * The type Point system.
 */
public class PointSystem {

    private static int score;

    /**
     * Instantiates a new Point system.
     *
     * @param score the score
     */
    public PointSystem (int score){
        this.score = score;
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
        this.score = score;
    }
}
