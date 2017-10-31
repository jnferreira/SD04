package sd03_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Bruno
 */
public class Chat_ConectWorker extends Thread {

    private Socket socket = null;
    private DatagramSocket socketDiagram = null;
    private ArrayList ListaConetores = null;

    public Chat_ConectWorker(Socket sock, ArrayList lista) {
        super("worker");
        this.socket = sock;
        this.ListaConetores = lista;
        //adiciona o socket a lista
        this.ListaConetores.add(sock);
        //informaçao visual no servidor da ligaçao da thread
        System.out.println("conection new client");
        System.out.println("sou a Thread " + ListaConetores.size());
    }

    @Override
    public void run() {

        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine;
            byte[] buf = new byte[256];

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String data = sdf.format(new Date(System.currentTimeMillis()));

            while ((inputLine = this.socket.getRemoteSocketAddress() + " : " + in.readLine() + " || " + data) != null) {

                Iterator it = this.ListaConetores.iterator();

                while (it.hasNext()) {
                    //Recupera o socket da lista
                    Socket soctet = (Socket) it.next();

                    InetAddress group = InetAddress.getByName("237.1.0.1");
                    
                    buf = inputLine.getBytes();
                    
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
                    socketDiagram.send(packet);

                    //escreve e envia a mensagem pelo socket
                    PrintWriter out = new PrintWriter(soctet.getOutputStream(), true);
                    out.println(inputLine);
                }

                if (inputLine.equals(this.socket.getRemoteSocketAddress() + " : " + "Bye" + " || " + data)) {
                    break;
                }
            }
            //out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
