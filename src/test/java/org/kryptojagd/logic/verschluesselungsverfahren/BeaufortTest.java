package org.kryptojagd.logic.verschluesselungsverfahren;

import org.junit.jupiter.api.Test;
import org.kryptojagd.verschluesselungsverfahren.Beaufort;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The class contains test cases for Beaufort encryption
 *
 * @author Leah Schlimm
 */
public class BeaufortTest {


    //Decode-Methode kein Text

    /**
     * Tests the decryption method with an empty text string and a valid key "ABCDE"
     */
    @Test
    void testEmptyValidKeyDecode(){
        assertEquals("", Beaufort.decode("", "ABCDE"));
    }

    /**
     * Tests the decryption method with an empty text string and an invalid key "ABC%"
     */
    @Test
    void testEmptyUnvalidKeyDecode(){
        assertEquals("", Beaufort.decode("", "ABC%"));
    }

    /**
     * Tests the decryption method with an empty text string, empty key string
     */
    @Test
    void testEmptyDecodeNoKey() {
        assertEquals("", Beaufort.decode("", ""));
    }

    /**
     * Tests the decryption method with an empty text string and a valid key "abCde" that is not completely written in Uppercase
     */
    @Test
    void testEmptyValidKeyDecodeNoUppercaseKey() {
        assertEquals("", Beaufort.decode("", "abCde"));
    }

    /**
     * Tests the decryption method with an empty text string and a valid key in Lowercase "abcde"
     */
    @Test
    void testEmptyValidKeyDecodeLowercaseKey() {
        assertEquals("", Beaufort.decode("", "abcde"));
    }



    //Encode-Methode kein Text

    /**
     * Tests the encryption method with an empty text string and without a key
     */
    @Test
    void testEmptyNoKeyEncode(){
        assertEquals("", Beaufort.encode(""));
    }

    /**
     * Tests the encryption method with an empty text string and a valid key "ABCDE"
     */
    @Test
    void testEmptyValidKeyEncode(){
        assertEquals("", Beaufort.encode("", "ABCDE"));
    }

    /**
     * Tests the encryption method with an empty text string and an invalid key "ABC%"
     */
    @Test
    void testEmptyUnvalidKeyEncode(){
        assertEquals("", Beaufort.encode("", "ABC%"));
    }

    /**
     * Tests the encryption method with an empty text string and an empty key string
     */
    @Test
    void testEmptyEncodeNoKey() {
        assertEquals("", Beaufort.encode("", ""));
    }

    /**
     * Tests the encryption method with an empty text string and a valid key "abCde" that is not completely written in uppercase
     */
    @Test
    void testEmptyValidKeyEncodeNoUppercaseKey() { assertEquals("", Beaufort.encode("", "abCde")); }

    /**
     * Tests the encryption method with an empty text string and a valid key in lowercase "abcde"
     */
    @Test
    void testEmptyValidKeyEncodeLowercaseKey() { assertEquals("", Beaufort.encode("", "abcde")); }



    //Decode-Methode mit Key und Text

    /**
     * Tests whether the decryption method with "SYMLH CTLC!" and the key "ABCDE" provides the correct decryption
     */
    @Test
    void testTextValidKeyDecode(){
        assertEquals("HALLO WELT!", Beaufort.decode("SYMLH CTLC!", "ABCDE"));
    }

    /**
     * Tests whether the decryption method with "Symlh Ctlc!" and the key "ABCDE" provides the correct decryption in uppercase
     */
    @Test
    void testTextValidKeyDecodeUppercase(){
        assertEquals("HALLO WELT!", Beaufort.decode("Symlh Ctlc!", "ABCDE"));
    }

    /**
     * Tests whether the decryption method with "Symlh Ctlc!" and the invalid key "ABC%" returns the encrypted input in uppercase
     */
    @Test
    void testTextUnvalidKeyDecode(){
        assertEquals("SYMLH CTLC!", Beaufort.decode("Symlh Ctlc!", "ABC%"));
    }

