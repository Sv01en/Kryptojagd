package org.kryptojagd.level;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

    public static int DURATION;

    private int startValue;

    private int actuelValue;

    private StringProperty outputValue = new SimpleStringProperty(this, "");

    public CountdownTimer(int startValue) {
        this.startValue = startValue;
        DURATION = startValue;
    }

    /**
     * Initializes and executes a countdown timer.
     */
    public void countdownTimer() {
        //TODO: public not private
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStartValue = startValue;
            @Override
            public void run() {
               setActuelValue(countdownStartValue);
                countdownStartValue--;
                if (countdownStartValue < 0) {
                    setActuelValue(countdownStartValue);
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * Returns the remaining seconds from the {@link CountdownTimer} as a string.
     * @return remaining seconds as a string
     */
    public StringProperty getActuelValue() {
        return this.outputValue;
    }

    /**
     * Set the remaining time in a readable format for the system
     * @param givenValue remaining time as an integer
     */
    private void setActuelValue(int givenValue) {
        String input = Integer.toString(givenValue);
        this.outputValue.set(input);
    }
}
