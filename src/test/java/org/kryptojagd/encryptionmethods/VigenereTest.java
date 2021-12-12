package org.kryptojagd.logic.verschluesselungsverfahren;

import org.junit.jupiter.api.Test;
import org.kryptojagd.verschluesselungsverfahren.Vigenere;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The class contains test cases for the Vigenere encryption
 *
 * @author Leah Schlimm
 */
public class VigenereTest {


    //Decode-Methode kein Text

    /**
     * Tests the decryption method with an empty text string and a valid key "ABCDE"
     */
    @Test
    void testEmptyValidKeyDecode(){
        assertEquals("", Vigenere.decode("", "ABCDE"));
    }

    /**
     * Tests the decryption method with an empty text string and an invalid key "ABC%"
     */
    @Test
    void testEmptyUnvalidKeyDecode(){
        assertEquals("", Vigenere.decode("", "ABC%"));
    }

    /**
     * Tests the decryption method with an empty text string, empty key string
     */
    @Test
    void testEmptyDecodeNoKey() {
        assertEquals("", Vigenere.decode("", ""));
    }

    /**
     * Tests the decryption method with an empty text string and a valid key "abCde" that is not completely written in uppercase
     */
    @Test
    void testEmptyValidKeyDecodeNoUppercaseKey() {
        assertEquals("", Vigenere.decode("", "abCde"));
    }

    /**
     * Tests the decryption method with an empty text string and a valid key in lowercase "abcde"
     */
    @Test
    void testEmptyValidKeyDecodeLowercaseKey() {
        assertEquals("", Vigenere.decode("", "abcde"));
    }



    //Encode-Methode kein Text

    /**
     * Tests the encryption method with an empty text string and without a key
     */
    @Test
    void testEmptyNoKeyEncode(){
        assertEquals("", Vigenere.encode(""));
    }

    /**
     * Tests the encryption method with an empty text string and a valid key "ABCDE"
     */
    @Test
    void testEmptyValidKeyEncode(){
        assertEquals("", Vigenere.encode("", "ABCDE"));
    }

    /**
     * Tests the encryption method with an empty text string and an invalid key "ABC%"
     */
    @Test
    void testEmptyUnvalidKeyEncode(){
        assertEquals("", Vigenere.encode("", "ABC%"));
    }

    /**
     * Tests the encryption method with an empty text string and an empty key string
     */
    @Test
    void testEmptyEncodeNoKey() {
        assertEquals("", Vigenere.encode("", ""));
    }

    /**
     * Tests the encryption method with an empty text string and a valid key "abCde" that is not completely written in uppercase
     */
    @Test
    void testEmptyValidKeyEncodeNoUppercaseKey() { assertEquals("", Vigenere.encode("", "abCde")); }

    /**
     * Tests the encryption method with an empty text string and a valid key in lowercase "abcde"
     */
    @Test
    void testEmptyValidKeyEncodeLowercaseKey() { assertEquals("", Vigenere.encode("", "abcde")); }



    //Decode-Methode mit Key und Text

    /**
     * Tests whether the decryption method with "HBNOS XGOX!" and the key "ABCDE" provides the correct decryption
     */
    @Test
    void testTextValidKeyDecode(){
        assertEquals("HALLO WELT!", Vigenere.decode("HBNOS XGOX!", "ABCDE"));
    }

    /**
     * Tests whether the decryption method with "Hbnos Xgox!" and the key "ABCDE" provides the correct decryption in uppercase
     */
    @Test
    void testTextValidKeyDecodeUppercase(){
        assertEquals("HALLO WELT!", Vigenere.decode("Hbnos Xgox!", "ABCDE"));
    }

    /**
     * Tests whether the decryption method with "Hbnos Xgox!" and the invalid key "ABC%" returns the encrypted entry in uppercase
     */
    @Test
    void testTextUnvalidKeyDecode(){
        assertEquals("HBNOS XGOX!", Vigenere.decode("Hbnos Xgox!", "ABC%"));
    }