    /**
     * Tests whether the decryption method with "Symlh Ctlc!" and returns the encrypted input in uppercase to the empty key string
     */
    @Test
    void testTextDecodeNoKey() {
        assertEquals("HALLO WELT!", Beaufort.decode("Hallo Welt!", ""));
    }

    /**
     * Tests whether the decryption method with "Symlh Ctlc!" and the key "abCde" provides the correct decryption in uppercase
     */
    @Test
    void testTextValidKeyDecodeNoUppercaseKey() {
        assertEquals("HALLO WELT!", Beaufort.decode("Symll Bsof!", "abCd"));
    }

    /**
     * Tests whether the decryption method with "Symlh Ctlc!" and the key "abcde" provides the correct decryption in uppercase
     */
    @Test
    void testTextValidKeyDecodeLowercaseKey() {
        assertEquals("HALLO WELT!", Beaufort.decode("Symll Bsof!", "abcd"));
    }

    /**
     * Tests whether the cyclical shifting works
     */
    @Test
    void testTextValidKeyDecodeOverflow(){
        assertEquals("HALLO WELT!", Beaufort.decode("ZVWVS LCVC!", "TEST"));
    }



    //Encode-Methode mit Key und Text

    /**
     * Tests whether the decryption method with "SYMLH CTLC!" and the key "ABCDE" provides the correct encryption
     */
    @Test
    void testTextValidKeyEncode(){
        assertEquals("HALLO WELT!", Beaufort.encode("SYMLH CTLC!", "ABCDE"));
    }

    /**
     * Tests whether the encryption method with "Symlh Ctlc!" and the key "ABCDE" provides the correct encryption in uppercase
     */
    @Test
    void testTextValidKeyEncodeUppercase(){
        assertEquals("HALLO WELT!", Beaufort.encode("Symlh Ctlc!", "ABCDE"));
    }

    /**
     * Tests whether the encryption method with "Symlh Ctlc!" and the invalid key "ABC%" returns the unencrypted input in uppercase
     */
    @Test
    void testTextUnvalidKeyEncode() {
        assertEquals("SYMLH CTLC!", Beaufort.encode("Symlh Ctlc!", "ABC%"));
    }

    /**
     * Tests whether the encryption method with "Symlh Ctlc!" and returns the unencrypted input in uppercase to the empty key string
     */
    @Test
    void testTextEncodeNoKey() {
        assertEquals("SYMLH CTLC!", Beaufort.encode("Symlh Ctlc!", ""));
    }

    /**
     * Testet, ob die Verschluesselungs-Methode mit "Symlh Ctlc!" und dem Schluessel "abCde" die korrekte Verschluesselung in uppercase liefert
     */
    @Test
    void testTextValidKeyEncodeNoUppercaseKey(){
        assertEquals("HALLO WELT!", Beaufort.encode("Symll Bsof!", "abCd"));
    }

    /**
     * Tests whether the encryption method with "Symlh Ctlc!" and the key "abcde" provides the correct encryption in uppercase
     */
    @Test
    void testTextValidKeyEncodeLowercaseKey(){
        assertEquals("HALLO WELT!", Beaufort.encode("Symll Bsof!", "abcd"));
    }

    /**
     * Tests whether the cyclical shifting works
     */
    @Test
    void testTextValidKeyEncodeOverflow(){
        assertEquals("ZVWVS LCVC!", Beaufort.encode("HALLO WELT!", "TEST"));
    }



    // Key-Generierung

    /**
     * Tests whether the method generates a random int value between really greater than 3 and really less than 7
     */
    @Test
    void testGenerateKeyLength(){
        for (int i = 0; i < 1000; i++){
            int keyLength = Beaufort.keyLength();
            if(keyLength <= 3 || keyLength >= 7){
                fail();
            }
        }
        assertTrue(true);
    }

    /**
     * Tests whether the method generates a random int value between greater than or equal to 0 and truly less than 26
     */
    @Test
    void testGenerateKeySymbolIndex(){
        for (int i = 0; i < 1000; i++){
            int keySymbolIndex = Beaufort.keySymbolIndex();
            if(keySymbolIndex < 0  || keySymbolIndex >= 26){
                fail();
            }
        }
        assertTrue(true);
    }
}
