package org.kryptojagd.logic.verschluesselungsverfahren;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Die Klasse enthaelt Testfaelle fuer die Vigenere-Verschluesselung
 *
 * @author Leah Schlimm
 */
public class VigenereTest {


    //Decode-Methode kein Text

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String und gueltigem Schluessel "ABCDE"
     */
    @Test
    void testEmptyValidKeyDecode(){
        assertEquals("", Vigenere.decode("", "ABCDE"));
    }

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String und ungueltigem Schluessel "ABC%"
     */
    @Test
    void testEmptyUnvalidKeyDecode(){
        assertEquals("", Vigenere.decode("", "ABC%"));
    }

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String leerem Schluessel-String
     */
    @Test
    void testEmptyDecodeNoKey() {
        assertEquals("", Vigenere.decode("", ""));
    }

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String und gueltigem, nicht komplett in Uppercase geschriebenen Schluessel "abCde"
     */
    @Test
    void testEmptyValidKeyDecodeNoUppercaseKey() {
        assertEquals("", Vigenere.decode("", "abCde"));
    }

    /**
     * Testet die Entschluesselungs-Methode mit leerem Text-String und gueltigem Schluessel in Lowercase "abcde"
     */
    @Test
    void testEmptyValidKeyDecodeLowercaseKey() {
        assertEquals("", Vigenere.decode("", "abcde"));
    }



    //Encode-Methode kein Text

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und ohne Schluessel
     */
    @Test
    void testEmptyNoKeyEncode(){
        assertEquals("", Vigenere.encode(""));
    }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und gueltigem Schluessel "ABCDE"
     */
    @Test
    void testEmptyValidKeyEncode(){
        assertEquals("", Vigenere.encode("", "ABCDE"));
    }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und ungueltigem Schluessel "ABC%"
     */
    @Test
    void testEmptyUnvalidKeyEncode(){
        assertEquals("", Vigenere.encode("", "ABC%"));
    }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und leerem Schluessel-String
     */
    @Test
    void testEmptyEncodeNoKey() {
        assertEquals("", Vigenere.encode("", ""));
    }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und gueltigem, nicht komplett in Uppercase geschriebenen Schluessel "abCde"
     */
    @Test
    void testEmptyValidKeyEncodeNoUppercaseKey() { assertEquals("", Vigenere.encode("", "abCde")); }

    /**
     * Testet die Verschluesselungs-Methode mit leerem Text-String und gueltigem Schluessel in Lowercase "abcde"
     */
    @Test
    void testEmptyValidKeyEncodeLowercaseKey() { assertEquals("", Vigenere.encode("", "abcde")); }



    //Decode-Methode mit Key und Text

