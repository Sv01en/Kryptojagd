package org.kryptojagd.level.countdown;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Provides the functionalities required for a countdown timer.
 *
 *  @author Sven Strasser
 * @version 1.0
 */

//TODO: correct handling with given values, just working values.
public class CountdownTimer {

    public static int DURATION;

    private int startValue;

    private long actuelValue;

    private String outputValue;

    private int minutes;

    private int seconds;

    private Timer timer;

    public CountdownTimer(int given) {
        this.countdownTimer(given);
        this.startValue = startValue;
        DURATION = startValue;
    }

    /**
     * Initializes and executes a countdown timer.
     */
    public long countdownTimer(int setTime) {
        actuelValue = 20;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (actuelValue > 0)
                    actuelValue--;
                    setActuelValue(actuelValue);
                if (actuelValue == 0) {
                    System.out.println("Time over");
                }
            }
        };
        timer.schedule(task, 0, 1000);
        System.out.println(actuelValue);
        return actuelValue;
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
    private void setActuelValue(long givenValue) {
        String input = Long.toString(givenValue);
        this.actuelValue = givenValue;
        this.outputValue = input;
    }
}
