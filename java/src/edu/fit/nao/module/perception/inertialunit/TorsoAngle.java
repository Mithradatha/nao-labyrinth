package edu.fit.nao.module.perception.inertialunit;

/**
 * radians (float)
 */
public class TorsoAngle {

    public final float x;
    public final float y;

    public TorsoAngle(float x, float y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {

        return "TorsoAngle{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
