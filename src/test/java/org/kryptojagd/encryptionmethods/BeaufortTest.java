package org.kryptojagd.encryptionmethods;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kryptojagd.encryptionmethods.Beaufort;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The class contains test cases for Beaufort encryption
 *
 * @author Leah Schlimm
 */
public class BeaufortTest {

    static Beaufort b;

    @BeforeAll
    static void init() {
        b = new Beaufort();
    }


    //Decode-Methode kein Text

    /**
     * Tests the decryption method with an empty text string and a valid key "ABCDE"
     */
    @Test
    void testEmptyValidKeyDecode(){
        assertEquals("", b.decode("", "ABCDE"));
    }

    /**
     * Tests the decryption method with an empty text string and an invalid key "ABC%"
     */
    @Test
    void testEmptyUnvalidKeyDecode(){
        assertEquals("", b.decode("", "ABC%"));
    }

    /**
     * Tests the decryption method with an empty text string, empty key string
     */
    @Test
    void testEmptyDecodeNoKey() {
        assertEquals("", b.decode("", ""));
    }

    /**
     * Tests the decryption method with an empty text string and a valid key "abCde" that is not completely written in Uppercase
     */
    @Test
    void testEmptyValidKeyDecodeNoUppercaseKey() {
        assertEquals("", b.decode("", "abCde"));
    }

    /**
     * Tests the decryption method with an empty text string and a valid key in Lowercase "abcde"
     */
    @Test
    void testEmptyValidKeyDecodeLowercaseKey() {
        assertEquals("", b.decode("", "abcde"));
    }



    //Encode-Methode kein Text

    /**
     * Tests the encryption method with an empty text string and without a key
     */
    @Test
    void testEmptyNoKeyEncode(){
        assertEquals("", b.encode(""));
    }

    /**
     * Tests the encryption method with an empty text string and a valid key "ABCDE"
     */
    @Test
    void testEmptyValidKeyEncode(){
        assertEquals("", b.encode("", "ABCDE"));
    }

    /**
     * Tests the encryption method with an empty text string and an invalid key "ABC%"
     */
    @Test
    void testEmptyUnvalidKeyEncode(){
        assertEquals("", b.encode("", "ABC%"));
    }

    /**
     * Tests the encryption method with an empty text string and an empty key string
     */
    @Test
    void testEmptyEncodeNoKey() {
        assertEquals("", b.encode("", ""));
    }

    /**
     * Tests the encryption method with an empty text string and a valid key "abCde" that is not completely written in uppercase
     */
    @Test
    void testEmptyValidKeyEncodeNoUppercaseKey() { assertEquals("", b.encode("", "abCde")); }

    /**
     * Tests the encryption method with an empty text string and a valid key in lowercase "abcde"
     */
    @Test
    void testEmptyValidKeyEncodeLowercaseKey() { assertEquals("", b.encode("", "abcde")); }



    //Decode-Methode mit Key und Text

    /**
     * Tests whether the decryption method with "SYMLH CTLC!" and the key "ABCDE" provides the correct decryption
     */
    @Test
    void testTextValidKeyDecode(){
        assertEquals("HALLO WELT!", b.decode("TBRSQ EXRK!", "ABCDE"));
    }

    /**
     * Tests whether the decryption method with "Symlh Ctlc!" and the key "ABCDE" provides the correct decryption in uppercase
     */
    @Test
    void testTextValidKeyDecodeUppercase(){
        assertEquals("HALLO WELT!", b.decode("Tbrsq Exrk!", "ABCDE"));
    }

    /**
     * Tests whether the decryption method with "Symlh Ctlc!" and the invalid key "ABC%" returns the encrypted input in uppercase
     */
    @Test
    void testTextUnvalidKeyDecode(){
        assertEquals("SYMLH CTLC!", b.decode("Symlh Ctlc!", "ABC%"));
    }

