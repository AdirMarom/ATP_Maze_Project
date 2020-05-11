package Server;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    public ServerStrategyGenerateMaze(){}

   public void serverStrategy(InputStream inFromClient, OutputStream outToClient){
       BufferedReader fromClient = new BufferedReader(new InputStreamReader(inFromClient));
       try {
           String line= fromClient.readLine();
           int rows=Integer.parseInt(String.valueOf(line.charAt(1)));
           int columns=Integer.parseInt(String.valueOf(line.charAt(3)));



       }
       catch (IOException e){}






    }

}
