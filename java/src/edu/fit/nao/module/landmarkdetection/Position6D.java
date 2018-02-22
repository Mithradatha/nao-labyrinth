package edu.fit.nao.module.landmarkdetection;

import edu.fit.nao.Util;

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
public class Position6D {

    // translation (meters)
    public float x;
    public float y;
    public float z;

    // rotation (radians)
    public float wx;
    public float wy;
    public float wz;

    public Position6D(
            final float x,
            final float y,
            final float z,
            final float wx,
            final float wy,
            final float wz
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

    @Override
    public String toString() {

        return Util.ToHumanReadable(this);
    }
}
