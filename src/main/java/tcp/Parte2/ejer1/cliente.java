package tcp.Parte2.ejer1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class cliente {
    public static void main(String[] args) throws IOException {
        Socket clientSideSocket = new Socket("192.168.1.116", 3400);
        PrintWriter toNetwork = new PrintWriter(clientSideSocket.getOutputStream(), true);
        BufferedReader fromNetwork = new BufferedReader(new
                InputStreamReader(clientSideSocket.getInputStream()));
        toNetwork.println("Hello World");
        String fromServer = fromNetwork.readLine();
        System.out.println("[Client Windows] From server: " + fromServer);
        clientSideSocket.close();
    }
}
