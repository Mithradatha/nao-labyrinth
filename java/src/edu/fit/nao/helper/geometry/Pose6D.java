package edu.fit.nao.helper.geometry;

import edu.fit.nao.helper.ALValue;

import java.util.List;

/**
 * Pose6D {
 * x,
 * y,
 * z,
 * wx,
 * wy,
 * wz
 * }
 */
public class Pose6D extends ALValue {

    // position (meters)
    public final float x;
    public final float y;
    public final float z;

    // orientation (radians)
    public final float wx;
    public final float wy;
    public final float wz;

    public Pose6D(
            float x,
            float y,
            float z,
            float wx,
            float wy,
            float wz
    ) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.wx = wx;
        this.wy = wy;
        this.wz = wz;
    }

    public static Pose6D FromALValue(List alValue) {

        float x = (float) alValue.get(0);
        float y = (float) alValue.get(1);
        float z = (float) alValue.get(2);
        float wx = (float) alValue.get(3);
        float wy = (float) alValue.get(4);
        float wz = (float) alValue.get(5);

        return new Pose6D(x, y, z, wx, wy, wz);
    }
}
