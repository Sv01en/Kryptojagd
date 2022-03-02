package org.kryptojagd.level.countdown;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Provides the functionalities for a countdown timer.
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class CountdownTimer {

    /**
     * Stores the current value as a long.
     */
    private long actualValue;

    /**
     * Stores the current value as a string.
     */
    private String outputValue;

    private TimerTask task;

    private Timer timer = new Timer();

    /**
     * Initializes with a given time in seconds and calls {@link #countdownTimer(int)}
     * @param given Countdown duration in seconds, passed as an integer
     */
    public CountdownTimer(int given) {
        this.countdownTimer(given);
    }

    /**
     * Initializes and executes a countdown timer.
     */
    private long countdownTimer(int setTime) {
        actualValue = setTime;
        this.task = new TimerTask() {
            @Override
            public void run() {
                if (actualValue > 0) {
                    actualValue--;
                    setCurrentValue(actualValue);
                }
                if (actualValue == 0) {
                    this.cancel();
                }
            }
        };
        this.timer.schedule(task, 0, 1000);
        return actualValue;
    }

    /**
     * Returns the remaining seconds from the {@link CountdownTimer} as a string.
     * @return remaining seconds as a string
     */
    public String getCurrentValue() {
        return this.outputValue;
    }

    /**
     * Set the remaining time in a readable format for the system
     * @param givenValue remaining time as an integer
     */
    private void setCurrentValue(long givenValue) {
        String input = Long.toString(givenValue);
        this.actualValue = givenValue;
        this.outputValue = input;
    }

    /**
     * After an incorrect entry in the game, the timer will be reduced by the given time in seconds.
     * @param given time to be reduced in seconds
     */
    public void reduceTimer(int given) {
        actualValue = actualValue - given;
        this.outputValue = Long.toString(actualValue);
    }

    /**
     * Cancel the running {@link TimerTask} and {@link Timer}.
     */
    public void cancelTimerTask() {
        this.task.cancel();
        this.timer.cancel();
    }
}
