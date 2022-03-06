package org.kryptojagd.encryptionmethods;

/**
 * The type Encryption.
 */
public abstract class Encryption {

    protected String name = "Encryption";
    protected String key = "0";

    /**
     * Encodes the text.
     *
     * @param text the text
     * @return the string
     */
    public abstract String encode(String text);


    /**
     * Decode string.
     *
     * @param text the text
     * @param key encryption key
     * @return the string
     */
    public abstract String decode(String text, String key);

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(String key) {
        this.key = key;
    }


    /**
     * Gives the name of the Encryption
     * @return the name of the Encryption
     */
    public String toString() {
        return name;
    }
}
