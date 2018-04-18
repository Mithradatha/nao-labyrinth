package edu.fit.nao.helper.geometry;

public class Position3D {

    // translation (meters)
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

        return "Position3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
