package org.kryptojagd.logic.verschluesselungsverfahren;

/**
* Provides methods for encrypting and decrypting texts with Beaufort encryption
 *
 * @author Leah Schlimm
*/
public class Beaufort implements EncryptionInterface {


    private final static char[] alphabet = "ZYXWVUTSRQPONMLKJIHGFEDCBA".toCharArray();
    private final static char[] normalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * Generated a random number between 4 and 6
     * @return Random int value
     */
    public static int keyLength () {

        return (int) (Math.random() * (7 - 4) + 4);
    }

    /**
     * Generates a random number between 0 and 25
     * @return Random int value
     */
    public static int keySymbolIndex () {

        return (int) (Math.random() * (26 - 0) + 0);

    }

    /**
     * Encrypts a string with a randomly generated key
     * @param text Text to be encrypted
     * @return Encrypted text in uppercase
     */
    public static String encode(String text) {
        String key = "";

        int keyLength = keyLength();
        for (int i = 0; i < keyLength; i++) {
            int symbolIndex = keySymbolIndex();
            key = key + alphabet[symbolIndex];
        }

        return encode(text, key);
    }
    
    /**
     * Encrypts a string with a given key
     * @param text Zu verschluesselnder Text
     * @param key Text to be encrypted
     * @return Encrypted text in uppercase
     */
    public static String encode(String text, String key) {
 
        String encodedText = "";

        key = key.toUpperCase();
        text = text.toUpperCase();

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
                int result = alphabet[(text.charAt(i) + key.charAt(i % key.length())) % 26];
                encodedText = encodedText + (char) result;
            } else {
                encodedText = encodedText + text.charAt(i);
            }
        }
 
        return encodedText;
 
    }

    /**
     * Decrypts an encrypted text with the corresponding key
     * @param text Text to be deciphered
     * @param key Passed key
     * @return Deciphered text
     */
    public static String decode (String text, String key) {
 
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
            int charIndex = getAlphabetIndex(text.charAt(i));
            if (charIndex != -1) {
                int result;
                if (charIndex - getNormalAlphabetIndex(key.charAt(i % key.length())) < 0) {
                    result = normalAlphabet[26 + charIndex - getNormalAlphabetIndex(key.charAt(i % key.length()))];
                } else {
                    result = normalAlphabet[charIndex - getNormalAlphabetIndex(key.charAt(i % key.length()))];
                }
                
                decodedText = decodedText + (char) result;
            } else {
                decodedText = decodedText + text.charAt(i);
            }
            
        }
 
        return decodedText;
 
    }

    /**
     * Searches for a character in the alphabet and returns its index if it is included
     * @param symbol Character to search for in the alphabet
     * @return If symbol is in alphabet, then the index of the character in the alphabet, otherwise -1
     */
    private static int getAlphabetIndex(char symbol) {
        if (symbol >= 'A' && symbol <= 'Z') {
            for (int i = 0; i < alphabet.length; i++) {
                if (alphabet[i] == symbol) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Searches for a character in the normal alphabet and returns its index if it is contained
     * @param symbol Character to search for in the alphabet
     * @return If symbol is contained in normalAlphabet, then index of the character in the alphabet, otherwise -1
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
