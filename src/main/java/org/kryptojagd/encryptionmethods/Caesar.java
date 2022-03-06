package org.kryptojagd.encryptionmethods;

/**
 * Provides methods for encrypting and decrypting texts with the Caesar encryption
 *
 * @author Leah Schlimm, Sven Strasser, Bartosz Treyde
 */
public class Caesar extends Encryption {

    @Override
    public String decode(String text, String key) {
        return null;
    }

    /**
     * Instantiates a new Caesar encryption.
     */
    public Caesar() {
        super.name = "Caesar";
    }

    /**
     * Generates a random number between 1 and 25
     * @return Random int value
     */
     public static int generateKey() {

        return (int) (Math.random() * (26 - 1) + 1);

    }

    /**
     * Encrypts a string
     *
     * if the key has not changed,
     * it encrypts with a randomly generated key
     * @param text Text to be encrypted
     * @return Encrypted text in uppercase
     */
    public String encode(String text) {
        if (super.key.equals("0")) {
            super.key = Integer.toString(generateKey());
        }
        return encode(text, super.key);
    }

    /**
     * Encrypts a string with a given key
     * @param text Text to be encrypted
     * @param keyString Passed key as a String
     * @return Encrypted text in uppercase
     */
    public String encode(String text, String keyString) {

        int key = Integer.parseInt(keyString);

        if (key < 0 || key > 26) {
            return text.toUpperCase();
        }

        char[] textCharArr;
        String encodedText = "";

        String inputText = text.toUpperCase();
        textCharArr = inputText.toCharArray();

        for (int i = 0; i < textCharArr.length; i++) {
            encodedText = encodedText + shift(textCharArr[i], key);
        }
        return encodedText;
    }

    /**
     * Decrypts an encrypted text with the corresponding key
     * @param text Text to be deciphered
     * @param key Encryption key
     * @return Deciphered text in uppercase
     */
    public String decode(String text, int key) {
        
        if (key < 0 || key > 26) {
            return text.toUpperCase();
        }

        char[] textCharArr;
        String decodedText = "";

        String inputText = text.toUpperCase();
        textCharArr = inputText.toCharArray();

        for (int i = 0; i < textCharArr.length; i++) {
            decodedText = decodedText + shift(textCharArr[i], 26 - key);
        }
        return decodedText;
    }

    /**
     * Shifts a given char with the given key cyclically along the uppercase ASCII alphabet.
     * @param symbol Any char
     * @param key Passed key
     * @return Shipped char if it is in the uppercase ASCII alphabet and otherwise the unshipped char symbol
     */
    private static char shift(char symbol, int key) {

        char tmp = symbol;

        if (tmp >= 'A' && tmp <= 'Z') {
            tmp = (char) (tmp + key);
            
            if (tmp > 'Z') {
                tmp = (char) (tmp - 26);
            }
        }

        return tmp;
    }
}