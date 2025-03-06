package tcp.parte3.ejer10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor2 {
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(3400);
        System.out.println("El servidor TCP está funcionando en el puerto 3400...");
        while (true) {
            System.out.println("Esperando a un cliente...");
            Socket serverSideSocket = listener.accept();
            InetAddress clientAddress = serverSideSocket.getInetAddress();
            System.out.println("Cliente conectado desde: " + clientAddress.getHostAddress());
            BufferedReader fromNetwork = new BufferedReader(new InputStreamReader(serverSideSocket.getInputStream()));
            PrintWriter toNetwork = new PrintWriter(serverSideSocket.getOutputStream(), true);
            String message;
            while ((message = fromNetwork.readLine()) != null && !message.equals("EOF")) {
                System.out.println("[Servidor] Recibido: " + message);
                toNetwork.println(message); // Enviar de vuelta la línea recibida
            }
            toNetwork.println("EOF"); // Indicar fin del mensaje
            serverSideSocket.close();
        }
    }
}