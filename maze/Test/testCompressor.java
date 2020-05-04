import algorithms.mazeGenerators.*;
import IO.*;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class testCompressor {

    private static int randomNum() {
        Random r = new Random();
        int low = 2;
        int high = 1000;
        return r.nextInt(high - low) + low;
    }

    private static Maze reandom_maze_create(){
        MyMazeGenerator m=new MyMazeGenerator();
        return m.generate(randomNum(),randomNum());
    }

    public static void main(String[] args) {
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        for(int i=0 ;i<100 ;i++) {
            Maze maze = reandom_maze_create(); //Generate new maze

            try {
                // save maze to a file
                OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
                out.write(maze.toByteArray());

                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte savedMazeBytes[] = new byte[0];
            try {
                //read maze from file
                InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
                savedMazeBytes = new byte[maze.toByteArray().length];
                in.read(savedMazeBytes);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Maze loadedMaze = new Maze(savedMazeBytes);
            boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), maze.toByteArray());
            System.out.println(String.format("Mazes equal: %s", areMazesEquals)); //maze should be equal to loadedMaze
        }
    }



}
