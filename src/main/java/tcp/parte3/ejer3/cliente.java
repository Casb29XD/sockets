package tcp.parte3.ejer3;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class cliente {
    public static void main(String[] args) throws IOException {
        Socket clientSideSocket = new Socket("localhost", 3400);
        PrintWriter toNetwork = new PrintWriter(clientSideSocket.getOutputStream(), true);
        BufferedReader fromNetwork = new BufferedReader(new InputStreamReader(clientSideSocket.getInputStream()));
        StringBuilder mensaje = new StringBuilder();
        String linea;
        do {
            linea = JOptionPane.showInputDialog("Ingrese una línea de mensaje (deje vacío para enviar)");
            if (linea != null && !linea.isEmpty()) {
                mensaje.append(linea).append("\n");
                toNetwork.println(linea);
            }
        } while (linea != null && !linea.isEmpty());
        toNetwork.println("EOF"); // Indica el final del mensaje
        // Recibir respuesta del servidor
        System.out.println("[Cliente] Recibiendo mensaje del servidor...");
        StringBuilder respuesta = new StringBuilder();
        String fromServer;
        while ((fromServer = fromNetwork.readLine()) != null && !fromServer.equals("EOF")) {
            respuesta.append(fromServer).append("\n");
        }
        System.out.println("[Cliente] Mensaje recibido:\n" + respuesta);
        clientSideSocket.close();
    }
}
