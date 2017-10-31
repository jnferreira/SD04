package sd03_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Bruno
 */
public class Chat_Cliente {

    public static void main(String[] args) throws IOException {

        //nome do servidor
        String nomeServidor = "127.0.0.1";
        //porta de acesso
        int numeroPorta = 2048;

        Socket echoSocket = null;
        //envia escrita
        PrintWriter out = null;
        //recebe mensagem
        BufferedReader in = null;
        
        MulticastSocket socket = new MulticastSocket(4446);
        InetAddress address = InetAddress.getByName("237.1.0.1");
        socket.joinGroup(address);
        
        DatagramPacket packet;

        //socket equivale a ligaçao
        try {
            echoSocket = new Socket(nomeServidor, numeroPorta);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Dont´t know about host: " + nomeServidor);
            System.exit(1);

        } catch (IOException ex) {
            System.err.println("Coult´t get I/O for the connection to : " + nomeServidor);
            System.exit(1);
        }

        new Chat_enviaMensagem(echoSocket).start();
        new Chat_DisplayMensagem(echoSocket).start();
        
        socket.leaveGroup(address);
        socket.close();

    }
}
