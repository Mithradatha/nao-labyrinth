package edu.fit.nao.module.perception.inertialunit;

/**
 * meters per second (float)
 * 1g = 9.81 m/s
 * precision = 8 | 12 (bits)
 */
public class Accelerometer {

    public final float x;
    public final float y;
    public final float z;

    public Accelerometer(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {

        return "Accelerometer{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
