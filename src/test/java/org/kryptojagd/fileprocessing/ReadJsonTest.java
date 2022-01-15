package org.kryptojagd.fileprocessing;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.kryptojagd.level.tasks.DecryptionTask;
import org.kryptojagd.level.tasks.EncryptionTask;
import org.kryptojagd.level.tasks.MultipleChoiceTask;

class ReadJsonTest {

  private final String path = "src/main/resources/org/kryptojagd/levels/level1/";

  @Test
  void testCreateMultipleChoiceQuestion() {
    String pathToQuestion = path + "question1.json";
    String question = "Warum werden Texte verschlüsselt? Hat das irgend einen bestimmten Grund?";
    String correctAnswer = "Ja, der Text kann sonst von jedem gelesen werden und das wäre nicht "
        + "sicher.";
    String[] possibilities = {"Nein, der Verfasser wollte sich nur einen Spaß erlauben.", 
        "Ja, der Text kann sonst von jedem gelesen werden und "
          + "das wäre nicht sicher.",
        "Ja, denn der Verfasser kommt vermutlich aus einem Land "
          + "indem es üblich ist Texte so zu schreiben."};

    MultipleChoiceTask q = ReadJson.createMultipleChoiceTask(pathToQuestion);
    // assert(q.proofAnswer(correctAnswer));
    assertEquals(q.proofAnswer(correctAnswer), true);
    assertEquals(q.getQuestion(), question);
    assertEquals(q.getPossibilities()[0], possibilities[0]);
    assertEquals(q.getPossibilities()[1], possibilities[1]);
    assertEquals(q.getPossibilities()[2], possibilities[2]);
  }

  @Test
  void testCreateEncryptionTask() {
    String pathToEncryption = path + "encryption.json";
    String encType = "Backwards";
    String text = "Erste Floppy-Disk gefunden";
    String task = "Verschlüssle den folgenden Text mit dem Rückwärtsverschlüsselungsverfahren:";

    EncryptionTask enc = ReadJson.createEncryptionTask(pathToEncryption);


    assertEquals(enc.getEncryption(), encType);
    assertEquals(enc.getTask(), task);
    assertEquals(enc.getText(), text);
  }

  @Test
  void testCreateDecryptionTask() {

    String pathToDecryption = path + "decryption.json";
    String plainText = "In der Stadt mit einem eisernen Turm und der glaesernen Pyramide "
        + "befindet sich die Disk.\nSie wird bewacht von der Frau mit dem geheimnisvollen "
        + "Laecheln und hat den besten Blick auf den glitzernden Fluss der die Stadt teilt.\n"
        + "Jeden morgen kann sie die vielseitigen Klaenge der Glocken hoeren, die Quasimodo "
        + "liebevoll umsorgt.";
    String encryptionMethod = "Rückwärtsverschlüsselung";
    String[] answerOptionsEncryption = {"Rückwärtsverschlüsselung", "RSA Verschlüsselung", 
        "Cäsar Verschlüsselung"};
    int correctAnswerEncryption = 0;
    String[] answerOptionsCity = {"Prag", "Paris", "Stockholm"};
    int correctAnswerCity = 1;
    String textAfterStart = "Ihr seid gerade in Paris gelandet."
        + "\nEure Kontaktperson wurde jedoch kurz vor eurer Ankunft gewarnt, "
        + "dass eure Erzfeindin Eve ebenfalls auf der Suche nach den antiken Floppy-Disks ist.\n"
        + "Ihr müsst euch beeilen, damit sie euch nicht zuvor kommt.\nSCHNELL!";

    DecryptionTask dec = ReadJson.createDecryptionTask(pathToDecryption);
    
    //TODO

    assertEquals(dec.getPlainText(), plainText);
  }

  @Test
  void testReadTime() {
    //TODO
    // fail("Not yet implemented");
  }


}
