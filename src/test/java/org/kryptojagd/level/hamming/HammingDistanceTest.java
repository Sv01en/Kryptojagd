package org.kryptojagd.level.hamming;

import org.junit.jupiter.api.Test;
import org.kryptojagd.level.HammingDistance;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The class contains test cases for the Hamming distance
 *
 * @author Bartosz Treyde
 */
class HammingDistanceTest {

    /**
     * Test calculate hamming distance.
     */
    @Test
    void testCalculateHammingDistance(){
        assertEquals(1, HammingDistance.calculateHammingDistance("TEST", "TES"));
        assertEquals(4, HammingDistance.calculateHammingDistance("", "TEST"));
        assertEquals(1, HammingDistance.calculateHammingDistance("T", "TT"));
        assertEquals(2, HammingDistance.calculateHammingDistance("TEST", "TSET"));
        assertEquals(4, HammingDistance.calculateHammingDistance("123", "TEST"));
        assertEquals(4, HammingDistance.calculateHammingDistance("?=)(", "TEST"));
        assertEquals(0, HammingDistance.calculateHammingDistance("", ""));
        assertEquals(5, HammingDistance.calculateHammingDistance("äüö``", "test"));
    }
}