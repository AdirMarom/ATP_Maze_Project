import algorithms.mazeGenerators.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class testAllGenerator {

    public static void main(String[] args) {
        testMazeGenerator(new SimpleMazeGenerator());
        testMazeGenerator(new EmptyMazeGenerator());
        testMazeGenerator(new MyMazeGenerator());
        //testMazeGenerator(null);
    }

    private static int randomNum() {
        Random r = new Random();
        int low = 2;
        int high = 1000;
        return r.nextInt(high - low) + low;
    }

    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
        // prints the time it takes the algorithm to run
        for (int i = 0; i < 100; i++) {
            int x = randomNum();
            int y = randomNum();
            assertNotNull(mazeGenerator.generate(x/*rows*/, y/*columns*/));
            long time = mazeGenerator.measureAlgorithmTimeMillis(x/*rows*/, y/*columns*/);
            if (time / 1000 > 30) {
                System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(x/*rows*/, y/*columns*/)));
            }
        }
            //prints the maze
            Maze maze = mazeGenerator.generate(91/*rows*/, 170/*columns*/);
            //maze.print();

            long time_1000 = mazeGenerator.measureAlgorithmTimeMillis(1000/*rows*/, 1000/*columns*/);
            if (time_1000 / 1000 > 30) {
                System.out.println(String.format("Maze generation time(ms): %s", time_1000));
            }

            //nullMaze
            assertNull(mazeGenerator.generate(0/*rows*/, 0/*columns*/));
            assertNull(mazeGenerator.generate(0/*rows*/, 1/*columns*/));
            assertNull(mazeGenerator.generate(1/*rows*/, 0/*columns*/));

            // get the maze entrance
            Position startPosition = maze.getStart_position();

            // print the position
             System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
            // prints the maze exit position

            System.out.println(String.format("Goal Position: %s", maze.getEnd_position()));
        }
    }
