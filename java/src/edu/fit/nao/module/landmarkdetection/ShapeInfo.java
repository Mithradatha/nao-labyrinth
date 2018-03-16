package edu.fit.nao.module.landmarkdetection;

import edu.fit.nao.ALValue;

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
public class ShapeInfo extends ALValue<List> {

    public final int one;
    public final float alpha;
    public final float beta;
    public final float sizeX;
    public final float sizeY;
    public final int heading;

    public ShapeInfo(
            int one,
            float alpha,
            float beta,
            float sizeX,
            float sizeY,
            int heading
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
}
