package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;//The port
    private IServerStrategy iserverStrategy;//The strategy for handling clients
    private volatile boolean stop;
    int timeOut;
    public static void main(String[] args) throws IOException {


    }

    public Server(int port,int timeOut, IServerStrategy serverStrategy) {
        this.port = port;
        this.iserverStrategy = serverStrategy;
        this.stop = false;
        this.timeOut=timeOut;
    }

    private void serverStart(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(1000);
            while (!stop)
            {
                try {
                    Socket clientSocket = serverSocket.accept();

                    InputStream inFromClient = clientSocket.getInputStream();
                    OutputStream outToClient = clientSocket.getOutputStream();

                    this.iserverStrategy.serverStrategy(inFromClient, outToClient);

                    inFromClient.close();
                    outToClient.close();
                    clientSocket.close();
                }
                catch (IOException e) {
                    System.out.println("Where are the clients??");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start()
    {

        new Thread(() -> {
            serverStart();
        }).start();

    }

    public void stop()
    {
        System.out.println("The server has stopped!");
        this.stop = true;
    }

    /**
     * This function receives client socket and handles it
     * @param clientSocket - The client socket
     */
    private void clientHandle(Socket clientSocket) {
        try {
            InputStream inFromClient = clientSocket.getInputStream();
            OutputStream outToClient = clientSocket.getOutputStream();
            this.iserverStrategy.serverStrategy(inFromClient, outToClient);

            inFromClient.close();
            outToClient.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
