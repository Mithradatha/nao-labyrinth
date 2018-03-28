package edu.fit.nao.module.navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Grid {

    private final Cell[][] grid;

    private final int xCells, yCells;

    public Grid(Cell[][] grid) {

        this.xCells = grid.length;
        this.yCells = grid[0].length;
        this.grid = grid;
    }

    public List<Cell> getNeighbors(Cell cell) {

        List<Cell> neighbors = new ArrayList<>(4);
        neighbors.add(tryGetCell(cell.position.x + 1, cell.position.y));
        neighbors.add(tryGetCell(cell.position.x, cell.position.y + 1));
        neighbors.add(tryGetCell(cell.position.x - 1, cell.position.y));
        neighbors.add(tryGetCell(cell.position.x, cell.position.y - 1));

        return neighbors.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public Cell tryGetCell(final int x, final int y) {

        if (x > -1 && x < xCells && y > -1 && y < yCells) return grid[x][y];
        else return null;
    }

    public Cell tryGetCell(final Point2D position) {
        return tryGetCell(position.x, position.y);
    }
}
