package org.kryptojagd.level.tasks;

<<<<<<< HEAD
import org.kryptojagd.encryptionmethods.Encryption;

=======
>>>>>>> 720278078edb37aa760bdf7d21f5ca5bec41b96c
/**
 * The interface describes the form of a task in the game
 *
 * @author Sonja
 */
public interface Task {


    /**
     * Checks the answer to the task
     *
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
