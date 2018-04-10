package edu.fit.nao.module.perception.sonar;

public class SonarEvent {

    public Name name;
    public float distance;

    public SonarEvent(Name name, float distance) {

        this.name = name;
        this.distance = distance;
    }

    public enum Name {

        LEFT,
        RIGHT,
        LEFT_NOTHING,
        RIGHT_NOTHING
    }

    @Override
    public String toString() {

        return "SonarEvent{" +
                "name=" + name +
                ", distance=" + distance +
                '}';
    }
}
