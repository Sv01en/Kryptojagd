package org.kryptojagd.Tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kryptojagd.level.tasks.DecryptionTask;

public class DecryptionTaskTest {

    private static DecryptionTask task;
    private String plainText = "Hallo Welt, der Tag ist schön!";
    private String encryptionType  = "Rückwärtsverschlüsselung";
    private String[] answerOptionsEncryption = new String[]{"Rückwärtsverschlüsselung", "test1", "test2"};
    private int correctAnswerEncryption = 0;
    private String[] answerOptionsCity = new String[]{"Hamburg", "Stadt", "Dorf"};
    private int correctAnswerCity = 0;
    private String textAfterStart = "Ende";
    private int timeInSec = 3;
    private int timePenalty = 20;
    private String helpText = "help text placeholder";


    // ############ Test proveAnswer() ##############

    @Test
    void trueAnswerTest() {
        task = new DecryptionTask(plainText, encryptionType, answerOptionsEncryption,
                correctAnswerEncryption, answerOptionsCity, correctAnswerCity, textAfterStart, timeInSec, timePenalty, helpText);
        Assertions.assertTrue(task.proveAnswer("Tag ist schön"));
    }

    @Test
    void falseAnswerTest() {
        task = new DecryptionTask(plainText, encryptionType, answerOptionsEncryption,
                correctAnswerEncryption, answerOptionsCity, correctAnswerCity, textAfterStart, timeInSec, timePenalty, helpText);
        Assertions.assertFalse(task.proveAnswer("schön"));
    }

    @Test
    void notPossibleAnswer() {
        task = new DecryptionTask(plainText, encryptionType, answerOptionsEncryption,
                correctAnswerEncryption, answerOptionsCity, correctAnswerCity, textAfterStart, timeInSec, timePenalty, helpText);
        Assertions.assertFalse(task.proveAnswer("abc"));
    }


    // ############# Test proveEncryptionType() ################

    @Test
    void trueAnswer() {
        task = new DecryptionTask(plainText, encryptionType, answerOptionsEncryption,
                correctAnswerEncryption, answerOptionsCity, correctAnswerCity, textAfterStart, timeInSec, timePenalty, helpText);
        Assertions.assertTrue(task.proveEncryptionType("Rückwärtsverschlüsselung"));
    }

    @Test
    void falseAnswer() {
        task = new DecryptionTask(plainText, encryptionType, answerOptionsEncryption,
                correctAnswerEncryption, answerOptionsCity, correctAnswerCity, textAfterStart, timeInSec, timePenalty, helpText);
        Assertions.assertFalse(task.proveEncryptionType("test1"));
    }

    @Test
    void notExistingAnswer() {
        task = new DecryptionTask(plainText, encryptionType, answerOptionsEncryption,
                correctAnswerEncryption, answerOptionsCity, correctAnswerCity, textAfterStart, timeInSec, timePenalty, helpText);
        Assertions.assertFalse(task.proveEncryptionType("abc"));
    }

}
