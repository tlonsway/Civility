import java.net.*;
import java.io.*;
import java.util.*;
public class ClientDataHost implements Runnable {
    ServerSocket ss;
    Socket s;
    BufferedReader din;
    DataOutputStream dout;
    InputStream is;
    ArrayList<String> chat;
    PrintStream ps;
    public static void main(String[] args) throws Exception {
        new ClientDataHost();
    }
    public ClientDataHost() {
        chat = new ArrayList<String>();
    }
    public void run() {
        try {
            ss = new ServerSocket(1555);
            //din = new BufferedReader(new InputStreamReader(ss.getInputStream()));
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                process(line);
                chat.add(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("server input received: " + line);
        }
    }
    public ArrayList<String> getChat() {
        return chat;
    }
    public ArrayList<String> getRecent() {
        ArrayList<String> temp = new ArrayList<String>();
        for(int i=chat.size()-15;i<chat.size();i++) {
            temp.add(chat.get(i));
        }
        return temp;
    }
    private void process(String input) {
        /*Structure of a server command:
         * t:t2:s1:s2:s3:s4:s5
         * t is type 1(chat,location,mapdata), t2 is type 2(more unique commands)
         * s1,s2,s3,s4,s5, are strings that can be sent optionally
        */
        //t = input.
        
        
    }
}