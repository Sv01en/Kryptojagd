package org.kryptojagd.level.tasks;

/**
 * The interface describes the form of a task in the game
 *
 * @author Sonja Kuklok
 */
public interface Task {

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
     * Returns the possibilities as a string array.
     *
     * @return possibilities as a string array
     */
    String[] getPossibilities();

    /**
     * Returns the task as string
     *
     * @return the task as string
     */
    String toString();

}
