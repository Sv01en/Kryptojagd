package org.kryptojagd.encryptionmethods;

/**
* Provides methods for encrypting and decrypting texts with Beaufort encryption
 *
 * @author Leah Schlimm, Sven Strasser, Bartosz Treyde
*/
public class Beaufort extends Encryption {

    private static final char[] ALPHABET = "ZYXWVUTSRQPONMLKJIHGFEDCBA".toCharArray();
    private static final char[] NORMALALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * Instantiates a new Beaufort encryption.
     */
    public Beaufort() {
        super.name = "Beaufort";
    }

    private int key;

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Generated a random number between 4 and 6
     * @return Random int value
     */
    public static int keyLength() {

        return (int) (Math.random() * (7 - 4) + 4);
    }

    /**
     * Generates a random number between 0 and 25
     * @return Random int value
     */
    public static int keySymbolIndex() {

        return (int) (Math.random() * (26 - 0) + 0);

    }

    /**
     * Encrypts a string with a randomly generated key
     * @param text Text to be encrypted
     * @return Encrypted text in uppercase
     */
    public String encode(String text) {
        String key = "";

        int keyLength = keyLength();
        for (int i = 0; i < keyLength; i++) {
            int symbolIndex = keySymbolIndex();
            key = key + ALPHABET[symbolIndex];
        }

        return encode(text, key);
    }
    
    /**
     * Encrypts a string with a given key
     * @param text Zu verschluesselnder Text
     * @param key Text to be encrypted
     * @return Encrypted text in uppercase
     */
    public String encode(String text, String key) {
 
        String encodedText = "";

        String inputKey = key.toUpperCase();
        String inputText = text.toUpperCase();

        if (inputKey.length() == 0) {
            return inputText.toUpperCase();
        }

        for (int i = 0; i < inputKey.length(); i++) {
            if (inputKey.charAt(i) < 'A' || inputKey.charAt(i) > 'Z') {
                return inputText.toUpperCase();
            }
        }

 
        for (int i = 0; i < inputText.length(); i++) {
            if (inputText.charAt(i) >= 'A' && inputText.charAt(i) <= 'Z') {
                int result = ALPHABET[(inputText.charAt(i) + inputKey.charAt(i % inputKey.length())) % 26];
                encodedText = encodedText + (char) result;
            } else {
                encodedText = encodedText + inputText.charAt(i);
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
    public String decode(String text, String key) {
 
        String decodedText = "";

        String inputText = text.toUpperCase();
        String inputKey = key.toUpperCase();

        if (inputKey.length() == 0) {
            return inputText.toUpperCase();
        }

        for (int i = 0; i < inputKey.length(); i++) {
            if (inputKey.charAt(i) < 'A' || inputKey.charAt(i) > 'Z') {
                return inputText.toUpperCase();
            }
        }

        for (int i = 0; i < inputText.length(); i++) {
            int charIndex = getAlphabetIndex(inputText.charAt(i));
            if (charIndex != -1) {
                int result;
                if (charIndex - getNormalAlphabetIndex(inputKey.charAt(i % inputKey.length())) < 0) {
                    result = NORMALALPHABET[26
                            + charIndex - getNormalAlphabetIndex(inputKey.charAt(i % inputKey.length()))];
                } else {
                    result = NORMALALPHABET[charIndex - getNormalAlphabetIndex(inputKey.charAt(i % inputKey.length()))];
                }
                
                decodedText = decodedText + (char) result;
            } else {
                decodedText = decodedText + inputText.charAt(i);
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
            for (int i = 0; i < ALPHABET.length; i++) {
                if (ALPHABET[i] == symbol) {
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
            for (int i = 0; i < NORMALALPHABET.length; i++) {
                if (NORMALALPHABET[i] == symbol) {
                    return i;
                }
            }
        }
        return -1;
    }
}