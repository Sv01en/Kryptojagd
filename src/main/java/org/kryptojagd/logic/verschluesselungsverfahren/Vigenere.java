package org.kryptojagd.logic.verschluesselungsverfahren;

/**
 * Stellt Methoden zum ver- und entschluesseln von Texten mit der Vigenere-Verschluesselung bereit
 *
 * @author Leah Schlimm
 */
public class Vigenere {

    private final static char[] normalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * Generierte eine zufaellige Zahl zwischen 4 und 6
     * @return Zufaelliger int Wert
     */
    public static int keyLength () {

        return (int) (Math.random() * (7 - 4) + 4);
    }

    /**
     * Generiert eine zufaellige Zahl zwischen 0 und 25
     * @return Zufaelliger int Wert
     */
    public static int keySymbolIndex () {

        return (int) (Math.random() * (26 - 0) + 0);

    }

    /**
     * Verschluesselt einen String mit einem zufaellig generierten Schluessel
     * @param text Zu verschluesselnder Text
     * @return Verschluesselter Text in uppercase
     */
    public static String encode(String text) {
        String key = "";

        int keyLength = keyLength();
        for (int i = 0; i < keyLength; i++) {
            int symbolIndex = keySymbolIndex();
            key = key + normalAlphabet[symbolIndex];
        }

        return encode(text, key);
    }

    /**
     * Verschluesselt einen String mit einem gegebenen Schluessel
     * @param text Zu verschluesselnder Text
     * @param key Uebergebener Schluessel
     * @return encode-Methode, die unverschluesselten Text und Schluessel entgegennimmt
     */
    public static String encode(String text, String key) {
        
        String encodedText = "";

        text = text.toUpperCase();
        key = key.toUpperCase();

        if (key.length() == 0) {
            return text.toUpperCase();
        }

        for (int i = 0; i < key.length(); i++){
            if(key.charAt(i) < 'A' || key.charAt(i) > 'Z') {
                return text.toUpperCase();
            }
        }

 
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
                int result = (text.charAt(i) + getNormalAlphabetIndex(key.charAt(i % key.length())));
                if (result > 'Z') {
                    result = result - 26;
                }
                encodedText = encodedText + (char) result;
            } else {
                encodedText = encodedText + text.charAt(i);
            }
        }
 
        return encodedText;
 
    }

    /**
     * Entschluesselt einen Verschluesselten Text mit dem dazugehoerigen Schluessel
     * @param text Zu entschluesselnder Text
     * @param key Uebergebener Schluessel
     * @return Entschluesselter Text
     */
    public static String decode(String text, String key) {
 
        String decodedText = "";

        text = text.toUpperCase();
        key = key.toUpperCase();

        if (key.length() == 0) {
            return text.toUpperCase();
        }

        for (int i = 0; i < key.length(); i++){
            if(key.charAt(i) < 'A' || key.charAt(i) > 'Z') {
                return text.toUpperCase();
            }
        }
 
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
                int result;
                if (getNormalAlphabetIndex(text.charAt(i)) - getNormalAlphabetIndex(key.charAt(i % key.length())) < 0) {
                    result = normalAlphabet[getNormalAlphabetIndex(text.charAt(i)) - getNormalAlphabetIndex(key.charAt(i % key.length())) + 26];
                } else {
                    result = normalAlphabet[getNormalAlphabetIndex(text.charAt(i)) - getNormalAlphabetIndex(key.charAt(i % key.length()))];
                }
                decodedText = decodedText + (char) result;
            } else {
                decodedText = decodedText + text.charAt(i);
            }
            
        }
 
        return decodedText;
 
    }

    /**
     * Sucht ein Zeichen im normalAlphabet und gibt dessen Index zurueck, falls es enthalten ist
     * @param symbol Zu suchendes char im Alphabet
     * @return Falls symbol in normalAlphabet enthalten dann, Index des Zeichens im Alphabet, sonst -1
     */
    private static int getNormalAlphabetIndex(char symbol) {
        if (symbol >= 'A' && symbol <= 'Z') {
            for (int i = 0; i < normalAlphabet.length; i++) {
                if (normalAlphabet[i] == symbol) {
                    return i;
                }
            }
        }
        return -1;
    }
}