package org.kryptojagd.logic.verschluesselungsverfahren;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Die Klasse enthaelt Testfaelle fuer die Beaufort-Verschluesselung
 *
 * @author Leah Schlimm
 */
public class BeaufortTest {


    //Decode-Methode kein Text

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String und gueltigem Schluessel "ABCDE"
     */
    @Test
    void testEmptyValidKeyDecode(){
        assertEquals("", Beaufort.decode("", "ABCDE"));
    }

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String und ungueltigem Schluessel "ABC%"
     */
    @Test
    void testEmptyUnvalidKeyDecode(){
        assertEquals("", Beaufort.decode("", "ABC%"));
    }

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String leerem Schluessel-String
     */
    @Test
    void testEmptyDecodeNoKey() {
        assertEquals("", Beaufort.decode("", ""));
    }

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String und gueltigem, nicht komplett in Uppercase geschriebenen Schluessel "abCde"
     */
    @Test
    void testEmptyValidKeyDecodeNoUppercaseKey() {
        assertEquals("", Beaufort.decode("", "abCde"));
    }

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String und gueltigem Schluessel in Lowercase "abcde"
     */
    @Test
    void testEmptyValidKeyDecodeLowercaseKey() {
        assertEquals("", Beaufort.decode("", "abcde"));
    }



    //Encode-Methode kein Text

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und ohne Schluessel
     */
    @Test
    void testEmptyNoKeyEncode(){
        assertEquals("", Beaufort.encode(""));
    }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und gueltigem Schluessel "ABCDE"
     */
    @Test
    void testEmptyValidKeyEncode(){
        assertEquals("", Beaufort.encode("", "ABCDE"));
    }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und ungueltigem Schluessel "ABC%"
     */
    @Test
    void testEmptyUnvalidKeyEncode(){
        assertEquals("", Beaufort.encode("", "ABC%"));
    }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und leerem Schluessel-String
     */
    @Test
    void testEmptyEncodeNoKey() {
        assertEquals("", Beaufort.encode("", ""));
    }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und gueltigem, nicht komplett in Uppercase geschriebenen Schluessel "abCde"
     */
    @Test
    void testEmptyValidKeyEncodeNoUppercaseKey() { assertEquals("", Beaufort.encode("", "abCde")); }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und gueltigem Schluessel in Lowercase "abcde"
     */
    @Test
    void testEmptyValidKeyEncodeLowercaseKey() { assertEquals("", Beaufort.encode("", "abcde")); }



    //Decode-Methode mit Key und Text

    /**
     * Testet, ob die Entschluesselungs-Methode mit "SYMLH CTLC!" und dem Schluessel "ABCDE" die korrekte Entschluesselung liefert
     */
    @Test
    void testTextValidKeyDecode(){
        assertEquals("HALLO WELT!", Beaufort.decode("SYMLH CTLC!", "ABCDE"));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode mit "Symlh Ctlc!" und dem Schluessel "ABCDE" die korrekte Entschluesselung in Uppercase liefert
     */
    @Test
    void testTextValidKeyDecodeUppercase(){
        assertEquals("HALLO WELT!", Beaufort.decode("Symlh Ctlc!", "ABCDE"));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode mit "Symlh Ctlc!" und dem ungültigen Schluessel "ABC%" die verschluesselte Eingabe in Uppercase liefert
     */
    @Test
    void testTextUnvalidKeyDecode(){
        assertEquals("SYMLH CTLC!", Beaufort.decode("Symlh Ctlc!", "ABC%"));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode mit "Symlh Ctlc!" und dem leeren Schluessel-String die verschluesselte Eingabe in Uppercase liefert
     */
    @Test
    void testTextDecodeNoKey() {
        assertEquals("HALLO WELT!", Beaufort.decode("Hallo Welt!", ""));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode mit "Symlh Ctlc!" und dem Schluessel "abCde" die korrekte Entschluesselung in Uppercase liefert
     */
    @Test
    void testTextValidKeyDecodeNoUppercaseKey() {
        assertEquals("HALLO WELT!", Beaufort.decode("Symll Bsof!", "abCd"));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode mit "Symlh Ctlc!" und dem Schluessel "abcde" die korrekte Entschluesselung in Uppercase liefert
     */
    @Test
    void testTextValidKeyDecodeLowercaseKey() {
        assertEquals("HALLO WELT!", Beaufort.decode("Symll Bsof!", "abcd"));
    }

    /**
     * Testet, ob das zyklische shiften funktioniert
     */
    @Test
    void testTextValidKeyDecodeOverflow(){
        assertEquals("HALLO WELT!", Beaufort.decode("ZVWVS LCVC!", "TEST"));
    }



    //Encode-Methode mit Key und Text

    /**
     * Testet, ob die Entschluesselungs-Methode mit "SYMLH CTLC!" und dem Schluessel "ABCDE" die korrekte Verschluesselung liefert
     */
    @Test
    void testTextValidKeyEncode(){
        assertEquals("HALLO WELT!", Beaufort.encode("SYMLH CTLC!", "ABCDE"));
    }

    /**
     * Testet, ob die Verschluesselungs-Methode mit "Symlh Ctlc!" und dem Schluessel "ABCDE" die korrekte Verschluesselung in Uppercase liefert
     */
    @Test
    void testTextValidKeyEncodeUppercase(){
        assertEquals("HALLO WELT!", Beaufort.encode("Symlh Ctlc!", "ABCDE"));
    }

    /**
     * Testet, ob die Verschluesselungs-Methode mit "Symlh Ctlc!" und dem ungültigen Schluessel "ABC%" die unverschluesselte Eingabe in Uppercase liefert
     */
    @Test
    void testTextUnvalidKeyEncode() {
        assertEquals("SYMLH CTLC!", Beaufort.encode("Symlh Ctlc!", "ABC%"));
    }

    /**
     * Testet, ob die Verschluesselungs-Methode mit "Symlh Ctlc!" und dem leeren Schluessel-String die unverschluesselte Eingabe in Uppercase liefert
     */
    @Test
    void testTextEncodeNoKey() {
        assertEquals("SYMLH CTLC!", Beaufort.encode("Symlh Ctlc!", ""));
    }

    /**
     * Testet, ob die Verschluesselungs-Methode mit "Symlh Ctlc!" und dem Schluessel "abCde" die korrekte Verschluesselung in Uppercase liefert
     */
    @Test
    void testTextValidKeyEncodeNoUppercaseKey(){
        assertEquals("HALLO WELT!", Beaufort.encode("Symll Bsof!", "abCd"));
    }

    /**
     * Testet, ob die Verschluesselungs-Methode mit "Symlh Ctlc!" und dem Schluessel "abcde" die korrekte Verschluesselung in Uppercase liefert
     */
    @Test
    void testTextValidKeyEncodeLowercaseKey(){
        assertEquals("HALLO WELT!", Beaufort.encode("Symll Bsof!", "abcd"));
    }

    /**
     * Testet, ob das zyklische shiften funktioniert
     */
    @Test
    void testTextValidKeyEncodeOverflow(){
        assertEquals("ZVWVS LCVC!", Beaufort.encode("HALLO WELT!", "TEST"));
    }



    // Key-Generierung

    /**
     * Testet, ob bei der Methode ein zufaelligen int-Wert zwischen echt groesser 3 und echt kleiner 7 generiert wird
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
     * Testet, ob bei der Methode ein zufaelligen int-Wert zwischen groeser gleich 0 und echt kleiner 26 generiert wird
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
