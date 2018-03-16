package edu.fit.nao.module.landmarkdetection;

import edu.fit.nao.ALValue;

import java.util.List;

/**
 * Position6D {
 * x,
 * y,
 * z,
 * wx,
 * wy,
 * wz
 * }
 */
public class Position6D extends ALValue<List> {

    // translation (meters)
    public final float x;
    public final float y;
    public final float z;

    // rotation (radians)
    public final float wx;
    public final float wy;
    public final float wz;

    public Position6D(
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

    public Position6D(List alValue) {

        this(
                (float) alValue.get(0),
                (float) alValue.get(1),
                (float) alValue.get(2),
                (float) alValue.get(3),
                (float) alValue.get(4),
                (float) alValue.get(5)
        );
    }
}
