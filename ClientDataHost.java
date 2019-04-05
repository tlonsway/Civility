import java.net.*;
import java.io.*;
import java.util.*;
public class ClientDataHost implements Runnable {
    ServerSocket ss;
    Socket s;
    BufferedReader din;
    DataOutputStream dout;
    InputStream is;
    ArrayList<String> chat = new ArrayList<String>();
    PrintStream ps;
    public static void main(String[] args) throws Exception {
        new ClientDataHost();
    }
    public ClientDataHost() {
        try {
            ss = new ServerSocket(1555);
            //din = new BufferedReader(new InputStreamReader(ss.getInputStream()));
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            s = ss.accept();
            din = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("server connection achieved " + s.getInetAddress());
            //(new Thread(new serverRunabble(s))).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true) {
            String line = "";
            try {
                line = din.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("server input received: " + line);
        }
    }
    public ArrayList<String> getChat() {
        return chat;
    }
    private void process(String input) {
        
        
        
    }
}