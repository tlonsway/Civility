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
    }
    public void addSocket(Socket so) {
        connections.add(so);
    }
    public void sendMessage(String msg) {
        for(Socket s : connections) {
            try {
                PrintStream sps = new PrintStream(s.getOutputStream());
                sps.println(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}