package edu.fit.nao.module.localization;

public class Position3D {

    public final float x;
    public final float y;
    public final float z;

    public Position3D(float x, float y, float z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("[%.3f, %.3f, %.3f]", x, y, z);
    }
}
