package edu.fit.nao.module.navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Grid {

    private final Node[][] grid;

    private final int xNodes, yNodes;

    Grid(final Node[][] grid) {

        this.xNodes = grid.length;
        this.yNodes = grid[0].length;
        this.grid = grid;
    }

    public List<Node> getNeighbors(Node node) {

        List<Node> neighbors = new ArrayList<>(4);
        neighbors.add(tryGetNode(node.position.x + 1, node.position.y));
        neighbors.add(tryGetNode(node.position.x, node.position.y + 1));
        neighbors.add(tryGetNode(node.position.x - 1, node.position.y));
        neighbors.add(tryGetNode(node.position.x, node.position.y - 1));

        return neighbors.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private Node tryGetNode(final int x, final int y) {

        if (x > -1 && x < xNodes && y > -1 && y < yNodes) return grid[x][y];
        else return null;
    }

    public Node tryGetNode(final Point2D position) {
        return tryGetNode(position.x, position.y);
    }
}
