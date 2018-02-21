package edu.fit.nao;

import java.util.List;

public class TimeStamp {

    private long seconds;
    private long microseconds;

    public TimeStamp(Object o) {

        if (o instanceof List) {

            List timeStamp = (List)o;
            seconds = (long)timeStamp.get(0);
            microseconds = (long)timeStamp.get(1);

        } else throw new ClassCastException("TimeStamp conversion not implemented correctly");
    }

    @Override
    public String toString() {

        return "TimeStamp {\n" +
                seconds + ",\n" +
                microseconds + "\n" +
                "}";
    }
}
