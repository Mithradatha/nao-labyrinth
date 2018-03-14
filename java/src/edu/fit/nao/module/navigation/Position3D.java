package edu.fit.nao.module.navigation;

public class Position3D {

    public final float x;
    public final float y;
    public final float z;

    Position3D(final float x, final float y, final float z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("[%.2f, %.2f, %.2f]", x, y, z);
    }
}
