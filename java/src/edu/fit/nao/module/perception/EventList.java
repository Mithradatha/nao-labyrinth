package edu.fit.nao.module.perception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class EventList<T> {

    private final int maxSize;

    private List<TimestampedData<T>> events = new ArrayList<>();

    public EventList() { this(500); }
    public EventList(int maxSize) { this.maxSize = maxSize; }

    public boolean add(TimestampedData<T> event) {

        return this.events.size() != maxSize && this.events.add(new TimestampedData<>(event));
    }

    public void clear() {

        this.events.clear();
    }

    public List<TimestampedData<T>> all() {

        return new ArrayList<>(events);
    }

    public List<TimestampedData<T>> before(long millis) {

        return events.stream()
                .filter(event -> event.millis < millis)
                .collect(Collectors.toList());
    }

    public List<TimestampedData<T>> after(long millis) {

        return events.stream()
                .filter(event -> event.millis > millis)
                .collect(Collectors.toList());
    }

    public List<TimestampedData<T>> between(long beginMillis, long endMillis) {

        return events.stream()
                .filter(event -> event.millis > beginMillis && event.millis < endMillis)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {

        return "EventList{" +
                "events=" + Arrays.toString(events.toArray()) +
                '}';
    }
}
