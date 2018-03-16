package edu.fit.nao.module.navigation;

import java.util.*;

public class AStar {

    private final Grid grid;

    public AStar(Node[][] grid) {
        this.grid = new Grid(grid);
    }

    public List<Point2D> search(Node start, Node goal) {

        PriorityQueue<Node> unexplored = new PriorityQueue<>();
        unexplored.add(start);

        HashSet<Node> explored = new HashSet<>();

        start.g = 0;
        start.h = Distance(start, goal);

        while (!unexplored.isEmpty()) {

            Node current = unexplored.peek();
            if (current == goal) return Trace(current);

            unexplored.remove(current);
            explored.add(current);

            for (Node neighbor : grid.getNeighbors(current)) {

                if (explored.contains(neighbor) || neighbor.isBlocked()) continue;

                if (!unexplored.contains(neighbor)) unexplored.add(neighbor);

                int accumulatedPathCost = current.g + 1;
                if (accumulatedPathCost >= neighbor.g) continue;

                neighbor.parent = current;
                neighbor.g = accumulatedPathCost;
                neighbor.h = Distance(neighbor, goal);
            }
        }

        return null;
    }

    private static List<Point2D> Trace(Node current) {

        List<Point2D> path = new ArrayList<>();
        path.add(current.position);

        while (current.parent != null) {

            current = current.parent;
            path.add(current.position);
        }

        Collections.reverse(path);
        return path;
    }

    private static int Distance(Node a, Node b) {

        // Manhattan Distance
        return Math.abs(a.position.x - b.position.x) + Math.abs(a.position.y - b.position.y);
    }
}
