package tcp.parte4.ejer12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class EchoTCPClient {
    public static void main(String[] args) throws IOException {
        Socket clientSideSocket = new Socket("localhost", 3400);
        PrintWriter toNetwork = new PrintWriter(clientSideSocket.getOutputStream(), true);
        BufferedReader fromNetwork = new BufferedReader(new InputStreamReader(clientSideSocket.getInputStream()));

        // Scanner para leer el input del cliente
        Scanner scanner = new Scanner(System.in);

        // Genera un string random de 32 bits (0s y 1s)
        String bitString = generateRandomBitString(32);

        // Formatea el string de bits con espacios cada 8 bits para mejor legibilidad
        String formattedBitString = formatBitString(bitString);
        System.out.println("String de bits generado: " + formattedBitString);

        // Le pide al usuario el num n entre 2 y 30
        int n;

        // Ciclo para asegurarse que el num ingresado esté entre 2 y 30
        do {
            System.out.print("Ingrese un número n que este entre 2 y 30: ");
            n = scanner.nextInt();
            // Si el num no está entre 2 y 30, se le pide al usuario que ingrese el num esperado
            if (n < 2 || n > 30) {
                System.out.println("Por favor ingrese un número que este entre 2 y 30.");
            }
        } while (n < 2 || n > 30);

        // Envia el string de bits y el num n al servidor separados por una coma
        toNetwork.println(bitString + "," + n);

        // Recibe y muestra el resultado del cliente
        String fromServer = fromNetwork.readLine();
        String formattedResult = formatBitString(fromServer);
        System.out.println("Resultado: " + formattedResult);

        clientSideSocket.close();
        scanner.close();
    }

    /**
     * Genera un string de bits random de 0s y 1s de un tam esperado
     * @param length Es el tamaño del string  a generar
     * @return Un string de ceros y unos
     */
    private static String generateRandomBitString(int length) {
        Random random = new Random();
        StringBuilder bitString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            bitString.append(random.nextInt(2));
        }

        return bitString.toString();
    }

    /**
     * Formatea un bit de string con espacios cada 8 bits para mejor legibilidad
     * @param bitString El bit de strings a formatear
     * @return El string formateado
     */
    private static String formatBitString(String bitString) {
        StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < bitString.length(); i++) {
            formatted.append(bitString.charAt(i));
            if ((i + 1) % 8 == 0 && i < bitString.length() - 1) {
                formatted.append(" ");
            }
        }

        return formatted.toString();
    }
}
