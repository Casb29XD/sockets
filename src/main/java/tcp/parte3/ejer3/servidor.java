package tcp.parte3.ejer3;

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
        System.out.println("El servidor TCP está funcionando en el puerto 3400...");
        System.out.println("Esperando a un cliente...");
        Socket serverSideSocket = listener.accept();
        InetAddress clientAddress = serverSideSocket.getInetAddress();
        int clientPort = serverSideSocket.getPort();
        System.out.println("Cliente conectado desde: " + clientAddress.getHostAddress() + ":" + clientPort);
        BufferedReader fromNetwork = new BufferedReader(new InputStreamReader(serverSideSocket.getInputStream()));
        PrintWriter toNetwork = new PrintWriter(serverSideSocket.getOutputStream(), true);
        StringBuilder receivedMessage = new StringBuilder();
        String message;
        while ((message = fromNetwork.readLine()) != null && !message.equals("EOF")) {
            receivedMessage.append(message).append("\n");
        }
        System.out.println("[Servidor] Mensaje recibido:\n" + receivedMessage);
        // Enviar de vuelta el mensaje completo
        toNetwork.println(receivedMessage.toString());
        toNetwork.println("EOF"); // Indica el final del mensaje
        serverSideSocket.close();
        listener.close();
    }
}
