package edu.fit.nao.helper.landmarkdetection;

import edu.fit.nao.helper.ALValue;

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
public class ShapeInfo extends ALValue {

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

    public static ShapeInfo FromALValue(List alValue) {

        int one = (int) alValue.get(0);
        float alpha = (float) alValue.get(1);
        float beta = (float) alValue.get(2);
        float sizeX = (float) alValue.get(3);
        float sizeY = (float) alValue.get(4);
        int heading = (int) alValue.get(5);

        return new ShapeInfo(one, alpha, beta, sizeX, sizeY, heading);
    }
}
