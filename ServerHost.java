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
        for(int i=0;i<connections.size();i++) {
            System.out.println("sending message: " + msg + " to " + connections.get(i).getInetAddress());
            try {
                PrintStream sps = new PrintStream(connections.get(i).getOutputStream());
                sps.println(msg);
            } catch (Exception e) {
                System.out.println("unable to send message to " + connections.get(i).getInetAddress());
                //connections.remove(i);
                //i--;
            }
            if (connections.get(i).isClosed()) {
                connections.remove(i);
                i--;
                System.out.println("dead socket has been cleared");
            }
        }
    }
    public void inputPacket(String data) {
        if (data.toCharArray()[0]=='c') {
            sendMessage(data);
        }
    }
}