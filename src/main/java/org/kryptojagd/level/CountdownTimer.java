package org.kryptojagd.level;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Provides the functionalities required for a countdown timer.
 *
 * @author Sven Strasser
 * @version 1.0
 */
public class CountdownTimer {

    public static int DURATION;

    private int startValue;

    private long actuelValue;

    private String outputValue;

    private int minutes;

    private int seconds;

    private Timer timer;

    public CountdownTimer(int startValue) {
        this.startValue = startValue;
        DURATION = startValue;
    }

    /**
     * Initializes and executes a countdown timer.
     */
    public static void countdownTimer(int setTime) {
        //TODO: implement timer function
    }

    /**
     * Returns the remaining seconds from the {@link CountdownTimer} as a string.
     * @return remaining seconds as a string
     */
    public String getActuelValue() {
        return this.outputValue;
    }

    /**
     * Set the remaining time in a readable format for the system
     * @param givenValue remaining time as an integer
     */
    private void setActuelValue(int givenValue) {
        String input = Integer.toString(givenValue);
        this.actuelValue = givenValue;
        this.outputValue = input;
    }

    public long getActuelValueAsLong() {
        return this.actuelValue;
    }
}
