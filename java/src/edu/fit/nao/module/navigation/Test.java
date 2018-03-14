package edu.fit.nao.module.navigation;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String... args) {

        if (args.length != 3) System.exit(1);

        final int xNodes = Integer.parseInt(args[0]);
        final int yNodes = Integer.parseInt(args[1]);

        final String fileName = args[2];

        Node[][] grid = new Node[xNodes][yNodes];
        Node start = null, goal = null;

        int x = 0, y = 0;

        try (final BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {

            int i;
            while ((i = bis.read()) != -1) {

                char c = (char) i;

                switch (c) {
                    case 'x':
                        grid[x][y] = new Node(x, y++, true);
                        break;
                    case 'o':
                        grid[x][y] = new Node(x, y++);
                        break;
                    case '\n':
                        x++;
                        y = 0;
                        break;
                    case 's':
                        start = new Node(x, y);
                        grid[x][y++] = start;
                        break;
                    case 'f':
                        goal = new Node(x, y);
                        grid[x][y++] = goal;
                        break;
                }
            }

            if (x != xNodes) System.exit(1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        printGrid(grid);

        final AStar aStar = new AStar(grid);
        final List<Point2D> path = aStar.search(start, goal);

        path.forEach(System.out::println);
    }

    static void printGrid(final Node[][] grid) {

        for (Node[] row : grid) {
            for (Node node : row) {

                System.out.print((node.isBlocked()) ? 'x' : '_');
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
    }
}
