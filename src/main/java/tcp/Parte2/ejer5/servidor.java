package tcp.Parte2.ejer5;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {
    public static void main(String[] args) throws IOException {
        InetAddress localAddress = InetAddress.getLocalHost();
        System.out.println("The Echo TCP server is running on:");
        System.out.println("IP Address: " + localAddress.getHostAddress() + " | Port: 3400");
        ServerSocket listener = new ServerSocket(3400);
        while (true) {
            Socket clientSocket = listener.accept();
            System.out.println("A new client has connected: " + clientSocket.getInetAddress().getHostAddress()
                    + " | Port: " + clientSocket.getPort());
            // Crear un hilo para manejar cada cliente
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }
}