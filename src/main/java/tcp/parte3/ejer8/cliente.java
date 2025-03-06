package tcp.parte3.ejer8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class cliente {
    public static void main(String[] args) throws IOException {
        Socket clientSideSocket = new Socket("localhost", 3400);
        PrintWriter toNetwork = new PrintWriter(clientSideSocket.getOutputStream(), true);
        BufferedReader fromNetwork = new BufferedReader(new
                InputStreamReader(clientSideSocket.getInputStream()));
//        String mensaje = JOptionPane.showInputDialog("Ingrese mensaje");
//        toNetwork.println(mensaje);
        String fromServer = fromNetwork.readLine();
        System.out.println("[Client] From server: " + fromServer);
        clientSideSocket.close();
    }
}
