package org.kryptojagd.logic.verschluesselungsverfahren;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BackwardsTest {

    @Test
    void testEmptyTextEncode() {
        assertEquals("", Backwards.encode("", ""));
    }

    @Test
    void testNotEmptyTextNoWhitespaceEncode() {
        assertEquals("OLLEH", Backwards.encode("Hello", ""));
    }

    @Test
    void testNotEmptyTextWhiteSpaceEncode() {
        assertEquals("OLLEH,   DLROW!", Backwards.encode("Hello,   World!", ""));
    }

    @Test
    void testEmptyTextDecode() {
        assertEquals("", Backwards.decode("", ""));
    }

    @Test
    void testNotEmptyTextNoWhitespaceDecode() {
        assertEquals("HELLO", Backwards.decode("OLLEH", ""));
    }

    @Test
    void testNotEmptyTextWhiteSpaceDecode() {
        assertEquals("HELLO,   WORLD!", Backwards.decode("OLLEH,   DLROW!", ""));
    }
}
