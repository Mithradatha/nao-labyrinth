package edu.fit.nao.module.landmarkdetection;

import edu.fit.nao.Util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TimeStamp {
 * TimeStamp_Seconds,
 * Timestamp_Microseconds
 * }
 */
public class TimeStamp {

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

    @Override
    public String toString() {

        List<Map.Entry<String, Object>> fields = new ArrayList<>();
        fields.add(new AbstractMap.SimpleImmutableEntry<String, Object>("seconds", seconds));
        fields.add(new AbstractMap.SimpleImmutableEntry<String, Object>("microseconds", microseconds));

        return Util.ToJson(fields);
    }
}
