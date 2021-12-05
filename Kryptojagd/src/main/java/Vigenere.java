/**
 * Stellt Methoden zum ver- und entschluesseln von Texten mit der Vigenere-Verschluesselung bereit
 */
public class Vigenere {

    private final static char[] normalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    
    /**
     * Verschluesselt einen String mit einem zufaellig generierten Schluessel
     * @param text Zu verschluesselnder Text
     * @return Verschluesselter Text in uppercase
     */
    public static String encode(String text) {
        String key = "";

        int keyLength = (int) (Math.random() * (7 - 4 + 1) + 4);
        for (int i = 0; i < keyLength; i++) {
            int symbolIndex = (int) (Math.random() * (26 - 0 + 1) + 0);
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

        if (key.length() == 0) {
            return text.toUpperCase();
        }

        text = text.toUpperCase();
        key = key.toUpperCase();
 
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
