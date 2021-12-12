package org.kryptojagd.level.tasks;

import org.kryptojagd.verschluesselungsverfahren.Encryption;

/**
 * The class describes a task, where you have to decrypt text
 *
 * @author Sonja
 */
public class DecryptionTask implements Task {

    private Encryption encryption;

    /**
     * Creates a {@link DecryptionTask}
     *
     * @param encryption the encryption, in which you have to decrypt the text
     */
    public DecryptionTask(Encryption encryption) {
        this.encryption = encryption;
    }

    @Override
    public boolean proofAnswer(String answer) {
        return false;
    }

}
