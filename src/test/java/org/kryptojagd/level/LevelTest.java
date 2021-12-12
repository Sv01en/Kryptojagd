package org.kryptojagd.level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;
import org.kryptojagd.verschluesselungsverfahren.Caesar;

import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {

    private DecryptionTask decryptionTask;
    private EncryptionTask encryptionTask;
    private MultipleChoiceTask multipleChoiceTask;

    @BeforeEach
    void setUp() {
        decryptionTask = new DecryptionTask(new Caesar());
        encryptionTask = new EncryptionTask(new Caesar());
        multipleChoiceTask = new MultipleChoiceTask(null, null, null);
    }

}
