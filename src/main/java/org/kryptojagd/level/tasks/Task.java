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
    boolean proofAnswer(String answer);

    /**
     * Returns the task as string
     *
     * @return the task as string
     */
    String toString();
}
