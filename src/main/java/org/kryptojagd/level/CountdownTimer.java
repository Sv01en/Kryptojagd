package org.kryptojagd.level;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Provides the functionalities required for a countdown timer.
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class CountdownTimer {

    /**
     * Initializes and executes a countdown timer.
     *
     * @param startValue given timer duration in seconds
     */
    public void countdownTimer(int startValue) {
        //TODO: public not private
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStartValue = startValue;
            @Override
            public void run() {
               returnTime(countdownStartValue);
                countdownStartValue--;
                if (countdownStartValue < 0) {
                    returnTime(0);
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * Returns the remaining seconds from the {@link CountdownTimer#countdownTimer(int)} as an integer.
     * @param givenSeconds remaining seconds
     * @return remaining seconds as an integer
     */
    public int returnTime(int givenSeconds) {
        return givenSeconds;
    }
}
