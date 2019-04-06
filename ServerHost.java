import java.net.*;
import java.io.*;
import java.util.*;
public class ServerHost {
    ServerSocket ss;
    Socket s;
    BufferedReader din;
    DataOutputStream dout;
    InputStream is;
    ArrayList<Socket> connections;
    PrintStream ps;
    public static void main(String[] args) {
        new ServerHost();
    }
    public ServerHost() {
        connections = new ArrayList<Socket>();
        (new Thread(new ServerConnectionThread(this))).start();
    }
    public void addSocket(Socket so) {
        connections.add(so);
        System.out.println("adding socket to server database");
        (new Thread(new ServerPoolThread(this,so))).start();
        System.out.println("socket connection added to database");
    }
    public void sendMessage(String msg) {
        for(Socket s : connections) {
            System.out.println("sending message: " + msg + " to " + s.getInetAddress());
            try {
                PrintStream sps = new PrintStream(s.getOutputStream());
                sps.println(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void inputPacket(String data) {
        if (data.toCharArray()[0]=='c') {
            sendMessage(data);
        }
    }
}