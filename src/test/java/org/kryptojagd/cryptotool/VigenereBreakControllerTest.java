package org.kryptojagd.cryptotool;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kryptojagd.cryptotools.VigenereBreakController;
import org.kryptojagd.encryptionmethods.Backwards;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VigenereBreakControllerTest {

    static VigenereBreakControllerTest v;

    @BeforeAll
    static void init() {
        v = new VigenereBreakControllerTest();
    }

    // ########## Tests for addSpaces() #############

    @Test
    void testAddSpacesEmptyString() {
        assertEquals("", VigenereBreakController.addSpacesAs("", ""));
    }

    @Test
    void testAddSpacesSpecialChars() {
        String textWithSpaces = "Hallo Welt, ein schönen Tag heute!";
        String textWithoutSpaces = "ollaHtleW,nienenöhcsgaTetueh!";
        String expected = "ollaH tleW, nie nenöhcs gaT etueh!";
        assertEquals(expected, VigenereBreakController.addSpacesAs(textWithSpaces, textWithoutSpaces));
    }

    @Test
    void testAddSpaces() {
        String textWithSpaces = "Hallo Welt ein schönen Tag heute";
        String textWithoutSpaces = "ollaHtleWnienenöhcsgaTetueh";
        String expected = "ollaH tleW nie nenöhcs gaT etueh";
        assertEquals(expected, VigenereBreakController.addSpacesAs(textWithSpaces, textWithoutSpaces));
    }

}
