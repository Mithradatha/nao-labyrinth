package edu.fit.nao.module.navigation;

public class Node implements Comparable<Node> {

    public final Point2D position;
    private final boolean blocked;

    public Node parent;

    public int accumulatedCost, futureCost;

    public Node(Point2D position, boolean blocked) {

        this.position = position;

        this.accumulatedCost = Integer.MAX_VALUE;
        this.futureCost = Integer.MAX_VALUE;

        this.blocked = blocked;
    }

    public Node(int x, int y, boolean blocked) {
        this(new Point2D(x, y), blocked);
    }

    public Node(int x, int y) {
        this(x, y, false);
    }

    public boolean isBlocked() {
        return blocked;
    }

    private int getTotalCost() {
        return accumulatedCost + futureCost;
    }

    @Override
    public int compareTo(Node o) {
        return this.getTotalCost() - o.getTotalCost();
    }
}
