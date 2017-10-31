package sd03_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class Chat_enviaMensagem extends Thread {

    private Socket socket;
    
    private PrintWriter out = null;

    public Chat_enviaMensagem(Socket sock) {
        super("work");
        this.socket = sock;
        
        try {
            this.out=new PrintWriter(this.socket.getOutputStream(), true);
        } catch (IOException ex) {
            System.err.println("nao foi possivel abrir a escrita do socket");
        }
    }

    @Override
    public void run() {
        BufferedReader stdln = new BufferedReader(new InputStreamReader(System.in));

        String userInput;

        try {
            while ((userInput = stdln.readLine()) != null) {
                out.println(userInput);
            }
        } catch (IOException ex) {
            Logger.getLogger(Chat_enviaMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
