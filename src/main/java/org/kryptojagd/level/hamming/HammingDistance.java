package org.kryptojagd.level.hamming;

/**
 * Provides all functionalities for the hamming distance.
 * @author Sven Strasser
 * @version 1.0
 */
public class HammingDistance {

    /**
     * Calculates the hamming distance between two given words.
     * @param first given word as a string
     * @param second given word as a string
     * @return hamming distance as an integer
     */
    public int calculateHammingDistance(String first, String second) {

        char[] s1 = first.toCharArray();
        char[] s2 = second.toCharArray();

        int shortWord = Math.min(s1.length, s2.length);
        int longWord = Math.max(s1.length, s2.length);

        int counter = 0;
        for (int i = 0; i< shortWord; i++) {
            if (s1[i] != s2[i]) {
                counter++;
            }
        }
        counter += longWord - shortWord;

        return counter;
    }
}
