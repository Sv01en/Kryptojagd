package org.kryptojagd.level.tasks;

import org.kryptojagd.encryptionmethods.Encryption;


public class DecryptionTask implements Task {

    private Encryption encryption;

    public DecryptionTask(Encryption encryption) {
        this.encryption = encryption;
    }

    @Override
    public boolean proofAnswer(String answer) {
        return false;
    }

}
