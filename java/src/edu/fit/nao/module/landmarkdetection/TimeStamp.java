package edu.fit.nao.module.landmarkdetection;

import edu.fit.nao.ALValue;

import java.util.List;

/**
 * TimeStamp {
 * TimeStamp_Seconds,
 * Timestamp_Microseconds
 * }
 */
public class TimeStamp extends ALValue {

    public final int seconds;
    public final int microseconds;

    public TimeStamp(int seconds, int microseconds) {

        this.seconds = seconds;
        this.microseconds = microseconds;
    }

    public static TimeStamp FromALValue(List alValue) {

        int seconds = (int) alValue.get(0);
        int microseconds = (int) alValue.get(1);

        return new TimeStamp(seconds, microseconds);
    }
}
