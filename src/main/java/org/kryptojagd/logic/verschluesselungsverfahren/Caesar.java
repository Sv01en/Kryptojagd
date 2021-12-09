package org.kryptojagd.logic.verschluesselungsverfahren;

/**
 * Stellt Methoden zum ver- und entschluesseln von Texten mit der Caesar-Verschluesselung bereit
 *
 * @author Leah Schlimm
 */
 public class Caesar implements EncryptionInterface {

    /**
     * Generiert einen zufaelligen Zahl zwischen 1 und 25
     * @return Zufaelliger int Wert
     */
     public static int generateKey () {

        return (int) (Math.random() * (26 - 1) + 1);

    }

    /**
     * Verschluesselt einen String mit einem zufaellig generierten Schluessel
     * @param text Zu verschluesselnder Text
     * @return Verschluesselter Text in uppercase
     */
    public static String encode (String text) {

        // key zwischen [1,26), damit 0 nicht als Schluessel verwendet wird
        int key = generateKey();

        return encode(text, key);
    }

    /**
     * Verschluesselt einen String mit einem gegebenen Schluessel
     * @param text Zu verschluesselnder Text
     * @param key Uebergebener Schluessel
     * @return Verschluesselter Text in uppercase
     */
    public static String encode (String text, int key) {

        if (key < 0 || key > 26) {
            return text.toUpperCase();
        }

        char[] textCharArr;
        String encodedText = "";

        text = text.toUpperCase();
        textCharArr = text.toCharArray();

        for (int i = 0; i < textCharArr.length; i++) {
            encodedText = encodedText + shift(textCharArr[i], key);
        }
        return encodedText;
    }

    /**
     * Entschluesselt einen Verschluesselten Text mit dem dazugehoerigen Schluessel
     * @param text Zu entschluesselnder Text
     * @param key Uebergebener Schluessel
     * @return Entschluesselter Text in uppercase
     */
    public static String decode (String text, int key) {
        
        if (key < 0 || key > 26) {
            return text.toUpperCase();
        }

        char[] textCharArr;
        String decodedText = "";

        text = text.toUpperCase();
        textCharArr = text.toCharArray();

        for (int i = 0; i < textCharArr.length; i++) {
            decodedText = decodedText + shift(textCharArr[i], 26 - key);
        }
        return decodedText;
    }

    /**
     * Shiftet ein gegebenen char mit dem gegebenen Schluessel zyklisch entlang des uppercase ASCII Alphabets.
     * @param symbol Beliebiges char
     * @param key Uebergebener Schluessel
     * @return Geschifftetes char falls es im uppercase ASCII Alphabet ist und sonst das ungeshifftete char symbol
     */
    private static char shift (char symbol, int key) {
        
        if (symbol >= 'A' && symbol <= 'Z') {
            symbol = (char) (symbol + key);
            
            if (symbol > 'Z') {
                symbol = (char) (symbol - 26);
            }
        }

        return symbol;
    }
}