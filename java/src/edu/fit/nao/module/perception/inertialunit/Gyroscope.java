package edu.fit.nao.module.perception.inertialunit;

/**
 * radians per second (float)
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
