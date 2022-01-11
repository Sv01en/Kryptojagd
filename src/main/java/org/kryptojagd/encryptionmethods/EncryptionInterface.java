package org.kryptojagd.encryptionmethods;

/**
 * Interface for encryption procedures
 *
 * @author Leah Schlimm, Sven Strasser
 *
 *<pre>
 * NOT USED ANYMORE
 * </pre>
 */
public interface EncryptionInterface {

    /**
     * Method that encrypts text with a given key
     * @param text Text to encrypt
     * @param key Passed key
     * @return Encrypted text
     */
    static String encode(String text, String key) {
        return null;
    }

    /**
     * Method that decrypts text with a given key
     * @param text Text to encrypt
     * @param key Passed key
     * @return Decrypted text
     */
    static String decode(String text, String key) {
        return null;
    }

    public String provideEncode(String text, String key);

    public String provideDecode(String text, String key);
}