    /**
     * Testet, ob die Entschluesselungs-Methode mit "HBNOS XGOX!" und dem Schluessel "ABCDE" die korrekte Entschluesselung liefert
     */
    @Test
    void testTextValidKeyDecode(){
        assertEquals("HALLO WELT!", Vigenere.decode("HBNOS XGOX!", "ABCDE"));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode mit "Hbnos Xgox!" und dem Schluessel "ABCDE" die korrekte Entschluesselung in Uppercase liefert
     */
    @Test
    void testTextValidKeyDecodeUppercase(){
        assertEquals("HALLO WELT!", Vigenere.decode("Hbnos Xgox!", "ABCDE"));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode mit "Hbnos Xgox!" und dem ungültigen Schluessel "ABC%" die verschluesselte Eingabe in Uppercase liefert
     */
    @Test
    void testTextUnvalidKeyDecode(){
        assertEquals("HBNOS XGOX!", Vigenere.decode("Hbnos Xgox!", "ABC%"));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode mit "Hbnos Xgox!" und dem leeren Schluessel-String die verschluesselte Eingabe in Uppercase liefert
     */
    @Test
    void testTextDecodeNoKey() {
        assertEquals("HBNOS XGOX!", Vigenere.decode("Hbnos Xgox!", ""));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode mit "Hbnos Xgox!" und dem Schluessel "abCde" die korrekte Entschluesselung in Uppercase liefert
     */
    @Test
    void testTextValidKeyDecodeNoUppercaseKey() {
        assertEquals("HALLO WELT!", Vigenere.decode("Hbnos Xgox!", "abCde"));
    }

    /**
     * Testet, ob die Entschluesselungs-Methode mit "Hbnos Xgox!" und dem Schluessel "abcde" die korrekte Entschluesselung in Uppercase liefert
     */
    @Test
    void testTextValidKeyDecodeLowercaseKey() {
        assertEquals("HALLO WELT!", Vigenere.decode("Hbnos Xgox!", "abcde"));
    }

    /**
     * Testet, ob das zyklische shiften funktioniert
     */
    @Test
    void testTextValidKeyDecodeOverflow(){
        assertEquals("ZUM BEISPIEL:", Vigenere.decode("Awp Gfkvtnfn:", "BCDEF"));
    }



    //Encode-Methode mit Key und Text

    /**
     * Testet, ob die Entschluesselungs-Methode mit "HALLO WELT!" und dem Schluessel "ABCDE" die korrekte Verschluesselung liefert
     */
    @Test
    void testTextValidKeyEncode(){
        assertEquals("HBNOS XGOX!", Vigenere.encode("Hallo Welt!", "ABCDE"));
    }

    /**
     * Testet, ob die Verschluesselungs-Methode mit "Hallo Welt!" und dem Schluessel "ABCDE" die korrekte Verschluesselung in Uppercase liefert
     */
    @Test
    void testTextValidKeyEncodeUppercase(){
        assertEquals("HBNOS XGOX!", Vigenere.encode("Hallo Welt!", "ABCDE"));
    }

    /**
     * Testet, ob die Verschluesselungs-Methode mit "Hallo Welt!" und dem ungültigen Schluessel "ABC%" die unverschluesselte Eingabe in Uppercase liefert
     */
    @Test
    void testTextUnvalidKeyEncode() {
        assertEquals("HALLO WELT!", Vigenere.encode("Hallo Welt!", "ABC%"));
    }

    /**
     * Testet, ob die Verschluesselungs-Methode mit "Hallo Welt!" und dem leeren Schluessel-String die unverschluesselte Eingabe in Uppercase liefert
     */
    @Test
    void testTextEncodeNoKey() {
        assertEquals("HALLO WELT!", Vigenere.encode("Hallo Welt!", ""));
    }

    /**
     * Testet, ob die Verschluesselungs-Methode mit "Hallo Welt!" und dem Schluessel "abCde" die korrekte Verschluesselung in Uppercase liefert
     */
    @Test
    void testTextValidKeyEncodeNoUppercaseKey(){
        assertEquals("HBNOS XGOX!", Vigenere.encode("Hallo Welt!", "abCde"));
    }

    /**
     * Testet, ob die Verschluesselungs-Methode mit "Hallo Welt!" und dem Schluessel "abcde" die korrekte Verschluesselung in Uppercase liefert
     */
    @Test
    void testTextValidKeyEncodeLowercaseKey(){
        assertEquals("HBNOS XGOX!", Vigenere.encode("Hallo Welt!", "abcde"));
    }

    /**
     * Testet, ob das zyklische shiften funktioniert
     */
    @Test
    void testTextValidKeyEncodeOverflow(){
        assertEquals("AWP GFKVTNFN:", Vigenere.encode("Zum Beispiel:", "BCDEF"));
    }



    // Key-Generierung

    /**
     * Testet, ob bei der Methode ein zufaelligen int-Wert zwischen echt groesser 3 und echt kleiner 7 generiert wird
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
     * Testet, ob bei der Methode ein zufaelligen int-Wert zwischen groesser gleich 0 und echt kleiner 26 generiert wird
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
