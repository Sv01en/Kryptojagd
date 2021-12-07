package org.kryptojagd.logic.verschluesselungsverfahren;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Die Klasse enthaelt Testfaelle fuer die Caesar-Verschluesselung
 *
 * @author Leah Schlimm
 */
public class CaesarTest {

    //Decode-Methode kein Text

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String und gueltigem Schluessel 5
     */
    @Test
    void testEmptyValidKeyDecode(){
        assertEquals("", Caesar.decode("", 5));
    }

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String und dem kleinsten gueltigem Schluessel 0
     */
    @Test
    void testEmptyKeyDecodeLowerBorder(){
        assertEquals("", Caesar.decode("", 0));
    }

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String und dem groessten gueltigem Schluessel 26
     */
    @Test
    void testEmptyKeyDecodeUpperBorder(){
        assertEquals("", Caesar.decode("", 26));
    }

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String und einem zu kleinen Schluessel -1
     */
    @Test
    void testEmptyUnvalidKeyDecodeToLow(){
        assertEquals("", Caesar.decode("", -1));
    }

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String und einem zu grossen Schluessel -1
     */
    @Test
    void testEmptyUnvalidKeyDecodeToHigh() {
        assertEquals("", Caesar.decode("", 27));
    }



    //Encode-Methode Empty Key und kein Text

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und keinem Schluessel
     */
    @Test
    void testEmptyNoKeyEncode(){
        assertEquals("", Caesar.encode(""));
    }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und gueltigem Schluessel 5
     */
    @Test
    void testEmptyValidKeyEncode(){
        assertEquals("", Caesar.encode("", 5));
    }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und dem kleinsten gueltigem Schluessel 0
     */
    @Test
    void testEmptyKeyEncodeLowerBorder(){
        assertEquals("", Caesar.encode("", 0));
    }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und dem groessten gueltigem Schluessel 26
     */
    @Test
    void testEmptyKeyEncodeUpperBorder(){
        assertEquals("", Caesar.encode("", 26));
    }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und einem zu kleinen Schluessel -1
     */
    @Test
    void testEmptyUnvalidKeyEncodeToLow(){
        assertEquals("", Caesar.encode("", -1));
    }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und einem zu grossen Schluessel -1
     */
    @Test
    void testEmptyUnvalidKeyEncodeToHigh(){
        assertEquals("", Caesar.encode("", 27));
    }



    //Decode-Methode mit Key und Text

    /**
     * Testet, ob die Entschluesselungs-Methode den Text "IBMMP XFMU!" korrekt entschluesselt
     */
    @Test
    void testTextValidKeyDecode(){
        assertEquals("HALLO WELT!", Caesar.decode("IBMMP XFMU!", 1));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode den Text "Ibmmp Xfmu!" korrekt entschluesselt und in Uppercase ausgibt
     */
    @Test
    void testTextValidKeyDecodeUppercase(){
        assertEquals("HALLO WELT!", Caesar.decode("Ibmmp Xfmu!", 1));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode den Text "Hallo Welt!" mit dem Schluessel 0 entschluesselt und in Uppercase ausgibt
     */
    @Test
    void testTextValidKeyDecodeUppercaseLowerBorder(){
        assertEquals("HALLO WELT!", Caesar.decode("Hallo Welt!", 0));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode den Text "Hallo Welt!" mit dem Schluessel 26 entschluesselt und in Uppercase ausgibt
     */
    @Test
    void testTextValidKeyDecodeUppercaseUpperBorder(){
        assertEquals("HALLO WELT!", Caesar.decode("Hallo Welt!", 26));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode den gegebenen Text immer noch verschluesselt und in Uppercase ausgibt
     */
    @Test
    void testTextUnvalidKeyDecodeToLow(){
        assertEquals("IBMMP XFMU!", Caesar.decode("Ibmmp Xfmu!", -1));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode den gegebenen Text immer noch verschluesselt und in Uppercase ausgibt
     */
    @Test
    void testTextUnvalidKeyDecodeToHigh(){
        assertEquals("IBMMP XFMU!", Caesar.decode("Ibmmp Xfmu!", 27));
    }




    //Encode-Methode mit Key und Text

    /**
     * Testet, ob die Entschluesselungs-Methode den Text "HALLO WELT!" korrekt verschluesselt
     */
    @Test
    void testTextValidKeyEncode(){
        assertEquals("IBMMP XFMU!", Caesar.encode("HALLO WELT!", 1));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode den Text "Hallo Welt!" korrekt verschluesselt und in Uppercase ausgibt
     */
    @Test
    void testTextValidKeyEncodeUppercase(){
        assertEquals("IBMMP XFMU!", Caesar.encode("Hallo Welt!", 1));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode den Text "Hallo Welt!" mit dem Schluessel 0 verschluesselt und in Uppercase ausgibt
     */
    @Test
    void testTextValidKeyEncodeUppercaseLowerBorder(){
        assertEquals("HALLO WELT!", Caesar.encode("Hallo Welt!", 0));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode den Text "Hallo Welt!" mit dem Schluessel 26 verschluesselt und in Uppercase ausgibt
     */
    @Test
    void testTextValidKeyEncodeUppercaseUpperBorder(){
        assertEquals("HALLO WELT!", Caesar.encode("Hallo Welt!", 26));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode den gegebenen Text unverschluesselt und in Uppercase ausgibt
     */
    @Test
    void testTextUnvalidKeyEncodeToLow(){
        assertEquals("HALLO WELT!", Caesar.encode("Hallo Welt!", -1));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode den gegebenen Text unverschluesselt und in Uppercase ausgibt
     */
    @Test
    void testTextUnvalidKeyEncodeToHigh(){
        assertEquals("HALLO WELT!", Caesar.encode("Hallo Welt!", 27));
    }



    // Key-Generierung

    /**
     * Testet, ob die Methode einen zufaelligen int-Wert zwischen echt groesser 0 und echt kleiner 25 generiert
     */
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
