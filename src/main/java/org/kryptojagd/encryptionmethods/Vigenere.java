package org.kryptojagd.encryptionmethods;

import java.util.ArrayList;

/**
 * Provides methods for encrypting and decrypting texts with Vigenere encryption
 *
 * @author Leah Schlimm, Sven Strasser, Bartosz Treyde
 */
public class Vigenere extends Encryption {

    private static final char[] NORMALALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * Instantiates a new Vigenere encryption
     */
    public Vigenere() {
        super.name = "Vigenere";
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

        return (int) (Math.random() * (26) + 0);

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
            StringBuilder key = new StringBuilder();

            int keyLength = keyLength();
            for (int i = 0; i < keyLength; i++) {
                int symbolIndex = keySymbolIndex();
                key.append(NORMALALPHABET[symbolIndex]);
            }
            super.key = key.toString();
        }
        return encode(text, key);
    }

    /**
     * Encrypts a string with a given key
     * @param text Text to be encrypted
     * @param key Passed key
     * @return encode method that accepts unencrypted text and keys
     */
    public String encode(String text, String key) {

        StringBuilder encryptedText = new StringBuilder();
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> revWords = new ArrayList<>();

        String inputText = text.toUpperCase();
        String inputKey = key.toUpperCase();

        int keyIndex = 0;

        if (inputKey.length() == 0) {
            return inputText.toUpperCase();
        }

        for (int i = 0; i < inputKey.length(); i++) {
            if (inputKey.charAt(i) < 'A' || inputKey.charAt(i) > 'Z') {
                return inputText.toUpperCase();
            }
        }


        // Separate input text with spaces and special characters
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < inputText.length(); i++) {
            if (inputText.charAt(i) >= 'A' && inputText.charAt(i) <= 'Z') {
                tmp.append(inputText.charAt(i));
            } else {
                words.add(tmp.toString());
                tmp = new StringBuilder();
                words.add("" + inputText.charAt(i));
            }
        }

        words.add(tmp.toString());

        // Remove unnecessary empty words
        for (int i = words.size() - 1; i >= 0; i--) {
            if (words.get(i).equals("")) {
                words.remove(i);
            }
        }


        // Encode every word
        for (String word : words) {
            if (word.length() == 1 && (word.charAt(0) < 'A' || word.charAt(0) > 'Z')) {
                revWords.add(word);
            } else {
                StringBuilder revWord = new StringBuilder();
                for (int j = 0; j < word.length(); j++) {
                    int result = (word.charAt(j) + getNormalAlphabetIndex(inputKey.charAt(keyIndex
                            % inputKey.length())));
                    keyIndex++;
                    if (result > 'Z') {
                        result = result - 26;
                    }
                    revWord.append((char) result);
                }
                revWords.add(revWord.toString());
            }

        }

        // Assemble the return string from individual words
        for (String revWord : revWords) {
            encryptedText.append(revWord);
        }

        return encryptedText.toString();
    }

    /**
     * Decrypts an encrypted text with the corresponding key
     * @param text Text to be deciphered
     * @param key Passed key
     * @return Decrypted text
     */
    public String decode(String text, String key) {
 
        StringBuilder decodedText = new StringBuilder();
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> revWords = new ArrayList<>();

        String inputText = text.toUpperCase();
        String inputKey = key.toUpperCase();

        int keyIndex = 0;

        if (inputKey.length() == 0) {
            return inputText.toUpperCase();
        }

        for (int i = 0; i < inputKey.length(); i++) {
            if (inputKey.charAt(i) < 'A' || inputKey.charAt(i) > 'Z') {
                return inputText.toUpperCase();
            }
        }

        // Separate input text with spaces and special characters
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < inputText.length(); i++) {
            if (inputText.charAt(i) >= 'A' && inputText.charAt(i) <= 'Z') {
                tmp.append(inputText.charAt(i));
            } else {
                words.add(tmp.toString());
                tmp = new StringBuilder();
                words.add("" + inputText.charAt(i));
            }
        }

        words.add(tmp.toString());

        // Remove unnecessary empty words
        for (int i = words.size() - 1; i >= 0; i--) {
            if (words.get(i).equals("")) {
                words.remove(i);
            }
        }

        // Decode every word
        for (String word : words) {
            if (word.length() == 1 && (word.charAt(0) < 'A' || word.charAt(0) > 'Z')) {
                revWords.add(word);
            } else {
                StringBuilder revWord = new StringBuilder();
                for (int j = 0; j < word.length(); j++) {
                    int result;
                    if (getNormalAlphabetIndex(word.charAt(j))
                            - getNormalAlphabetIndex(inputKey.charAt(keyIndex % inputKey.length())) < 0) {
                        result = NORMALALPHABET[getNormalAlphabetIndex(word.charAt(j))
                                - getNormalAlphabetIndex(inputKey.charAt(keyIndex % inputKey.length())) + 26];
                    } else {
                        result = NORMALALPHABET[getNormalAlphabetIndex(word.charAt(j))
                                - getNormalAlphabetIndex(inputKey.charAt(keyIndex % inputKey.length()))];
                    }
                    keyIndex++;
                    revWord.append((char) result);

                }
                revWords.add(revWord.toString());
            }

        }

        // Assemble the return string from individual words
        for (String revWord : revWords) {
            decodedText.append(revWord);
        }

        return decodedText.toString();
    }

    /**
     * Searches for a character in the normal alphabet and returns its index if it is contained
     * @param symbol Character to search for in the alphabet
     * @return If symbol is contained in normalAlphabet then the index of the character in the alphabet, otherwise -1
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