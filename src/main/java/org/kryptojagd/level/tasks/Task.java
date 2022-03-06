package org.kryptojagd.level.tasks;

import org.kryptojagd.level.PointSystem;

/**
 * The interface describes the form of a task in the game
 *
 * @author Sonja Kuklok, Amelie Reichert
 */
public interface Task {

    /**
     * The constant POINT_SYSTEM.
     */
    PointSystem POINT_SYSTEM = new PointSystem(0);

    /**
     * Checks the answer to the task
     *
     * @param answer the answer
     * @return true, if the answer is correct
     */
    boolean proveAnswer(String answer);

    /**
     * Getter for the name of the task
     *
     * @return name of the task
     */
    String getName();


    /**
     * Setter for the name of the task
     *
     * @param name of the task
     */
    void setName(String name);

    /**
     * Returns if the task is complete
     *
     * @return true or false as boolean
     */
    boolean getTaskCompleted();

    /**
     * Set true or false if the task is completed
     */
    void setTaskCompletedEnd();

    /**
     * Getter for the Content of the helpText
     *
     * @return name of the helpText
     */
    String getHelpText();

    /**
     * Setter for the Content of the helpText
     *
     * @param newHelpText the new help text
     */
    void setHelpText(String newHelpText);

    /**
     * Returns the possibilities as a string array.
     *
     * @return possibilities as a string array
     */
    String[] getPossibilities();

    /**
     * Gets score.
     *
     * @return the score
     */
    int getScore();

    /**
     * Sets score.
     *
     * @param score the score
     */
    void setScore(int score);

    /**
     * Returns the task as string
     *
     * @return the task as string
     */
    String toString();
}