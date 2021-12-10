package org.kryptojagd.verschluesselungsverfahren;

/**
* Stellt Methoden zum ver- und entschluesseln von Texten mit der Beaufort-Verschluesselung bereit
 *
 * @author Leah Schlimm
*/
public class Beaufort extends Encryption {


    private static final char[] ALPHABET = "ZYXWVUTSRQPONMLKJIHGFEDCBA".toCharArray();
    private static final char[] NORMALALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * Generierte eine zufaellige Zahl zwischen 4 und 6
     * @return Zufaelliger int Wert
     */
    public static int keyLength() {

        return (int) (Math.random() * (7 - 4) + 4);
    }

    /**
     * Generiert eine zufaellige Zahl zwischen 0 und 25
     * @return Zufaelliger int Wert
     */
    public static int keySymbolIndex() {

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
            key = key + ALPHABET[symbolIndex];
        }

        return encode(text, key);
    }
    
    /**
     * Verschluesselt einen String mit einem gegebenen Schluessel
     * @param inputText Zu verschluesselnder Text
     * @param inputKey Uebergebener Schluessel
     * @return Verschluesselter Text in uppercase
     */
    public static String encode(String inputText, String inputKey) {
 
        String encodedText = "";
        String key;
        String text;

        key = inputKey.toUpperCase();
        text = inputText.toUpperCase();

        if (key.length() == 0) {
            return text.toUpperCase();
        }

        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) < 'A' || key.charAt(i) > 'Z') {
                return text.toUpperCase();
            }
        }

 
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
                int result = ALPHABET[(text.charAt(i) + key.charAt(i % key.length())) % 26];
                encodedText = encodedText + (char) result;
            } else {
                encodedText = encodedText + text.charAt(i);
            }
        }
 
        return encodedText;
 
    }

    /**
     * Entschluesselt einen Verschluesselten Text mit dem dazugehoerigen Schluessel
     * @param inputText Zu entschluesselnder Text
     * @param inputKey Uebergebener Schluessel
     * @return Entschluesselter Text
     */
    public static String decode(String inputText, String inputKey) {
 
        String decodedText = "";
        String key;
        String text;

        text = inputText.toUpperCase();
        key = inputKey.toUpperCase();

        if (key.length() == 0) {
            return text.toUpperCase();
        }

        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) < 'A' || key.charAt(i) > 'Z') {
                return text.toUpperCase();
            }
        }

        for (int i = 0; i < text.length(); i++) {
            int charIndex = getAlphabetIndex(text.charAt(i));
            if (charIndex != -1) {
                int result;
                if (charIndex - getNormalAlphabetIndex(key.charAt(i % key.length())) < 0) {
                    result = NORMALALPHABET[26 + charIndex - getNormalAlphabetIndex(key.charAt(i % key.length()))];
                } else {
                    result = NORMALALPHABET[charIndex - getNormalAlphabetIndex(key.charAt(i % key.length()))];
                }
                
                decodedText = decodedText + (char) result;
            } else {
                decodedText = decodedText + text.charAt(i);
            }
            
        }
 
        return decodedText;
 
    }

    /**
     * Sucht ein Zeichen im alphabet und gibt dessen Index zurueck, falls es enthalten ist
     * @param symbol Zu suchendes char im Alphabet
     * @return Falls symbol in alphabet enthalten, dann Index des Zeichens im Alphabet, sonst -1
     */
    private static int getAlphabetIndex(char symbol) {
        if (symbol >= 'A' && symbol <= 'Z') {
            for (int i = 0; i < ALPHABET.length; i++) {
                if (ALPHABET[i] == symbol) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Sucht ein Zeichen im normalAlphabet und gibt dessen Index zurueck, falls es enthalten ist
     * @param symbol Zu suchendes char im Alphabet
     * @return Falls symbol in normalAlphabet enthalten, dann Index des Zeichens im Alphabet, sonst -1
     */
    private static int getNormalAlphabetIndex(char symbol) {
        if (symbol >= 'A' && symbol <= 'Z') {
            for (int i = 0; i < NORMALALPHABET.length; i++) {
                if (NORMALALPHABET[i] == symbol) {
                    return i;
                }
            }
        }
        return -1;
    }

}
