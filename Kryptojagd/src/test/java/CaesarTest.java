import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CaesarTest {

    //Decode-Methode Empty Key und kein Text

    @Test
    void testEmptyValidKeyDecode(){
        assertEquals("", Caesar.decode("", 5));
    }

    @Test
    void testEmptyKeyLowerDecodeBorder(){
        assertEquals("", Caesar.decode("", 0));
    }

    @Test
    void testEmptyKeyUpperDecodeBorder(){
        assertEquals("", Caesar.decode("", 26));
    }

    @Test
    void testEmptyUnvalidKeyDecodeToLow(){
        assertEquals("", Caesar.decode("", -1));
    }

    @Test
    void testEmptyUnvalidKeyDecodeToHigh() {
        assertEquals("", Caesar.decode("", 27));
    }



    //Encode-Methode Empty Key und kein Text
    @Test
    void testEmptyNoKeyEncode(){
        assertEquals("", Caesar.encode(""));
    }

    @Test
    void testEmptyValidKeyEncode(){
        assertEquals("", Caesar.encode("", 5));
    }

    @Test
    void testEmptyKeyLowerEncodeBorder(){
        assertEquals("", Caesar.encode("", 0));
    }

    @Test
    void testEmptyKeyUpperEncodeBorder(){
        assertEquals("", Caesar.encode("", 26));
    }

    @Test
    void testEmptyUnvalidKeyEncodeToLow(){
        assertEquals("", Caesar.encode("", -1));
    }

    @Test
    void testEmptyUnvalidKeyEncodeToHigh(){
        assertEquals("", Caesar.encode("", 27));
    }





    //Decode-Methode mit Key und Text

    @Test
    void testTextValidKeyDecode(){
        assertEquals("HALLO WELT!", Caesar.decode("IBMMP XFMU!", 1));
    }

    @Test
    void testTextValidKeyDecodeUppercase(){
        assertEquals("HALLO WELT!", Caesar.decode("Ibmmp Xfmu!", 1));
    }

    @Test
    void testTextValidKeyDecodeUppercaseLowerBorder(){
        assertEquals("HALLO WELT!", Caesar.decode("Hallo Welt!", 0));
    }

    @Test
    void testTextValidKeyDecodeUppercaseUpperBorder(){
        assertEquals("HALLO WELT!", Caesar.decode("Hallo Welt!", 26));
    }



    //Encode-Methode mit Key und Text
    @Test
    void testTextValidKeyEncode(){
        assertEquals("IBMMP XFMU!", Caesar.encode("HALLO WELT!", 1));
    }

    @Test
    void testTextValidKeyEncodeUppercase(){
        assertEquals("IBMMP XFMU!", Caesar.encode("Hallo Welt!", 1));
    }

    @Test
    void testTextValidKeyEncodeUppercaseLowerBorder(){
        assertEquals("HALLO WELT!", Caesar.encode("Hallo Welt!", 0));
    }

    @Test
    void testTextValidKeyEncodeUppercaseUpperBorder(){
        assertEquals("HALLO WELT!", Caesar.encode("Hallo Welt!", 26));
    }

    @Test
    void testTextUnvalidKeyEncodeToLow(){
        assertEquals("HALLO WELT!", Caesar.encode("Hallo Welt!", -1));
    }

    @Test
    void testTextUnvalidKeyEncodeToHigh(){
        assertEquals("HALLO WELT!", Caesar.encode("Hallo Welt!", 27));
    }



    // Key-Generierung

    @Test
    void testGenerateKey(){
        for (int i = 0; i < 1000; i++){
            int key = Caesar.generateKey();
            if(key <= 0 || key >= 26){
                fail();
            }
        }
        assertTrue(true);
    }

}
