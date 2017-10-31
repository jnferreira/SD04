package sd03_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Bruno
 */
public class Chat_DisplayMensagem extends Thread {

    private Socket socket = null;

    public Chat_DisplayMensagem(Socket st) {
        super("wo");
        this.socket = st;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            
            while (true) {                
                System.out.println(in.readLine());
            }
        } catch (IOException ex) {
            System.err.println("nao foi possivel abrir o socket de leitura");
        }

    }
}
