package edu.fit.nao.module.landmarkdetection;

import edu.fit.nao.Util;

import java.util.List;

/**
 * ShapeInfo {
 * 1,
 * alpha,
 * beta,
 * sizeX,
 * sizeY,
 * heading
 * }
 */
public class ShapeInfo {

    public int one;
    public float alpha;
    public float beta;
    public float sizeX;
    public float sizeY;
    public int heading;

    public ShapeInfo(
            final int one,
            final float alpha,
            final float beta,
            final float sizeX,
            final float sizeY,
            final int heading
    ) {
        this.one = one;
        this.alpha = alpha;
        this.beta = beta;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.heading = heading;
    }

    public ShapeInfo(List alValue) {

        this(
                (int) alValue.get(0),
                (float) alValue.get(1),
                (float) alValue.get(2),
                (float) alValue.get(3),
                (float) alValue.get(4),
                (int) alValue.get(5)
        );
    }

    @Override
    public String toString() {

        return Util.ToHumanReadable(this);
    }
}
