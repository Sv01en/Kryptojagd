import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BeaufortTest {


    //Decode-Methode kein Text

    @Test
    void testEmptyValidKeyDecode(){
        assertEquals("", Beaufort.decode("", "ABCDE"));
    }


    @Test
    void testEmptyUnvalidKeyDecode(){
        assertEquals("", Beaufort.decode("", "ABC%"));
    }

    @Test
    void testEmptyDecodeNoKey() {
        assertEquals("", Beaufort.decode("", ""));
    }

    @Test
    void testEmptyValidKeyDecodeNoUppercaseKey() {
        assertEquals("", Beaufort.decode("", "abCde"));
    }

    @Test
    void testEmptyValidKeyDecodeLowercaseKey() {
        assertEquals("", Beaufort.decode("", "abcde"));
    }



    //Encode-Methode kein Text

    @Test
    void testEmptyNoKeyEncode(){
        assertEquals("", Beaufort.encode(""));
    }

    @Test
    void testEmptyValidKeyEncode(){
        assertEquals("", Beaufort.encode("", "ABCDE"));
    }

    @Test
    void testEmptyUnvalidKeyEncode(){
        assertEquals("", Beaufort.encode("", "ABC%"));
    }

    @Test
    void testEmptyEncodeNoKey() {
        assertEquals("", Beaufort.encode("", ""));
    }

    @Test
    void testEmptyValidKeyEncodeNoUppercaseKey() { assertEquals("", Beaufort.encode("", "abCde")); }

    @Test
    void testEmptyValidKeyEncodeLowercaseKey() { assertEquals("", Beaufort.encode("", "abcde")); }



    //Decode-Methode mit Key und Text

    @Test
    void testTextValidKeyDecode(){
        assertEquals("HALLO WELT!", Beaufort.decode("SYMLH CTLC!", "ABCDE"));
    }

    @Test
    void testTextValidKeyDecodeUppercase(){
        assertEquals("HALLO WELT!", Beaufort.decode("Symlh Ctlc!", "ABCDE"));
    }

    @Test
    void testTextUnvalidKeyDecode(){
        assertEquals("SYMLH CTLC!", Beaufort.decode("Symlh Ctlc!", "ABC%"));
    }

    @Test
    void testTextDecodeNoKey() {
        assertEquals("HALLO WELT!", Beaufort.decode("Hallo Welt!", ""));
    }

    @Test
    void testTextValidKeyDecodeNoUppercaseKey() {
        assertEquals("HALLO WELT!", Beaufort.decode("Symll Bsof!", "abCd"));
    }

    @Test
    void testTextValidKeyDecodeLowercaseKey() {
        assertEquals("HALLO WELT!", Beaufort.decode("Symll Bsof!", "abcd"));
    }

    @Test
    void testTextValidKeyDecodeOverflow(){
        assertEquals("HALLO WELT!", Beaufort.decode("ZVWVS LCVC!", "TEST"));
    }



    //Encode-Methode mit Key und Text

    @Test
    void testTextValidKeyEncode(){
        assertEquals("HALLO WELT!", Beaufort.encode("SYMLH CTLC!", "ABCDE"));
    }

    @Test
    void testTextValidKeyEncodeUppercase(){
        assertEquals("HALLO WELT!", Beaufort.encode("Symlh Ctlc!", "ABCDE"));
    }

    @Test
    void testTextUnvalidKeyEncode() {
        assertEquals("SYMLH CTLC!", Beaufort.encode("Symlh Ctlc!", "ABC%"));
    }

    @Test
    void testTextEncodeNoKey() {
        assertEquals("SYMLH CTLC!", Beaufort.encode("Symlh Ctlc!", ""));
    }

    @Test
    void testTextValidKeyEncodeNoUppercaseKey(){
        assertEquals("HALLO WELT!", Beaufort.encode("Symll Bsof!", "abCd"));
    }

    @Test
    void testTextValidKeyEncodeLowercaseKey(){
        assertEquals("HALLO WELT!", Beaufort.encode("Symll Bsof!", "abcd"));
    }

    @Test
    void testTextValidKeyEncodeOverflow(){
        assertEquals("ZVWVS LCVC!", Beaufort.encode("HALLO WELT!", "TEST"));
    }



    // Key-Generierung

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
