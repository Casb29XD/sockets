package tcp.parte3.ejer10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(3400);
        System.out.println("The Echo TCP server is running on port 3400 ...");
        System.out.println("The server is waiting for a client.");
        Socket serverSideSocket = listener.accept(); // Espera a un cliente
        InetAddress clientAddress = serverSideSocket.getInetAddress(); // Obtiene la IP del cliente
        int clientPort = serverSideSocket.getPort(); // Obtiene el puerto del cliente
        System.out.println("A client has connected.");
        System.out.println("Client IP: " + clientAddress.getHostAddress() + " | Client Port: " + clientPort);
        BufferedReader fromNetwork = new BufferedReader(new InputStreamReader(serverSideSocket.getInputStream()));
        PrintWriter toNetwork = new PrintWriter(serverSideSocket.getOutputStream(), true);
        String message = fromNetwork.readLine();
        System.out.println("[Server UBUNTU] From client: " + message);
        toNetwork.println(message); // Responde con el mismo mensaje
        serverSideSocket.close();
        listener.close();
    }
}
