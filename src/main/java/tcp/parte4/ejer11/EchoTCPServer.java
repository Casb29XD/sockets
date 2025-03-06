package tcp.parte4.ejer11;

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
        System.out.println("El server esta esperando por un cliente.");

        Socket serverSideSocket = listener.accept();
        System.out.println("Un cliente se ha conectado.");

        BufferedReader fromNetwork = new BufferedReader(new InputStreamReader(serverSideSocket.getInputStream()));
        PrintWriter toNetwork = new PrintWriter(serverSideSocket.getOutputStream(), true);

        // Lee el mensaje del cliente
        String message = fromNetwork.readLine();
        System.out.println("[Server] From client: " + message);

        // Convierte el numero y los digitos que vienen del mensaje client
        String[] parts = message.split(",");
        int number = Integer.parseInt(parts[0]);
        int digits = Integer.parseInt(parts[1]);

        // Convierte el numero a hexadecimal con los digitos establecidos
        String hexResult = convertToHexWithDigits(number, digits);

        // Envia el resultado al cliente
        toNetwork.println(hexResult);

        serverSideSocket.close();
        listener.close();
    }

    /**
     * Convierte un integer a un string hexadecimal con una cantidad espeficca de digitos
     * @param number El integer a convertir
     * @param digits La cantidad de digitos en el resultado hexadecimal
     * @return El string hexadecimal formateado
     */
    private static String convertToHexWithDigits(int number, int digits) {
        // Convertir el numero a hex string en mayus
        String hex = Integer.toHexString(number).toUpperCase();

        // Si el hex string tiene menos digitos de los esperados, los llena de ceros a la derecha
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < digits - hex.length(); i++) {
            result.append('0');
        }

        result.append(hex);
        return result.toString();
    }
}
