package edu.fit.nao.helper.geometry;

public class Point2D {

    // coordinates
    public final int x;
    public final int y;

    public Point2D(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public float angleFrom(Point2D other) {

        return (float) Math.atan((this.y - other.y) / (this.x - other.x));
    }

    public float distanceFrom(Point2D other) {

        return (float) Math.pow(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2), 0.5);
    }

    @Override
    public String toString() {

        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
