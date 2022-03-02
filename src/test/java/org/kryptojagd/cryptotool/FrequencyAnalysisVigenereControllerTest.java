package org.kryptojagd.cryptotool;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kryptojagd.cryptotools.FrequencyAnalysisVigenereController;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrequencyAnalysisVigenereControllerTest {

    static FrequencyAnalysisVigenereControllerTest v;

    @BeforeAll
    static void init() {
        v = new FrequencyAnalysisVigenereControllerTest();
    }

    // ########## Tests for addSpaces() #############

    @Test
    void testAddSpacesEmptyString() {
        assertEquals("", FrequencyAnalysisVigenereController.addSpacesAs("", ""));
    }

    @Test
    void testAddSpacesSpecialChars() {
        String textWithSpaces = "Hallo Welt, ein schönen Tag heute!";
        String textWithoutSpaces = "ollaHtleW,nienenöhcsgaTetueh!";
        String expected = "ollaH tleW, nie nenöhcs gaT etueh!";
        assertEquals(expected, FrequencyAnalysisVigenereController.addSpacesAs(textWithSpaces, textWithoutSpaces));
    }

    @Test
    void testAddSpaces() {
        String textWithSpaces = "Hallo Welt ein schönen Tag heute";
        String textWithoutSpaces = "ollaHtleWnienenöhcsgaTetueh";
        String expected = "ollaH tleW nie nenöhcs gaT etueh";
        assertEquals(expected, FrequencyAnalysisVigenereController.addSpacesAs(textWithSpaces, textWithoutSpaces));
    }

}
