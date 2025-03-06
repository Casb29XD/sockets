package tcp.Parte2.ejer5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


class ClientHandler implements Runnable {
    private Socket clientSocket;
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }
    @Override
    public void run() {
        try {
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            String message = fromClient.readLine();
            System.out.println("[Server] From client (" + clientSocket.getInetAddress().getHostAddress() + "): " + message);
            // Enviar la respuesta al cliente
            toClient.println(message);
        } catch (IOException e) {
            System.out.println("[Server] Error: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("[Server] Error closing client socket.");
            }
        }
    }
}
