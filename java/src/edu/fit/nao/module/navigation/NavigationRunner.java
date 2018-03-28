package edu.fit.nao.module.navigation;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import edu.fit.nao.ModuleRunner;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.List;

public class NavigationRunner extends ModuleRunner {

    private final int xCells;
    private final int yCells;

    private final String fileName;

    public NavigationRunner(Session session, int xCells, int yCells, String fileName) {

        super(session);

        this.xCells = xCells;
        this.yCells = yCells;
        this.fileName = fileName;
    }

    @Override
    public void run() throws Exception {

        ALTextToSpeech tts = new ALTextToSpeech(session);

        Cell[][] grid = new Cell[xCells][yCells];
        Cell start = null, goal = null;

        int x = 0, y = 0;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {

            int i;
            while ((i = bis.read()) != -1) {

                char c = (char) i;

                switch (c) {
                    case 'x':
                        grid[x][y] = new Cell(x, y++, true);
                        break;
                    case 'o':
                        grid[x][y] = new Cell(x, y++);
                        break;
                    case '\n':
                        x++;
                        y = 0;
                        break;
                    case 's':
                        start = new Cell(x, y);
                        grid[x][y++] = start;
                        break;
                    case 'f':
                        goal = new Cell(x, y);
                        grid[x][y++] = goal;
                        break;
                }
            }

            if (x != xCells) throw new Exception("File Format Exception");
        }

        printGrid(grid);

        AStar aStar = new AStar(grid);
        List<Point2D> path = aStar.search(start, goal);

        tts.say("Path found!");
        path.forEach(System.out::println);
    }

    private static void printGrid(Cell[][] grid) {

        for (Cell[] row : grid) {
            for (Cell cell : row) {

                System.out.print((cell.isBlocked()) ? 'x' : '_');
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
    }
}
