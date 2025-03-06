package tcp.parte3.ejer10;

import java.io.*;
import java.net.Socket;

public class cliente2 {
    public static void main(String[] args) throws IOException {
        // Pedir al usuario la ruta del archivo
        String rutaArchivo = "src/main/java/tcp/parte3/ejer4/texto";
        if (rutaArchivo == null || rutaArchivo.trim().isEmpty()) {
            System.out.println("[Cliente] No se ingresó una ruta válida.");
            return;
        }
        File archivo = new File(rutaArchivo);
        if (!archivo.exists() || !archivo.isFile()) {
            System.out.println("[Cliente] El archivo no existe o no es válido.");
            return;
        }
        BufferedReader lectorArchivo = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = lectorArchivo.readLine()) != null) {
            // Para cada línea se crea una nueva conexión
            Socket clientSideSocket = new Socket("localhost", 3400);
            PrintWriter toNetwork = new PrintWriter(clientSideSocket.getOutputStream(), true);
            BufferedReader fromNetwork = new BufferedReader(new InputStreamReader(clientSideSocket.getInputStream()));
            toNetwork.println(linea); // Enviar línea
            toNetwork.println("EOF"); // Indicar fin de mensaje para esta conexión
            // Recibir respuesta del servidor
            String fromServer;
            while ((fromServer = fromNetwork.readLine()) != null && !fromServer.equals("EOF")) {
                System.out.println("[Cliente] Servidor respondió: " + fromServer);
            }
            clientSideSocket.close();
        }
        lectorArchivo.close();
    }
}