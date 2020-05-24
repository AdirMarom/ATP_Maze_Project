import algorithms.mazeGenerators.*;
import IO.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class JUnitTestingtestCompressor {

      int randomNum() {
        Random r = new Random();
        int low = 2;
        int high = 1000;
        return r.nextInt(high - low) + low;
    }

      Maze[] reandom_maze_create(){
        MyMazeGenerator m=new MyMazeGenerator();
        int x=randomNum();
        int y=randomNum();
        Maze m1=m.generate(x,x);
        Maze m2=m.generate(y,x);
        Maze m3=m.generate(x,y);
        Maze[] arr={m1,m2,m3};
        return arr;
    }

      Maze test_transfer(Maze m,String mazeFileName){

          if(m==null)
              return null;
        try {
            // save maze to a file
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(m.toByteArray());

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte savedMazeBytes[] = new byte[0];
        try {
            //read maze from file
            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[m.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Maze loadedMaze = new Maze(savedMazeBytes);
        return loadedMaze;
    }

    @Test
    //maze row=col
     void test_1(){
        String mazeFileName = "savedMaze.maze";
        Maze[] all_option_maze=reandom_maze_create();//Generate new maze
        Maze loadedMaze =test_transfer(all_option_maze[0],mazeFileName);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), all_option_maze[0].toByteArray());
        boolean T=true;
         if(!areMazesEquals) {
             System.out.println("sent byte array: " + Arrays.toString(all_option_maze[0].toByteArray()));
             System.out.println("return byte array: "+ Arrays.toString(loadedMaze.toByteArray()));
         }
         assertEquals(T,areMazesEquals,"Maze failure in reconstruction byte array");
         Maze RE_build_maze=new Maze(loadedMaze.toByteArray());
         assertFalse(!RE_build_maze.equals(all_option_maze[0]),"Maze failure to create Maze from compress byte[]");
    }

    @Test
        //maze col != row
    void test_2(){
        String mazeFileName = "savedMaze.maze";
        Maze[] all_option_maze=reandom_maze_create();//Generate new maze
        Maze loadedMaze =test_transfer(all_option_maze[1],mazeFileName);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), all_option_maze[1].toByteArray());
        boolean T=true;
        if(!areMazesEquals) {
            System.out.println("sent byte array: " + Arrays.toString(all_option_maze[1].toByteArray()));
            System.out.println("return byte array: "+ Arrays.toString(loadedMaze.toByteArray()));
        }
        assertEquals(T,areMazesEquals,"Maze failure in reconstruction byte array");
        Maze RE_build_maze=new Maze(loadedMaze.toByteArray());
        assertFalse(!RE_build_maze.equals(all_option_maze[1]),"Maze failure to create Maze from compress byte[]");

    }

    @Test
        //maze col != row
    void test_3(){
        String mazeFileName = "savedMaze.maze";
        Maze[] all_option_maze=reandom_maze_create();//Generate new maze

        Maze loadedMaze =test_transfer(all_option_maze[2],mazeFileName);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), all_option_maze[2].toByteArray());
        boolean T=true;
        if(!areMazesEquals) {
            System.out.println("sent byte array: " + Arrays.toString(all_option_maze[2].toByteArray()));
            System.out.println("return byte array: "+ Arrays.toString(loadedMaze.toByteArray()));
        }
        assertEquals(T,areMazesEquals,"Maze failure in reconstruction byte array");
        Maze RE_build_maze=new Maze(loadedMaze.toByteArray());
        assertFalse(!RE_build_maze.equals(all_option_maze[2]),"Maze failure to create Maze from compress byte[]");
    }

    @Test
        // maze is null
    void test_4(){
          /*
        String mazeFileName = "savedMaze.maze";
        Maze loadedMaze =test_transfer(null,mazeFileName);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), null);
        boolean F=false;
        byte[] empty=null;
        assertNull(new Maze(empty),"Maze failure when get null byte array");
        assertEquals(areMazesEquals,F,"compress failure when get null byte array");
        */
    }

    @Test
        //maze 1000*1000
    void test_5(){
        String mazeFileName = "savedMaze.maze";
        MyMazeGenerator m=new MyMazeGenerator();
        Maze option_maze=m.generate(1000,1000);//Generate new maze

        Maze loadedMaze =test_transfer(option_maze,mazeFileName);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), option_maze.toByteArray());
        boolean T=true;
        if(!areMazesEquals) {
            System.out.println("sent byte array: " + Arrays.toString(option_maze.toByteArray()));
            System.out.println("sent byte array len: " + option_maze.toByteArray().length);
            System.out.println("return byte array: "+ Arrays.toString(loadedMaze.toByteArray()));
            System.out.println("return byte array len: "+ loadedMaze.toByteArray().length);
        }
        assertEquals(T,areMazesEquals,"Maze failure in reconstruction byte array");
        Maze RE_build_maze=new Maze(loadedMaze.toByteArray());
        assertFalse(!RE_build_maze.equals(option_maze),"Maze failure to create Maze from compress byte[]");
    }

    @Test
        //maze 100*100
    void test_6(){
        String mazeFileName = "savedMaze.maze";
        MyMazeGenerator m=new MyMazeGenerator();
        Maze option_maze=m.generate(100,100);//Generate new maze

        Maze loadedMaze =test_transfer(option_maze,mazeFileName);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), option_maze.toByteArray());
        boolean T=true;
        if(!areMazesEquals) {
            System.out.println("sent byte array: " + Arrays.toString(option_maze.toByteArray()));
            System.out.println("sent byte array len: " + option_maze.toByteArray().length);
            System.out.println("return byte array: "+ Arrays.toString(loadedMaze.toByteArray()));
            System.out.println("return byte array len: "+ loadedMaze.toByteArray().length);
        }
        assertEquals(T,areMazesEquals,"Maze failure in reconstruction byte array");
        Maze RE_build_maze=new Maze(loadedMaze.toByteArray());
        assertFalse(!RE_build_maze.equals(option_maze),"Maze failure to create Maze from compress byte[]");
    }

    @Test
        //maze 4*4
    void test_7(){
        String mazeFileName = "savedMaze.maze";
        MyMazeGenerator m=new MyMazeGenerator();
        Maze option_maze=m.generate(4,4);//Generate new maze

        Maze loadedMaze =test_transfer(option_maze,mazeFileName);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), option_maze.toByteArray());
        boolean T=true;
        if(!areMazesEquals) {
            System.out.println("sent byte array: " + Arrays.toString(option_maze.toByteArray()));
            System.out.println("sent byte array len: " + option_maze.toByteArray().length);
            System.out.println("return byte array: "+ Arrays.toString(loadedMaze.toByteArray()));
            System.out.println("return byte array len: "+ loadedMaze.toByteArray().length);
        }
        assertEquals(T,areMazesEquals,"Maze failure in reconstruction byte array");
        Maze RE_build_maze=new Maze(loadedMaze.toByteArray());
        assertFalse(!RE_build_maze.equals(option_maze),"Maze failure to create Maze from compress byte[]");
    }

    @Test
        //maze 2*2
    void test_8(){
        String mazeFileName = "savedMaze.maze";
        MyMazeGenerator m=new MyMazeGenerator();
        Maze option_maze=m.generate(2,2);//Generate new maze

        Maze loadedMaze =test_transfer(option_maze,mazeFileName);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), option_maze.toByteArray());
        boolean T=true;
        if(!areMazesEquals) {
            System.out.println("sent byte array: " + Arrays.toString(option_maze.toByteArray()));
            System.out.println("sent byte array len: " + option_maze.toByteArray().length);
            System.out.println("return byte array: "+ Arrays.toString(loadedMaze.toByteArray()));
            System.out.println("return byte array len: "+ loadedMaze.toByteArray().length);
        }
        assertEquals(T,areMazesEquals,"Maze failure in reconstruction byte array");
        Maze RE_build_maze=new Maze(loadedMaze.toByteArray());
        assertFalse(!RE_build_maze.equals(option_maze),"Maze failure to create Maze from compress byte[]");
    }

    @Test
    //maze 2*3
    void test_9(){
        String mazeFileName = "savedMaze.maze";
        MyMazeGenerator m=new MyMazeGenerator();
        Maze option_maze=m.generate(2,3);//Generate new maze

        Maze loadedMaze =test_transfer(option_maze,mazeFileName);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), option_maze.toByteArray());
        boolean T=true;
        if(!areMazesEquals) {
            System.out.println("sent byte array: " + Arrays.toString(option_maze.toByteArray()));
            System.out.println("sent byte array len: " + option_maze.toByteArray().length);
            System.out.println("return byte array: "+ Arrays.toString(loadedMaze.toByteArray()));
            System.out.println("return byte array len: "+ loadedMaze.toByteArray().length);
        }
        assertEquals(T,areMazesEquals,"Maze failure in reconstruction byte array");
        Maze RE_build_maze=new Maze(loadedMaze.toByteArray());
        assertFalse(!RE_build_maze.equals(option_maze),"Maze failure to create Maze from compress byte[]");
    }

    @Test
    //maze 3*2
    void test_10(){
        String mazeFileName = "savedMaze.maze";
        MyMazeGenerator m=new MyMazeGenerator();
        Maze option_maze=m.generate(3,2);//Generate new maze

        Maze loadedMaze =test_transfer(option_maze,mazeFileName);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), option_maze.toByteArray());
        boolean T=true;
        if(!areMazesEquals) {
            System.out.println("sent byte array: " + Arrays.toString(option_maze.toByteArray()));
            System.out.println("sent byte array len: " + option_maze.toByteArray().length);
            System.out.println("return byte array: "+ Arrays.toString(loadedMaze.toByteArray()));
            System.out.println("return byte array len: "+ loadedMaze.toByteArray().length);
        }
        assertEquals(T,areMazesEquals,"Maze failure in reconstruction byte array");
        Maze RE_build_maze=new Maze(loadedMaze.toByteArray());
        assertFalse(!RE_build_maze.equals(option_maze),"Maze failure to create Maze from compress byte[]");
    }

    @Test
    //maze 3*3
    void test_11(){
        String mazeFileName = "savedMaze.maze";
        MyMazeGenerator m=new MyMazeGenerator();
        Maze option_maze=m.generate(3,3);//Generate new maze

        Maze loadedMaze =test_transfer(option_maze,mazeFileName);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), option_maze.toByteArray());
        boolean T=true;
        if(!areMazesEquals) {
            System.out.println("sent byte array: " + Arrays.toString(option_maze.toByteArray()));
            System.out.println("sent byte array len: " + option_maze.toByteArray().length);
            System.out.println("return byte array: "+ Arrays.toString(loadedMaze.toByteArray()));
            System.out.println("return byte array len: "+ loadedMaze.toByteArray().length);
        }
        assertEquals(T,areMazesEquals,"Maze failure in reconstruction byte array");
        Maze RE_build_maze=new Maze(loadedMaze.toByteArray());
        assertFalse(!RE_build_maze.equals(option_maze),"Maze failure to create Maze from compress byte[]");;
    }

    @Test
        //maze 3*4
    void test_12(){
        String mazeFileName = "savedMaze.maze";
        MyMazeGenerator m=new MyMazeGenerator();
        Maze option_maze=m.generate(3,4);//Generate new maze

        Maze loadedMaze =test_transfer(option_maze,mazeFileName);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), option_maze.toByteArray());
        boolean T=true;
        if(!areMazesEquals) {
            System.out.println("sent byte array: " + Arrays.toString(option_maze.toByteArray()));
            System.out.println("sent byte array len: " + option_maze.toByteArray().length);
            System.out.println("return byte array: "+ Arrays.toString(loadedMaze.toByteArray()));
            System.out.println("return byte array len: "+ loadedMaze.toByteArray().length);
        }
        assertEquals(T,areMazesEquals,"Maze failure in reconstruction byte array");
        Maze RE_build_maze=new Maze(loadedMaze.toByteArray());
        assertFalse(!RE_build_maze.equals(option_maze),"Maze failure to create Maze from compress byte[]");
    }

    @Test
        //maze 4*3
    void test_13(){
        String mazeFileName = "savedMaze.maze";
        MyMazeGenerator m=new MyMazeGenerator();
        Maze option_maze=m.generate(4,3);//Generate new maze

        Maze loadedMaze =test_transfer(option_maze,mazeFileName);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), option_maze.toByteArray());
        boolean T=true;
        if(!areMazesEquals) {
            System.out.println("sent byte array: " + Arrays.toString(option_maze.toByteArray()));
            System.out.println("sent byte array len: " + option_maze.toByteArray().length);
            System.out.println("return byte array: "+ Arrays.toString(loadedMaze.toByteArray()));
            System.out.println("return byte array len: "+ loadedMaze.toByteArray().length);
        }
        assertEquals(T,areMazesEquals,"Maze failure in reconstruction byte array");
        Maze RE_build_maze=new Maze(loadedMaze.toByteArray());
        assertFalse(!RE_build_maze.equals(option_maze),"Maze failure to create Maze from compress byte[]");
    }

}
