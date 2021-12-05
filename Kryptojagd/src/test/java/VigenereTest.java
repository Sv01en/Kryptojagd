import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VigenereTest {


    //Decode-Methode kein Text

    @Test
    void testEmptyValidKeyDecode(){
        assertEquals("", Vigenere.decode("", "ABCDE"));
    }


    @Test
    void testEmptyUnvalidKeyDecode(){
        assertEquals("", Vigenere.decode("", "ABC%"));
    }

    @Test
    void testEmptyDecodeNoKey() {
        assertEquals("", Vigenere.decode("", ""));
    }

    @Test
    void testEmptyValidKeyDecodeNoUppercaseKey() {
        assertEquals("", Vigenere.decode("", "abCde"));
    }

    @Test
    void testEmptyValidKeyDecodeLowercaseKey() {
        assertEquals("", Vigenere.decode("", "abcde"));
    }



    //Encode-Methode kein Text

    @Test
    void testEmptyNoKeyEncode(){
        assertEquals("", Vigenere.encode(""));
    }

    @Test
    void testEmptyValidKeyEncode(){
        assertEquals("", Vigenere.encode("", "ABCDE"));
    }

    @Test
    void testEmptyUnvalidKeyEncode(){
        assertEquals("", Vigenere.encode("", "ABC%"));
    }

    @Test
    void testEmptyEncodeNoKey() {
        assertEquals("", Vigenere.encode("", ""));
    }

    @Test
    void testEmptyValidKeyEncodeNoUppercaseKey() { assertEquals("", Vigenere.encode("", "abCde")); }

    @Test
    void testEmptyValidKeyEncodeLowercaseKey() { assertEquals("", Vigenere.encode("", "abcde")); }



    //Decode-Methode mit Key und Text

    @Test
    void testTextValidKeyDecode(){
        assertEquals("HALLO WELT!", Vigenere.decode("HBNOS XGOX!", "ABCDE"));
    }

    @Test
    void testTextValidKeyDecodeUppercase(){
        assertEquals("HALLO WELT!", Vigenere.decode("Hbnos Xgox!", "ABCDE"));
    }

    @Test
    void testTextUnvalidKeyDecode(){
        assertEquals("HBNOS XGOX!", Vigenere.decode("Hbnos Xgox!", "ABC%"));
    }

    @Test
    void testTextDecodeNoKey() {
        assertEquals("HBNOS XGOX!", Vigenere.decode("Hbnos Xgox!", ""));
    }

    @Test
    void testTextValidKeyDecodeNoUppercaseKey() {
        assertEquals("HALLO WELT!", Vigenere.decode("Hbnos Xgox!", "abCde"));
    }

    @Test
    void testTextValidKeyDecodeLowercaseKey() {
        assertEquals("HALLO WELT!", Vigenere.decode("Hbnos Xgox!", "abcde"));
    }

    @Test
    void testTextValidKeyDecodeOverflow(){
        assertEquals("ZUM BEISPIEL:", Vigenere.decode("Awp Gfkvtnfn:", "BCDEF"));
    }



    //Encode-Methode mit Key und Text

    @Test
    void testTextValidKeyEncode(){
        assertEquals("HBNOS XGOX!", Vigenere.encode("Hallo Welt!", "ABCDE"));
    }

    @Test
    void testTextValidKeyEncodeUppercase(){
        assertEquals("HBNOS XGOX!", Vigenere.encode("Hallo Welt!", "ABCDE"));
    }

    @Test
    void testTextUnvalidKeyEncode() {
        assertEquals("HALLO WELT!", Vigenere.encode("Hallo Welt!", "ABC%"));
    }

    @Test
    void testTextEncodeNoKey() {
        assertEquals("HALLO WELT!", Vigenere.encode("Hallo Welt!", ""));
    }

    @Test
    void testTextValidKeyEncodeNoUppercaseKey(){
        assertEquals("HBNOS XGOX!", Vigenere.encode("Hallo Welt!", "abCde"));
    }

    @Test
    void testTextValidKeyEncodeLowercaseKey(){
        assertEquals("HBNOS XGOX!", Vigenere.encode("Hallo Welt!", "abcde"));
    }


    @Test
    void testTextValidKeyEncodeOverflow(){
        assertEquals("AWP GFKVTNFN:", Vigenere.encode("Zum Beispiel:", "BCDEF"));
    }



    // Key-Generierung

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
