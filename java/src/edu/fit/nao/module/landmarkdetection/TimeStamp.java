package edu.fit.nao.module.landmarkdetection;

import edu.fit.nao.ALValue;

import java.util.List;

/**
 * TimeStamp {
 * TimeStamp_Seconds,
 * Timestamp_Microseconds
 * }
 */
public class TimeStamp extends ALValue<List> {

    public int seconds;
    public int microseconds;

    public TimeStamp(int seconds, int microseconds) {

        this.seconds = seconds;
        this.microseconds = microseconds;
    }

    public TimeStamp(List alValue) {

        this(
                (int) alValue.get(0),
                (int) alValue.get(1)
        );
    }
}
