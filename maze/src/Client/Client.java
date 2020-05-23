package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    private InetAddress host;
    private final int port;
    private IClientStrategy clientStrategy;

    public Client(InetAddress host, int port, IClientStrategy strategy ) {
        this.host = host;
        this.port = port;
        this.clientStrategy = strategy;

}

    public void start(InetAddress serverIP, int port)
    {
        try {
            Socket socket = new Socket(serverIP,port);
            System.out.println("Client is connected to server!");
           Thread.sleep(1000);
            clientStrategy.clientStrategy(socket.getInputStream(),socket.getOutputStream());


            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void communicateWithServer() {
        start(host,port);
    }
}
