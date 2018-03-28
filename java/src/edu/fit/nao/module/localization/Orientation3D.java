package edu.fit.nao.module.localization;

public class Orientation3D {

    public final float wx; // roll
    public final float wy; // pitch
    public final float wz; // yaw

    public Orientation3D(float wx, float wy, float wz) {

        this.wx = wx;
        this.wy = wy;
        this.wz = wz;
    }

    @Override
    public String toString() {

        return String.format("[%.3f, %.3f, %.3f]", wx, wy, wz);
    }
}
