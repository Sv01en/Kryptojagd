package org.kryptojagd.encryptionmethods;

/**
 * The type Encryption.
 */
//redundant class, because we are not sure if we use interface or abstract class
public abstract class Encryption {

    protected String name = "Encryption";

    /**
     * Encodes the text.
     *
     * @param text the text
     * @param key  the key
     * @return the string
     */
    public  String encode(String text, String key) {
        return null;
    }

    /**
     * Decode string.
     *
     * @param text the text
     * @param key  the key
     * @return the string
     */
    public String decode(String text, String key) {
        return null;
    }

    public String toString() {
        return name;
    }

}
