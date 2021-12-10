package org.kryptojagd.level.tasks;

/**
 * The interface describes the form of a task in the game
 *
 * @author Sonja
 */
public abstract class Task {


    /**
     * Checks the answer to the task
     *
     * @return true, if the answer is correct
     */
    public boolean proofAnswer(String answer) {
        return false;
    }

    /**
     * Returns the task as string
     *
     * @return the task as string
     */
    public String toString() {
        return "";
    }
}
