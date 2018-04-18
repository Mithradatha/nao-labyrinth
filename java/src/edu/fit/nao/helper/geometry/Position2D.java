package edu.fit.nao.helper.geometry;

public class Position2D {

    // translation (meters)
    public final float x;
    public final float y;

    public Position2D(float x, float y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {

        return "Position2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}