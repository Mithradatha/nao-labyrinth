package edu.fit.nao.module.navigation;

public class Node implements Comparable<Node> {

    public final Point2D position;
    private final boolean blocked;

    public Node parent;

    public int g, h;

    public Node(final Point2D position, final boolean blocked) {

        this.position = position;

        this.g = Integer.MAX_VALUE;
        this.h = Integer.MAX_VALUE;

        this.blocked = blocked;
    }

    public Node(final int x, final int y, final boolean blocked) {
        this(new Point2D(x, y), blocked);
    }

    public Node(final int x, final int y) {
        this(x, y, false);
    }

    public boolean isBlocked() {
        return blocked;
    }

    private int getF() {
        return g + h;
    }

    @Override
    public int compareTo(Node o) {
        return this.getF() - o.getF();
    }
}