    /**
     * Tests whether the decryption method with "Hbnos Xgox!" and returns the encrypted input in Uppercase to the empty key string
     */
    @Test
    void testTextDecodeNoKey() {
        assertEquals("HBNOS XGOX!", Vigenere.decode("Hbnos Xgox!", ""));
    }

    /**
     * Tests whether the decryption method with "Hbnos Xgox!" and the key "abCde" provides the correct decryption in uppercase
     */
    @Test
    void testTextValidKeyDecodeNoUppercaseKey() {
        assertEquals("HALLO WELT!", Vigenere.decode("Hbnos Xgox!", "abCde"));
    }

    /**
     * Tests whether the decryption method with "Hbnos Xgox!" and the key "abcde" provides the correct decryption in uppercase
     */
    @Test
    void testTextValidKeyDecodeLowercaseKey() {
        assertEquals("HALLO WELT!", Vigenere.decode("Hbnos Xgox!", "abcde"));
    }

    /**
     * Tests whether the cyclical shifting works
     */
    @Test
    void testTextValidKeyDecodeOverflow(){
        assertEquals("ZUM BEISPIEL:", Vigenere.decode("Awp Gfkvtnfn:", "BCDEF"));
    }



    //Encode-Methode mit Key und Text

    /**
     * Tests whether the decryption method with "HALLO WELT!" and the key "ABCDE" provides the correct encryption
     */
    @Test
    void testTextValidKeyEncode(){
        assertEquals("HBNOS XGOX!", Vigenere.encode("Hallo Welt!", "ABCDE"));
    }

    /**
     * Tests whether the encryption method with "Hallo Welt!" and the key "ABCDE" provides the correct encryption in uppercase
     */
    @Test
    void testTextValidKeyEncodeUppercase(){
        assertEquals("HBNOS XGOX!", Vigenere.encode("Hallo Welt!", "ABCDE"));
    }

    /**
     * Tests whether the encryption method with "Hallo Welt!" and the invalid key "ABC%" returns the unencrypted input in uppercase
     */
    @Test
    void testTextUnvalidKeyEncode() {
        assertEquals("HALLO WELT!", Vigenere.encode("Hallo Welt!", "ABC%"));
    }

    /**
     * Tests whether the encryption method with "Hallo Welt!" and returns the unencrypted input in uppercase to the empty key string
     */
    @Test
    void testTextEncodeNoKey() {
        assertEquals("HALLO WELT!", Vigenere.encode("Hallo Welt!", ""));
    }

    /**
     * Tests whether the encryption method with "Hallo Welt!" and the key "abCde" provides the correct encryption in uppercase
     */
    @Test
    void testTextValidKeyEncodeNoUppercaseKey(){
        assertEquals("HBNOS XGOX!", Vigenere.encode("Hallo Welt!", "abCde"));
    }

    /**
     * Tests whether the encryption method with "Hallo Welt!" and the key "abcde" provides the correct encryption in uppercase
     */
    @Test
    void testTextValidKeyEncodeLowercaseKey(){
        assertEquals("HBNOS XGOX!", Vigenere.encode("Hallo Welt!", "abcde"));
    }

    /**
     * Tests whether the cyclical shifting works
     */
    @Test
    void testTextValidKeyEncodeOverflow(){
        assertEquals("AWP GFKVTNFN:", Vigenere.encode("Zum Beispiel:", "BCDEF"));
    }



    // Key-Generierung

    /**
     * Tests whether the method generates a random int value between really greater than 3 and really less than 7
     */
    @Test
    void testGenerateKeyLength(){
        for (int i = 0; i < 1000; i++){
            int keyLength = Vigenere.keyLength();
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
            int keySymbolIndex = Vigenere.keySymbolIndex();
            if(keySymbolIndex < 0  || keySymbolIndex >= 26){
                fail();
            }
        }
        assertTrue(true);
    }
}
