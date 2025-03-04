package tcp.parte4.ejer11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoTCPClient {
    public static void main(String[] args) throws IOException {
        Socket clientSideSocket = new Socket("localhost", 3400);
        PrintWriter toNetwork = new PrintWriter(clientSideSocket.getOutputStream(), true);
        BufferedReader fromNetwork = new BufferedReader(new InputStreamReader(clientSideSocket.getInputStream()));

        // Scanner para leer el input del usuario
        Scanner scanner = new Scanner(System.in);

        // Convierte el número ingresado por el usuario a hexadecimal (192)
        System.out.print("Ingrese un integer para convertir a hexadecimal: ");
        int numberToConvert = scanner.nextInt();

        // La cantidad de digitos para el resultado (4)
        System.out.print("Entre la cantidad de digitos para el resultado hexadecimal: ");
        int digits = scanner.nextInt();

        // Envia dos valores al servidor separados por coma
        toNetwork.println(numberToConvert + "," + digits);

        // Recibe y muestra el resultado del server (00C0)
        String fromServer = fromNetwork.readLine();
        System.out.println("Resultado: " + fromServer);

        clientSideSocket.close();
        scanner.close();
    }
}
