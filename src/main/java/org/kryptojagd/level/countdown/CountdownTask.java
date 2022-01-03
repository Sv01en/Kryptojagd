package org.kryptojagd.level.countdown;

import java.util.TimerTask;

public class CountdownTask extends TimerTask {

    private long actuelValue;

    private long setValue;

    public CountdownTask(long setValue) {
        this.setValue = setValue;
        this.actuelValue = setValue;
    }

    @Override
    public void run() {
        this.actuelValue = this.setValue--;
    }

    public long getActuelValue() {
        return this.actuelValue;
    }
}
