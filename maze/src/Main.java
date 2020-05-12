import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import Server.*;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.io.*;

public class Main {

    public static void main(String[] args) {



        Server server=new Server(5400,1000,new ServerStrategyGenerateMaze());
/*
        server.start();
        int[] mazeDimension={7,7};
        try{
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream("kkk"));
           // InputStream out = new InputStream(new FileOutputStream("kkk")) {
                @Override
                public int read() throws IOException {
                    return 0;
                }
            };
        }
        catch (IOException e){}

*/




        IMazeGenerator m= new MyMazeGenerator();
        //Maze maze=m.generate(4,6);
        try {
            AMazeGenerator m2=new MyMazeGenerator();
            Maze maze12=m2.generate(5,300);
            OutputStream o=new MyCompressorOutputStream(new FileOutputStream("save.maze")) ;
            o.write(maze12.toByteArray());
            InputStream in=new MyDecompressorInputStream(new FileInputStream("save.maze"));
            MyCompressorOutputStream o1=(MyCompressorOutputStream)o;
            in.read((o1.getCompress_object_array()));
        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


/*
        AMazeGenerator m1=new MyMazeGenerator();
        Maze maze1=m1.generate(700,700);
        maze1.print();
        byte[] test=maze1.toByteArray();
        String s105="";
        for(int i=0 ;i<test.length;i++){
           s105+=test[i]+"x";
        }

        /*
        System.out.println(s105);
       // maze1.print2();
        for(int i=0 ; i<50 ;i++){
          //  m1.generate(2,4);
            m1.generate(3,2);
            m1.generate(2,3);
            m1.generate(3,3);
            m1.generate(5,5);
            m1.generate(2,9);
            m1.generate(100,115);
            m1.generate(2,12);
            m1.generate(650,8);
            m1.generate(99,9);
            m1.generate(15,30);
            m1.generate(17,38);
            m1.generate(5,3);

        }



       maze1.print2();
        DepthFirstSearch D1=new DepthFirstSearch();
        BreadthFirstSearch D2=new BreadthFirstSearch();
        BestFirstSearch D3=new BestFirstSearch();
        ISearchable maze=new SearchableMaze(maze1);
        System.out.println(" ****BFS****");
        Solution s=D2.solve(maze);
        s.print_toDebug(maze1);
        System.out.println(s.getSolutionSize());
        System.out.println(s.getSolutionCost());
        System.out.println(s.getSolutionPath());
        System.out.println(" ****DFS****");

        Solution s1=D1.solve(maze);
        s1.print_toDebug(maze1);
        System.out.println(s1.getSolutionSize());
        System.out.println(s1.getSolutionCost());
        System.out.println(s1.getSolutionPath());
        System.out.println(" ****best****");
        Solution s3=D3.solve(maze);
        s3.print_toDebug(maze1);
        System.out.println(s3.getSolutionSize());
        System.out.println(s3.getSolutionCost());
        System.out.println(s3.getSolutionPath());
*/


    }
}
