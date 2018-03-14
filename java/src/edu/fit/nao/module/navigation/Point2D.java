package edu.fit.nao.module.navigation;

public class Point2D {

    public final int x;
    public final int y;

    Point2D(final int x, final int y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", x, y);
    }
}
