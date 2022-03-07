package org.kryptojagd.level.countdown;

import org.junit.jupiter.api.Test;
import org.kryptojagd.level.CountdownTimer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The class contains test cases for the timer
 *
 * @author Bartosz Treyde
 */
class CountdownTimerTest {
    private final CountdownTimer countdownTimer = new CountdownTimer(500);

    /**
     * Tests if timer is correctly initialized
     */
    @Test
    void countdownTimer(){
        assertTrue((499 == Integer.parseInt(countdownTimer.getCurrentValue())));
    }

    /**
     * Tests if timer is correctly reduced
     */
    @Test
    void reduceTimer(){
        countdownTimer.reduceTimer(50);
        assertTrue((449 == Integer.parseInt(countdownTimer.getCurrentValue())) || (450 == Integer.parseInt(countdownTimer.getCurrentValue())));
    }

}