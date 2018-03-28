package edu.fit.nao.module.navigation;

import java.util.*;

public class AStar {

    private final Grid grid;

    public AStar(Cell[][] grid) {
        this.grid = new Grid(grid);
    }

    // https://en.wikipedia.org/wiki/A*_search_algorithm
    public List<Point2D> search(Cell start, Cell goal) {

        PriorityQueue<Cell> unexplored = new PriorityQueue<>();
        unexplored.add(start);

        HashSet<Cell> explored = new HashSet<>();

        start.accumulatedCost = 0;
        start.futureCost = Distance(start, goal);

        while (!unexplored.isEmpty()) {

            Cell current = unexplored.peek();
            if (current == goal) return Trace(current);

            unexplored.remove(current);
            explored.add(current);

            for (Cell neighbor : grid.getNeighbors(current)) {

                if (explored.contains(neighbor) || neighbor.isBlocked()) continue;

                if (!unexplored.contains(neighbor)) unexplored.add(neighbor);

                int predictedCost = current.accumulatedCost + 1;
                if (predictedCost >= neighbor.accumulatedCost) continue;

                neighbor.parent = current;
                neighbor.accumulatedCost = predictedCost;
                neighbor.futureCost = Distance(neighbor, goal);
            }
        }

        return null;
    }

    private static List<Point2D> Trace(Cell current) {

        List<Point2D> path = new ArrayList<>();
        path.add(current.position);

        while (current.parent != null) {

            current = current.parent;
            path.add(current.position);
        }

        Collections.reverse(path);
        return path;
    }

    private static int Distance(Cell a, Cell b) {

        // Manhattan Distance
        return Math.abs(a.position.x - b.position.x) + Math.abs(a.position.y - b.position.y);
    }
}
