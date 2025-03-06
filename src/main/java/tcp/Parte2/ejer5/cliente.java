package tcp.Parte2.ejer5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class cliente {
    public static void main(String[] args) throws IOException {
        final String SERVER_IP = "192.168.1.144";
        final int PORT = 3400;
        Socket clientSocket = new Socket(SERVER_IP, PORT);
        PrintWriter toServer = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter message (type 'END' to send):");
        String inputLine;
        while (!(inputLine = userInput.readLine()).equalsIgnoreCase("END")) {
            toServer.println(inputLine);
        }
        toServer.println("END"); // Enviar señal de fin de mensaje
        System.out.println("[Client] Waiting for server response...");
        String serverResponse;
        while ((serverResponse = fromServer.readLine()) != null) {
            System.out.println("[Client] From server: " + serverResponse);
        }
        clientSocket.close();
    }
}