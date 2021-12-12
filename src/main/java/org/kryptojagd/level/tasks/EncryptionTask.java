package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;

/**
 * The class describes a task, where you have to encrypt text
 *
 * @author Sonja
 */
public class EncryptionTask implements Task {

    private Encryption encryption;

    /**
     * Creates a {@link EncryptionTask}
     *
     * @param encryption the encryption, in which you have to encrypt the text
     */
    public EncryptionTask(Encryption encryption) {
        this.encryption = encryption;
    }

    @Override
    public boolean proofAnswer(String answer) {
        return false;
    }

}
