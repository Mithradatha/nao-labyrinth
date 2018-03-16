package edu.fit.nao.module.navigation;

import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;
import edu.fit.nao.ModuleRunner;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.List;

public class NavigationRunner extends ModuleRunner {

    private final int xNodes;
    private final int yNodes;

    private final String fileName;

    public NavigationRunner(Session session, int xNodes, int yNodes, String fileName) {

        super(session);

        this.xNodes = xNodes;
        this.yNodes = yNodes;
        this.fileName = fileName;
    }

    @Override
    public void run() throws Exception {

        ALTextToSpeech tts = new ALTextToSpeech(session);

        Node[][] grid = new Node[xNodes][yNodes];
        Node start = null, goal = null;

        int x = 0, y = 0;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {

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

            if (x != xNodes) throw new Exception("File Format Exception");
        }

        printGrid(grid);

        AStar aStar = new AStar(grid);
        List<Point2D> path = aStar.search(start, goal);

        tts.say("Path found!");
        path.forEach(System.out::println);
    }

    private static void printGrid(Node[][] grid) {

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
