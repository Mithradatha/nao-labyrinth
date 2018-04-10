package edu.fit.nao.module.perception.sonar;

import edu.fit.nao.module.perception.EventList;
import edu.fit.nao.module.perception.TimestampedData;

public class SonarListener {

    public final EventList<SonarEvent> events = new EventList<>();

    public void attachTo(Sonar sonar) throws Exception {

        // TODO: Return subscription ids, Store active listeners

        sonar.subscribeLeftDetected("onLeftDetected::v(f)", this);
        sonar.subscribeRightDetected("onRightDetected::v(f)", this);
        sonar.subscribeLeftNothingDetected("onLeftNothingDetected::v(f)", this);
        sonar.subscribeRightNothingDetected("onRightNothingDetected::v(f)", this);
    }

    public void onLeftDetected(Float distance) {

        SonarEvent event = new SonarEvent(SonarEvent.Name.LEFT, distance);
        TimestampedData<SonarEvent> data = new TimestampedData<>(event);

        this.events.add(data);
    }

    public void onRightDetected(Float distance) {

        SonarEvent event = new SonarEvent(SonarEvent.Name.RIGHT, distance);
        TimestampedData<SonarEvent> data = new TimestampedData<>(event);

        this.events.add(data);
    }
    public void onLeftNothingDetected(Float distance) {

        SonarEvent event = new SonarEvent(SonarEvent.Name.LEFT_NOTHING, distance);
        TimestampedData<SonarEvent> data = new TimestampedData<>(event);

        this.events.add(data);
    }

    public void onRightNothingDetected(Float distance) {

        SonarEvent event = new SonarEvent(SonarEvent.Name.RIGHT_NOTHING, distance);
        TimestampedData<SonarEvent> data = new TimestampedData<>(event);

        this.events.add(data);
    }

    @Override
    public String toString() {

        return "SonarListener{" +
                "events=" + events +
                '}';
    }
}
