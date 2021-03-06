package org.kryptojagd.fileprocessing;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;

import static org.junit.Assert.*;

/**
 * The type Read json test.
 * @author Bartosz Treyde
 */
class ReadJsonTest {

  private final String path = "src/main/resources/org/kryptojagd/levels/level1/";

  /**
   * Test create multiple choice question.
   * Tests if the JSon files contain the right content.
   */
  @Test
  void testCreateMultipleChoiceQuestion() throws Exception {
    ReadDirectory.initialize();
    String pathToQuestion = path + "question1.json";
    String question = "Warum werden Texte verschluesselt? Hat das irgend einen bestimmten Grund?";
    String correctAnswer = "Ja, der Text kann sonst von jedem gelesen werden und das waere nicht "
        + "sicher.";
    String[] possibilities = {"Nein, der Verfasser wollte sich nur einen Spass erlauben.",
        "Ja, der Text kann sonst von jedem gelesen werden und "
          + "das waere nicht sicher.",
        "Ja, denn der Verfasser kommt vermutlich aus einem Land "
          + "indem es ueblich ist Texte so zu schreiben."};

    MultipleChoiceTask q = ReadJson.createMultipleChoiceTask(pathToQuestion);
    // assert(q.proveAnswer(correctAnswer));
    if(q != null) {
      Assertions.assertTrue(q.proveAnswer(correctAnswer));
      Assertions.assertEquals(q.getQuestion(), question);
      Assertions.assertEquals(q.getPossibilities()[0], possibilities[0]);
      Assertions.assertEquals(q.getPossibilities()[1], possibilities[1]);
      Assertions.assertEquals(q.getPossibilities()[2], possibilities[2]);
    }
  }

  /**
   * Test create encryption task.
   * Tests if the JSon files contain the right content.
   */
  @Test
  void testCreateEncryptionTask() {
    String pathToEncryption = path + "encryption.json";
    String encType = "Backwards";
    String text = "Erste Floppy-Disk gefunden";
    String task = "Verschluessle den folgenden Text mit dem Rueckwaertsverschluesselungsverfahren:\n\nHinweise\n1." +
    " Gib deine Loesung in Upper Case ein\n2. Verschluessle jedes Wort einzeln, unabhaengig von den Sonderzeichen.\n";

    EncryptionTask enc = ReadJson.createEncryptionTask(pathToEncryption);

    if(enc != null) {
      Assertions.assertEquals(enc.getEncryption().toString(), encType);
      Assertions.assertEquals(enc.getTaskText(), task);
      Assertions.assertEquals(enc.getText(), text);
    }
  }

  /**
   * Test create decryption task.
   * Tests if the JSon files contain the right content.
   */
  @Test
  void testCreateDecryptionTask() {

    String pathToDecryption = path + "decryption.json";
    String plainText = "In der Stadt mit einem eisernen Turm und der glaesernen Pyramide "
        + "befindet sich die Disk.\nSie wird bewacht von der Frau mit dem geheimnisvollen "
        + "Laecheln und hat den besten Blick auf den glitzernden Fluss der die Stadt teilt.\n"
        + "Jeden morgen kann sie die vielseitigen Klaenge der Glocken hoeren, die Quasimodo "
        + "liebevoll umsorgt.";
    String encryptionMethod = "R??ckw??rtsverschl??sselung";
    String[] answerOptionsEncryption = {"R??ckw??rtsverschl??sselung", "RSA Verschl??sselung", 
        "C??sar Verschl??sselung"};
    int correctAnswerEncryption = 0;
    String[] answerOptionsCity = {"Prag", "Paris", "Stockholm"};
    int correctAnswerCity = 1;
    String textAfterStart = "Ihr seid gerade in Paris gelandet."
        + "\nEure Kontaktperson wurde jedoch kurz vor eurer Ankunft gewarnt, "
        + "dass eure Erzfeindin Eve ebenfalls auf der Suche nach den antiken Floppy-Disks ist.\n"
        + "Ihr mu??sst euch beeilen, damit sie euch nicht zuvor kommt.\nSCHNELL!";
    int timeInSec = 300;
    int timePenalty = 20;

    DecryptionTask dec = ReadJson.createDecryptionTask(pathToDecryption);


if(dec != null) {
  Assertions.assertEquals(dec.getPlainText(), plainText);
  Assertions.assertEquals(dec.getEncryptionMethod().toString(), encryptionMethod);
  Assertions.assertEquals(dec.getCorrectAnswerEncryption(), correctAnswerEncryption);
  Assertions.assertArrayEquals(dec.getCityTask().getPossibilities(), answerOptionsCity);
  Assertions.assertEquals(dec.getCityTask().getCorrectAnswer(), dec.getCityTask().getPossibilities()[correctAnswerCity]);
  Assertions.assertEquals(dec.getTextAfterStart(), textAfterStart);
  Assertions.assertEquals(dec.getAnswerOptionsEncryption(), answerOptionsEncryption);
  Assertions.assertEquals(timeInSec, dec.getTimeInSec());
  Assertions.assertEquals(timePenalty, dec.getTimePenalty());
}
  }

  /**
   * Test read time.
   * Tests if the JSon files contain the right content.
   */
  @Test
  void testReadTime() {

    Assertions.assertTrue(ReadJson.readTime(path + "time.json") <= 1200 && ReadJson.readTime(path + "time.json") >= 600);
  }


}
