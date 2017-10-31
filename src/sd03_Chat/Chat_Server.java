package sd03_Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 *
 * @author Bruno
 */
public class Chat_Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        int port = 2048;
        boolean listening = true;
        ArrayList listaLigacoes = new ArrayList();
        int nUtilizadores = 0;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor a escuta na porta: " + port);
            if (listaLigacoes != null) {
                System.out.println("Lista de coneçoes inicializada");
                System.out.println("Tamanho atual da lista: " + listaLigacoes.size());
            }

        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port + ".");
            System.exit(-1);
        }

        while (listening) {
            new Chat_ConectWorker(serverSocket.accept(), listaLigacoes).start();
            System.out.println("Utilizador conectado.");
            nUtilizadores++;
            System.out.println("Existem " + nUtilizadores + " conectados.");
        }

    }
}
