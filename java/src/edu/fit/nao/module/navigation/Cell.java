package edu.fit.nao.module.navigation;

import edu.fit.nao.module.geometry.Point2D;

public class Cell implements Comparable<Cell> {

    public final Point2D position;
    private final boolean blocked;

    public Cell parent;

    public int accumulatedCost, futureCost;

    public Cell(Point2D position, boolean blocked) {

        this.position = position;

        this.accumulatedCost = Integer.MAX_VALUE;
        this.futureCost = Integer.MAX_VALUE;

        this.blocked = blocked;
    }

    public Cell(int x, int y, boolean blocked) {
        this(new Point2D(x, y), blocked);
    }

    public Cell(int x, int y) {
        this(x, y, false);
    }

    public boolean isBlocked() {
        return blocked;
    }

    private int getTotalCost() {
        return accumulatedCost + futureCost;
    }

    @Override
    public int compareTo(Cell o) {
        return this.getTotalCost() - o.getTotalCost();
    }
}
