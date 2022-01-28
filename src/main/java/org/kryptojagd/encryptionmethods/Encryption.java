package org.kryptojagd.encryptionmethods;

/**
 * The type Encryption.
 */
//redundant class, because we are not sure if we use interface or abstract class
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
     * @return the string
     */
    public abstract String decode(String text, String key);



    public String toString() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
