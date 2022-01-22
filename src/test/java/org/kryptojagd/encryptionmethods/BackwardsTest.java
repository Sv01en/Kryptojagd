package org.kryptojagd.encryptionmethods;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kryptojagd.encryptionmethods.Backwards;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The class contains test cases for the Backward encryption
 *
 * @author Leah Schlimm
 */
public class BackwardsTest {

    static Backwards b;

    @BeforeAll
    static void init() {
        b = new Backwards();
    }

    /**
     * Tests the encode method with an empty text string and no key
     */
    @Test
    void testEmptyTextEncode() {
        assertEquals("", b.encode("", ""));
    }

    /**
     * Tests whether the encode method reads "HELLO" correctly encrypted
     */
    @Test
    void testNotEmptyTextNoWhitespaceEncode() {
        assertEquals("OLLEH", b.encode("Hello", ""));
    }

    /**
     * Tests whether the encode method reads "Hello,   World!" correctly encrypted
     */
    @Test
    void testNotEmptyTextWhiteSpaceEncode() {
        assertEquals("OLLEH,   DLROW!", b.encode("Hello,   World!", ""));
    }

    /**
     * Tests the decode method with an empty text string and no key
     */
    @Test
    void testEmptyTextDecode() {
        assertEquals("", b.decode("", ""));
    }

    /**
     * Tests whether the decode method reads "OLLEH" correctly decrypted
     */
    @Test
    void testNotEmptyTextNoWhitespaceDecode() {
        assertEquals("HELLO", b.decode("OLLEH", ""));
    }

    /**
     * Tests whether the decode method reads "OLLEH,   DLROW!" correctly decrypted
     */
    @Test
    void testNotEmptyTextWhiteSpaceDecode() {
        assertEquals("HELLO,   WORLD!", b.decode("OLLEH,   DLROW!", ""));
    }
}
