package edu.fit.nao.module.localization;

import edu.fit.nao.helper.geometry.Position2D;

/**
 * noise = Guassian{variance=s, mean=0}
 */
public class Observation {

    public final Frame frame;
    public final float distance;
    public final float bearing;

    public final float distanceNoise = 0.0f;
    public final float bearingNoise = 0.0f;

    public Observation(Frame frame, float distance, float bearing) {

        this.frame = frame;
        this.distance = distance;
        this.bearing = bearing;
    }

    public Observation(Frame frame, Position2D local, Position2D reference) {

        float xDisplacement = local.x - reference.x;
        float yDisplacement = local.y - reference.y;

        double distance = Math.sqrt(Math.pow(xDisplacement, 2) + Math.pow(yDisplacement, 2));
        double bearing = Math.atan(yDisplacement / xDisplacement);

        this.frame = frame;
        this.distance = (float) distance + distanceNoise;
        this.bearing = (float) bearing + bearingNoise;
    }

    public static Observation FromLandmarkPositionInRobotFrame(Position2D reference) {

        // between local robot and reference landmark
        return new Observation(Frame.ROBOT, new Position2D(0.0f, 0.0f), reference);
    }

    public static Observation Inverse(Observation observation, Position2D reference) {

        // TODO: implement inverse

        // between global frame and reference landmark
        return new Observation(Frame.WORLD, observation.distance, observation.bearing);
    }

    public Observation subtract(Observation o) {

        return new Observation(frame, distance - o.distance, bearing - o.bearing);
    }

    @Override
    public String toString() {

        return "Observation{" +
                "distance=" + distance +
                ", bearing=" + bearing +
                '}';
    }
}
