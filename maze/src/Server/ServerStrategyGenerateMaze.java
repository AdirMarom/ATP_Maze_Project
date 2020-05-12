package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    public ServerStrategyGenerateMaze(){}

   public void serverStrategy(InputStream inFromClient, OutputStream outToClient){
       int[] arr2;
       int row;
       int col;
       MyMazeGenerator my=new MyMazeGenerator();
       try {
           ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
           ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
           arr2=(int[])fromClient.readObject();
           row=arr2[0];
           col=arr2[1];
           Maze M=my.generate(row,col);
           byte[] arr_byte=M.toByteArray();
           OutputStream out = new MyCompressorOutputStream(toClient);
           out.write(arr_byte);
           toClient.flush();


       } catch (IOException | ClassNotFoundException e) {
           e.printStackTrace();
       }

       /*
       BufferedReader fromClient = new BufferedReader(new InputStreamReader(inFromClient));
       PrintWriter Toclient=new PrintWriter(outToClient);*/

       String pharse="    vbdfhfdgf";










    }

}
