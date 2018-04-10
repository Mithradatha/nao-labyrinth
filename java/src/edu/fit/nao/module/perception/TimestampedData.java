package edu.fit.nao.module.perception;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimestampedData<T> {

    public final long seconds;
    public final long millis;

    public final T value;

    public TimestampedData(T value) {

        this.millis = System.currentTimeMillis();
        this.seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        this.value = value;
    }

    public TimestampedData(TimestampedData<T> data) {

        this.millis = data.millis;
        this.seconds = data.seconds;
        this.value = data.value;
    }

    public TimestampedData(long seconds, long millis, T value) {

        this.seconds = seconds;
        this.millis = millis;
        this.value = value;
    }

    public static <T> TimestampedData FromMemory(Object o) {

        List alValue = (List) o;

        T value = (T) alValue.get(0);
        int seconds = (int) alValue.get(1);
        int microseconds = (int) alValue.get(2);

        long millis = TimeUnit.MICROSECONDS.toMillis(microseconds);

        return new TimestampedData<T>(seconds, millis, value);
    }

    @Override
    public String toString() {

        return "TimestampedData{" +
                "seconds=" + seconds +
                ", millis=" + millis +
                ", value=" + value +
                '}';
    }
}
