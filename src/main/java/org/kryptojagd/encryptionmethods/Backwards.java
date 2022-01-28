package org.kryptojagd.encryptionmethods;

import java.util.ArrayList;

/**
 * Class that contains methods for encrypting and decrypting text using backward encryption.
 * 
 * @author Leah Schlimm, Sven Strasser
 */
public class Backwards extends Encryption {

    public Backwards() {
        super.name = "Backwards";
    }

    /**
     * Encrypts a text using word wise reverse encryption
     * 
     * @param text Text to be encrypted
     * @param key Key field needed because of the interface, will not be used here
     * @return Encrypted text
     */
    public String encode(String text, String key) {
        String inputText = text.toUpperCase();

        String encryptedText = "";
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> revWords = new ArrayList<>();

        // Separate input text with spaces and special characters
        String tmp = "";
        for (int i = 0; i < inputText.length(); i++) {
            if (inputText.charAt(i) >= 'A' && inputText.charAt(i) <= 'Z') {
                tmp = tmp + inputText.charAt(i);
            } else {
                words.add(tmp);
                tmp = "";
                words.add("" + inputText.charAt(i));
            }
        }

        words.add(tmp);

        // Remove unnecessary empty words
        for (int i = words.size() - 1; i >= 0; i--) {
            if (words.get(i).equals("")) {
                words.remove(i);
            }
        }

        // Flip every word
        for (int i = 0; i < words.size(); i++) {
            String revWord = "";
            for (int j = words.get(i).length() - 1; j >= 0; j--) {
                revWord = revWord + words.get(i).charAt(j);
            }
            revWords.add(revWord);
        }

        // Assemble the return string from individual words
        for (int i = 0; i < revWords.size(); i++) {
            encryptedText = encryptedText + revWords.get(i);
        }

        return encryptedText;
    }

    /**
     * Decrypt a text that has been encrypted with reverse encryption
     * 
     * @param text Text to be decrypted
     * @param key Key field needed because of the interface, will not be used here
     * @return Decrypted text
     */
    public String decode(String text, String key) {
        return encode(text, key);
    }
}