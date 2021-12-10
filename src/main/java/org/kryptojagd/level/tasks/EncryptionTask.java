package org.kryptojagd.level.tasks;

import org.kryptojagd.verschluesselungsverfahren.Encryption;


public class EncryptionTask implements Task {

    private Encryption encryption;

    public EncryptionTask(Encryption encryption) {
        this.encryption = encryption;
    }

    @Override
    public boolean proofAnswer(String answer) {
        return false;
    }

}
