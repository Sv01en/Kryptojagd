package org.kryptojagd.verschluesselungsverfahren;

import java.util.ArrayList;

/**
 * Class that contains methods for encrypting and decrypting text using backward encryption.
 * 
 * @author Leah Schlimm
 */
public class Backwards implements EncryptionInterface {

    /**
     * Encrypts a text using word wise reverse encryption
     * 
     * @param text Text to be encrypted
     * @param key Key field needed because of the interface, will not be used here
     * @return Encrypted text
     */
    public static String encode(String text, String key) {
        text = text.toUpperCase();

        String encryptedText = "";
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> revWords = new ArrayList<>();

        // Separate input text with spaces and special characters
        String tmp = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
                tmp = tmp + text.charAt(i);
            } else {
                words.add(tmp);
                tmp = "";
                words.add("" + text.charAt(i));
            }
        }

        words.add(tmp);

        // Remove unnecessary empty words
        for (int i = 0; i < words.size(); i++) {
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
    public static String decode(String text, String key) {
        return encode(text, key);
    }
}