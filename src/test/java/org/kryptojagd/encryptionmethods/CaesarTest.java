package org.kryptojagd.encryptionmethods;

import org.junit.jupiter.api.Test;
import org.kryptojagd.encryptionmethods.Caesar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The class contains test cases for the Caesar encryption
 *
 * @author Leah Schlimm
 */
/*public class CaesarTest {

    //Decode-Methode kein Text

    /**
     * Tests the decode method with an empty text string and a valid key 5
     */
    /*@Test
    void testEmptyValidKeyDecode(){
        assertEquals("", Caesar.decode("", 5));
    }

    /**
     * Tests the decode method with an empty text string and the smallest valid key 0
     */
    /*@Test
    void testEmptyKeyDecodeLowerBorder(){
        assertEquals("", Caesar.decode("", 0));
    }

    /**
     * Tests the decode method with an empty text string and the largest valid key 26
     */
    /*@Test
    void testEmptyKeyDecodeUpperBorder(){
        assertEquals("", Caesar.decode("", 26));
    }

    /**
     * Tests the decode method with an empty text string and a key that is too small, -1
     */
    /*@Test
    void testEmptyUnvalidKeyDecodeToLow(){
        assertEquals("", Caesar.decode("", -1));
    }

    /**
     * Tests the decode method with an empty text string and a key that is too large -1
     */
    /*@Test
    void testEmptyUnvalidKeyDecodeToHigh() {
        assertEquals("", Caesar.decode("", 27));
    }



    //Encode-Methode Empty Key und kein Text

    /**
     * Tests the encode method with an empty text string and no key
     */
    /*@Test
    void testEmptyNoKeyEncode(){
        assertEquals("", Caesar.encode(""));
    }

    /**
     * Tests the encode method with an empty text string and a valid key 5
     */
    /*@Test
    void testEmptyValidKeyEncode(){
        assertEquals("", Caesar.encode("", 5));
    }

    /**
     * Tests the encode method with an empty text string and the smallest valid key 0
     */
    /*@Test
    void testEmptyKeyEncodeLowerBorder(){
        assertEquals("", Caesar.encode("", 0));
    }

    /**
     * Tests the encode method with an empty text string and the largest valid key 26
     */
    /*@Test
    void testEmptyKeyEncodeUpperBorder(){
        assertEquals("", Caesar.encode("", 26));
    }

    /**
     * Tests the encode method with an empty text string and a key that is too small -1
     */
    /*@Test
    void testEmptyUnvalidKeyEncodeToLow(){
        assertEquals("", Caesar.encode("", -1));
    }

    /**
     * Tests the encode method with an empty text string and a key that is too large -1
     */
    /*@Test
    void testEmptyUnvalidKeyEncodeToHigh(){
        assertEquals("", Caesar.encode("", 27));
    }



    //Decode-Methode mit Key und Text

    /**
     * Tests whether the decode method reads "IBMMP XFMU!" correctly deciphered
     */
    /*@Test
    void testTextValidKeyDecode(){
        assertEquals("HALLO WELT!", Caesar.decode("IBMMP XFMU!", 1));
    }

    /**
     * Tests whether the decode method reads "Ibmmp Xfmu!" correctly decrypted and output in uppercase
     */
    /*@Test
    void testTextValidKeyDecodeUppercase(){
        assertEquals("HALLO WELT!", Caesar.decode("Ibmmp Xfmu!", 1));
    }

    /**
     * Tests whether the decode method reads "Hallo Welt!" decrypted with key 0 and output in uppercase
     */
    /*@Test
    void testTextValidKeyDecodeUppercaseLowerBorder(){
        assertEquals("HALLO WELT!", Caesar.decode("Hallo Welt!", 0));
    }

    /**
     * Tests whether the decode method reads "Hallo Welt!" decrypted with key 26 and output in uppercase
     */
    /*@Test
    void testTextValidKeyDecodeUppercaseUpperBorder(){
        assertEquals("HALLO WELT!", Caesar.decode("Hallo Welt!", 26));
    }

    /**
     * Tests whether the decode method still encrypts the given text and outputs it in uppercase
     */
    /*@Test
    void testTextUnvalidKeyDecodeToLow(){
        assertEquals("IBMMP XFMU!", Caesar.decode("Ibmmp Xfmu!", -1));
    }

    /**
     * Tests whether the decode method still encrypts the given text and outputs it in uppercase
     */
    /*@Test
    void testTextUnvalidKeyDecodeToHigh(){
        assertEquals("IBMMP XFMU!", Caesar.decode("Ibmmp Xfmu!", 27));
    }




    //Encode-Methode mit Key und Text

    /**
     * Tests whether the encode method reads "HALLO WELT!" correctly encrypted
     */
    /*@Test
    void testTextValidKeyEncode(){
        assertEquals("IBMMP XFMU!", Caesar.encode("HALLO WELT!", 1));
    }

    /**
     * Tests whether the encode method reads "Hallo Welt!" correctly encrypted and output in uppercase
     */
    /*@Test
    void testTextValidKeyEncodeUppercase(){
        assertEquals("IBMMP XFMU!", Caesar.encode("Hallo Welt!", 1));
    }

    /**
     * Tests whether the encode method reads "Hallo Welt!" Encrypted with the key 0 and output in Uppercase
     */
    /*@Test
    void testTextValidKeyEncodeUppercaseLowerBorder(){
        assertEquals("HALLO WELT!", Caesar.encode("Hallo Welt!", 0));
    }

    /**
     * Tests whether the encode method reads "Hallo Welt!" encrypted with the key 26 and issued in the uppercase
     */
    /*@Test
    void testTextValidKeyEncodeUppercaseUpperBorder(){
        assertEquals("HALLO WELT!", Caesar.encode("Hallo Welt!", 26));
    }

    /**
     * Tests whether the encode method outputs the given text unencrypted and in uppercase
     */
    /*@Test
    void testTextUnvalidKeyEncodeToLow(){
        assertEquals("HALLO WELT!", Caesar.encode("Hallo Welt!", -1));
    }

    /**
     * Tests whether the encode method outputs the given text unencrypted and in uppercase
     */
    /*@Test
    void testTextUnvalidKeyEncodeToHigh(){
        assertEquals("HALLO WELT!", Caesar.encode("Hallo Welt!", 27));
    }



    // Key-Generierung

    /**
     * Tests whether the method generates a random int value between really greater than 0 and really less than 25
     */
    /*@Test
    void testGenerateKey(){
        for (int i = 0; i < 1000; i++){
            int key = Caesar.generateKey();
            if(key <= 0 || key >= 26){
                fail();
            }
        }
        assertTrue(true);
    }

}*/
