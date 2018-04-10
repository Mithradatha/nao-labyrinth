package edu.fit.nao.module.geometry;

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

        return "Orientation3D{" +
                "wx=" + wx +
                ", wy=" + wy +
                ", wz=" + wz +
                '}';
    }
}
