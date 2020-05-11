package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;//The port
    private IServerStrategy serverStrategy;//The strategy for handling clients
    private volatile boolean stop;
    int timeOut;

    public Server(int port,int timeOut, IServerStrategy serverStrategy) {
        this.port = port;
        this.serverStrategy = serverStrategy;
        this.stop = false;
        this.timeOut=timeOut;
    }

    public void start()
    {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(this.timeOut);

            while (!stop)
            {
                try {
                    Socket clientSocket = serverSocket.accept();

                    new Thread(() -> {
                        clientHandle(clientSocket);
                    }).start();
                }
                catch (IOException e) {
                    System.out.println("Where are the clients??");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function receives client socket and handles it
     * @param clientSocket - The client socket
     */
    private void clientHandle(Socket clientSocket) {
        try {
            InputStream inFromClient = clientSocket.getInputStream();
            OutputStream outToClient = clientSocket.getOutputStream();
            this.serverStrategy.serverStrategy(inFromClient, outToClient);

            inFromClient.close();
            outToClient.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void stop()
    {
        this.stop = true;
    }



}
