package tcp.parte4.ejer12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(3400);
        System.out.println("El server TCP se esta ejecutando en el port 3400 ...");
        System.out.println("El servidor esta esperando por un cliente.");

        Socket serverSideSocket = listener.accept();
        System.out.println("Un cliente se ha conectado.");

        BufferedReader fromNetwork = new BufferedReader(new InputStreamReader(serverSideSocket.getInputStream()));
        PrintWriter toNetwork = new PrintWriter(serverSideSocket.getOutputStream(), true);

        // Lee el mensaje del cliente
        String message = fromNetwork.readLine();
        System.out.println("[Server] From client: " + message);

        // Separa el mensaje del cliente y convierte el num n a entero
        String[] parts = message.split(",");
        String bitString = parts[0];
        int n = Integer.parseInt(parts[1]);

        // Hace llamado al metodo para procesar el string de bits
        String processedString = processBitString(bitString, n);

        // Envia el resultado al cliente
        toNetwork.println(processedString);

        serverSideSocket.close();
        listener.close();
    }

    /**
     * Procesa un bit de string manteniendo los promeros n bits y dejando el resto en 0
     * @param bitString El string original
     * @param n El numero de bits a mantener
     * @return El bit procesado
     */
    private static String processBitString(String bitString, int n) {
        StringBuilder result = new StringBuilder();

        // Mantiene los primeros n bits
        for (int i = 0; i < n && i < bitString.length(); i++) {
            result.append(bitString.charAt(i));
        }

        // Deja el resto en 0
        for (int i = n; i < bitString.length(); i++) {
            result.append('0');
        }

        return result.toString();
    }
}
