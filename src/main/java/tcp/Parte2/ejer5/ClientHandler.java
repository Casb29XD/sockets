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
            StringBuilder receivedMessage = new StringBuilder();
            String line;
            // Leer múltiples líneas hasta que el cliente envíe "END"
            while ((line = fromClient.readLine()) != null) {
                if ("END".equalsIgnoreCase(line)) break;
                receivedMessage.append(line).append("\n");
            }
            System.out.println("[Server] From client (" + clientSocket.getInetAddress().getHostAddress() + "):");
            System.out.println(receivedMessage.toString());
            // Enviar la respuesta al cliente
            toClient.println(receivedMessage.toString());
            toClient.flush();  // Asegura que se envíen todos los datos
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