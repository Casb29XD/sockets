package tcp.parte3.ejer2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(3400);
        System.out.println("The Echo TCP server is running on port 3400 ...");
        System.out.println("The server is waiting for a client.");
        Socket serverSideSocket = listener.accept(); // Espera a un cliente
        InetAddress clientAddress = serverSideSocket.getInetAddress();
        int clientPort = serverSideSocket.getPort();
        System.out.println("A client has connected.");
        System.out.println("Client IP: " + clientAddress.getHostAddress() + " | Client Port: " + clientPort);
        // Establecer tiempo de espera de 10 segundos para recibir datos
        serverSideSocket.setSoTimeout(10000);
        try {
            BufferedReader fromNetwork = new BufferedReader(new InputStreamReader(serverSideSocket.getInputStream()));
            PrintWriter toNetwork = new PrintWriter(serverSideSocket.getOutputStream(), true);
            System.out.println("Waiting for client message...");
            String message = fromNetwork.readLine(); // Espera el mensaje
            if (message != null) {
                System.out.println("[Server UBUNTU] From client: " + message);
                toNetwork.println(message); // Responde con el mismo mensaje
            } else {
                System.out.println("[Server UBUNTU] No message received.");
            }
        } catch (SocketTimeoutException e) {
            System.out.println("[Server UBUNTU] Client took too long to respond. Closing connection...");
        }
        serverSideSocket.close();
        listener.close();
    }
}
