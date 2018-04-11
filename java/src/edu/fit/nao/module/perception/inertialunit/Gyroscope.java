package edu.fit.nao.module.perception.inertialunit;

/**
 * radians per second (float)
 * <p>
 * Once the z axis is provided by Aldebaran,
 * it can be used for the robot's orientation
 */
public class Gyroscope {

    public final float wx;
    public final float wy;

    public Gyroscope(float wx, float wy) {
        this.wx = wx;
        this.wy = wy;
    }

    @Override
    public String toString() {

        return "Gyroscope{" +
                "wx=" + wx +
                ", wy=" + wy +
                '}';
    }
}
