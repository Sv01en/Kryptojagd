package org.kryptojagd.verschluesselungsverfahren;

/**
 * Stellt Methoden zum ver- und entschluesseln von Texten mit der Vigenere-Verschluesselung bereit
 *
 * @author Leah Schlimm
 */
public class Vigenere extends Encryption {

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
            key = key + NORMALALPHABET[symbolIndex];
        }

        return encode(text, key);
    }

    /**
     * Verschluesselt einen String mit einem gegebenen Schluessel
     * @param inputText Zu verschluesselnder Text
     * @param inputKey Uebergebener Schluessel
     * @return encode-Methode, die unverschluesselten Text und Schluessel entgegennimmt
     */
    public static String encode(String inputText, String inputKey) {
        
        String encodedText = "";

        String text;
        String key;

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
     * @param inputText Zu entschluesselnder Text
     * @param inputKey Uebergebener Schluessel
     * @return Entschluesselter Text
     */
    public static String decode(String inputText, String inputKey) {
 
        String decodedText = "";

        String text;
        String key;

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
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
                int result;
                if (getNormalAlphabetIndex(text.charAt(i)) - getNormalAlphabetIndex(key.charAt(i % key.length())) < 0) {
                    result = NORMALALPHABET[getNormalAlphabetIndex(text.charAt(i))
                            - getNormalAlphabetIndex(key.charAt(i % key.length())) + 26];
                } else {
                    result = NORMALALPHABET[getNormalAlphabetIndex(text.charAt(i))
                            - getNormalAlphabetIndex(key.charAt(i % key.length()))];
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
            for (int i = 0; i < NORMALALPHABET.length; i++) {
                if (NORMALALPHABET[i] == symbol) {
                    return i;
                }
            }
        }
        return -1;
    }
}