    @Test
    void testSomething(){assertEquals("Xuhyjza Pbozwn-Lkmk gyjrylor".toUpperCase(), b.decode("Fuenfte Floppy-Disk gefunden","Colloseum"));}

    /**
     * Tests whether the decryption method with "Symlh Ctlc!" and returns the encrypted input in uppercase to the empty key string
     */
    @Test
    void testTextDecodeNoKey() {
        assertEquals("HALLO WELT!", b.decode("Hallo Welt!", ""));
    }

    /**
     * Tests whether the decryption method with "Symlh Ctlc!" and the key "abCde" provides the correct decryption in uppercase
     */
    @Test
    void testTextValidKeyDecodeNoUppercaseKey() {
        assertEquals("HALLO WELT!", b.decode("Tbrsm Fysh!", "abCd"));
    }

    /**
     * Tests whether the decryption method with "Symlh Ctlc!" and the key "abcde" provides the correct decryption in uppercase
     */
    @Test
    void testTextValidKeyDecodeLowercaseKey() {
        assertEquals("HALLO WELT!", b.decode("Tbrsm Fysh!", "abcd"));
    }

    /**
     * Tests whether the cyclical shifting works
     */
    @Test
    void testTextValidKeyDecodeOverflow(){
        assertEquals("HALLO WELT!", b.decode("MEHIF IOIA!", "TEST"));
    }



    //Encode-Methode mit Key und Text

    /**
     * Tests whether the decryption method with "SYMLH DUMD!" and the key "ABCDE" provides the correct encryption
     */
    @Test
    void testTextValidKeyEncode(){
        assertEquals("HALLO WELT!", b.encode("SYMLH DUMD!", "ABCDE"));
    }

    /**
     * Tests whether the encryption method with "Symlh Dumd!" and the key "ABCDE" provides the correct encryption in uppercase
     */
    @Test
    void testTextValidKeyEncodeUppercase(){
        assertEquals("HALLO WELT!", b.encode("Symlh Dumd!", "ABCDE"));
    }

    /**
     * Tests whether the encryption method with "Symlh Ctlc!" and the invalid key "ABC%" returns the unencrypted input in uppercase
     */
    @Test
    void testTextUnvalidKeyEncode() {
        assertEquals("SYMLH CTLC!", b.encode("Symlh Ctlc!", "ABC%"));
    }

    /**
     * Tests whether the encryption method with "Symlh Ctlc!" and returns the unencrypted input in uppercase to the empty key string
     */
    @Test
    void testTextEncodeNoKey() {
        assertEquals("SYMLH CTLC!", b.encode("Symlh Ctlc!", ""));
    }

    /**
     * Testet, ob die Verschluesselungs-Methode mit "ymll Ctlg!" und dem Schluessel "abCde" die korrekte Verschluesselung in uppercase liefert
     */
    @Test
    void testTextValidKeyEncodeNoUppercaseKey(){
        assertEquals("HALLO WELT!", b.encode("Symll Ctlg!", "abCd"));
    }

    /**
     * Tests whether the encryption method with "Symlh Ctlc!" and the key "abcde" provides the correct encryption in uppercase
     */
    @Test
    void testTextValidKeyEncodeLowercaseKey(){
        assertEquals("HALLO WELT!", b.encode("Symll Ctlg!", "abcd"));
    }

    /**
     * Tests whether the cyclical shifting works
     */
    @Test
    void testTextValidKeyEncodeOverflow(){
        assertEquals("ZVWVS ZDVN!", b.encode("HALLO WELT!", "TEST"));
    }



    // Key-Generierung

    /**
     * Tests whether the method generates a random int value between really greater than 3 and really less than 7
     */
    @Test
    void testGenerateKeyLength(){
        for (int i = 0; i < 1000; i++){
            int keyLength = b.keyLength();
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
            int keySymbolIndex = b.keySymbolIndex();
            if(keySymbolIndex < 0  || keySymbolIndex >= 26){
                fail();
            }
        }
        assertTrue(true);
    }
}
