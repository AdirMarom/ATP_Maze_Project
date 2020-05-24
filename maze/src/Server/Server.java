package Server;

import algorithms.search.ISearchingAlgorithm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Server {
    private int port;//The port
    private IServerStrategy iserverStrategy;//The strategy for handling clients
    private volatile boolean stop;
    int timeOut;
    protected ExecutorService threadPoolExecutor;

    public Server(int port,int timeOut, IServerStrategy serverStrategy) {
        this.port = port;
        this.iserverStrategy = serverStrategy;
        this.stop = false;
        this.timeOut=timeOut;
        threadPoolExecutor = Executors.newFixedThreadPool(Configurations.GetThreadNumber());
    }

    public void start(){
        new Thread(() -> {
            start_server();
        }).start();
    }

    public void start_server()
    {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(1000);
            while (!stop)
            {
                try {
                    Socket clientSocket = serverSocket.accept();
                    threadPoolExecutor.execute(() ->{
                        clientHandle(clientSocket);
                    });
                }
                catch (IOException e) {
                    System.out.println("Where are the clients??");
                }
            }
            serverSocket.close();
            threadPoolExecutor.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            synchronized (this){
                this.iserverStrategy.serverStrategy(inFromClient, outToClient);
            }


            inFromClient.close();
            outToClient.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
